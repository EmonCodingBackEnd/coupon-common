/*
 * 文件名称：FTPTemplate.java
 * 系统名称：[系统名称]
 * 模块名称：FTP模板
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180622 20:17
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180622-01         Rushing0711     M201806222017 新建文件
 ********************************************************************************/
package com.coding.coupon.common.ftp.template;

import com.coding.coupon.common.ftp.config.ServerConfig;
import com.coding.coupon.common.ftp.exception.FTPException;
import com.coding.coupon.common.ftp.param.*;
import com.coding.coupon.common.ftp.pool.GenericKeyedFTPClientPool;
import com.coding.coupon.common.ftp.property.ReplayCode;
import com.coding.coupon.common.ftp.result.*;
import com.coding.coupon.common.regex.RegexSupport;
import com.coding.coupon.common.regex.result.FilenameResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * FTP模板.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180622 20:17</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@Component
@ConditionalOnBean(GenericKeyedFTPClientPool.class)
public class FTPTemplate implements FTPOperations {

    private final GenericKeyedFTPClientPool ftpClientPool;

    @Autowired
    public FTPTemplate(GenericKeyedFTPClientPool ftpClientPool) {
        this.ftpClientPool = ftpClientPool;
    }

    public GenericKeyedFTPClientPool getFtpClientPool() {
        return ftpClientPool;
    }

    private FTPClient borrowFTPClient(ServerConfig serverConfig) {
        FTPClient ftpClient;
        try {
            ftpClient = getFtpClientPool().borrowObject(serverConfig);
        } catch (Exception e) {
            log.error("【FTP】获取FTPClient实例异常", e);
            throw new FTPException(e);
        }
        return ftpClient;
    }

    private void invalidateFTPClient(ServerConfig serverConfig, FTPClient ftpClient) {
        try {
            getFtpClientPool().invalidateObject(serverConfig, ftpClient);
        } catch (Exception e) {
            throw new FTPException(e);
        }
    }

    private void returnFTPClient(ServerConfig serverConfig, FTPClient ftpClient) {
        getFtpClientPool().returnObject(serverConfig, ftpClient);
    }

    private boolean changeWorkingDirectory(FTPClient ftpClient, String remoteDirectory) {
        boolean success;
        try {
            // 每次进来，都先恢复当前目录为根目录
            success = ftpClient.changeToParentDirectory();
            if (success) {
                if (!StringUtils.isEmpty(remoteDirectory)) {
                    success = ftpClient.changeWorkingDirectory(remoteDirectory);
                    if (!success) {
                        // 默认设置true，如果有任何目录失败，则认为false并跳出
                        success = true;
                        StringTokenizer token = new StringTokenizer(remoteDirectory, "\\//");
                        while (token.hasMoreElements()) {
                            String directory = token.nextToken();
                            if (ftpClient.changeWorkingDirectory(directory)) {
                                continue;
                            } else {
                                boolean mkdirSuccess = ftpClient.makeDirectory(directory);
                                boolean changeSuccess = ftpClient.changeWorkingDirectory(directory);
                                if (!mkdirSuccess || !changeSuccess) {
                                    // 如果没有创建目录的权限、没有切换目录的权限，则会失败到这里
                                    success = false;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            return success;
        } catch (IOException e) {
            log.error("【FTP】切换远程目录异常", e);
            throw new FTPException(e);
        }
    }

    private String getVirtualFilename(String originalFilename) {
        FilenameResult regexResult = RegexSupport.matchStrictFilename(originalFilename);
        if (!regexResult.isMatched()) {
            log.error("【FTP】源文件名 {} 不合法", originalFilename);
            throw new FTPException("【FTP】源文件名不合法");
        }
        StringBuilder buffer = new StringBuilder();
        buffer.append(UUID.randomUUID().toString().replace("-", ""));
        if (regexResult.isHasSuffix()) {
            buffer.append(".").append(regexResult.getSuffix());
        }
        return buffer.toString();
    }

    // FTP操作失败时调用，正常情况下不应该调用
    private UploadResultItem getFailureUploadResultItemByReply(
            FTPClient ftpClient, String originalFilename) {
        UploadResultItem resultItem;
        int reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ReplayCode replayCode = ReplayCode.getByCode(reply);
            resultItem = UploadResultItem.newFailure(replayCode);
            resultItem.setOriginalFilename(originalFilename);
        } else {
            // 理论上，这里不应该被执行
            throw new FTPException("【FTP】警告，不应该在FTP操作成功情况下执行[getFailureUploadResultItemByReply]方法");
        }
        return resultItem;
    }

    private UploadResultItem uploadFile(
            FTPClient ftpClient,
            ServerConfig serverConfig,
            String remoteDirectory,
            String originalFilename,
            InputStream inputStream) {
        Assert.notNull(inputStream, "inputStream must be not null");
        UploadResultItem resultItem;
        try {
            if (changeWorkingDirectory(ftpClient, remoteDirectory)) {
                String virtualFilename = getVirtualFilename(originalFilename);
                boolean success = ftpClient.storeFile(virtualFilename, inputStream);
                if (success) {
                    resultItem = UploadResultItem.newSuccess();
                    resultItem.setOriginalFilename(originalFilename);
                    resultItem.setVirtualFilename(virtualFilename);
                    String url =
                            serverConfig.getAccessUrlPrefixes()
                                    + ftpClient.printWorkingDirectory()
                                    + "/"
                                    + virtualFilename;

                    resultItem.setUrl(url);
                } else {
                    resultItem = getFailureUploadResultItemByReply(ftpClient, originalFilename);
                }
            } else {
                resultItem = getFailureUploadResultItemByReply(ftpClient, originalFilename);
            }
        } catch (IOException e) {
            log.error("【FTP】上传文件失败", e);
            resultItem = UploadResultItem.newFailure();
            resultItem.setOriginalFilename(originalFilename);
            resultItem.setErrorMessage(e.getMessage());
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error("【FTP】文件流关闭失败", e);
            }
        }
        return resultItem;
    }

    private static final String IMAGE_DIRECTORY = "images";
    private static final String AUDIO_DIRECTORY = "audios";
    private static final String VEDIO_DIRECTORY = "vedios";

    private String getRemoteDirectory(String originalFilename, UploadParam uploadParam) {
        String remoteDirectory;
        if (uploadParam.isAutoDetect()) {
            if (RegexSupport.matchImage(originalFilename).isMatched()) {
                remoteDirectory = IMAGE_DIRECTORY;
            } else if (RegexSupport.matchAudio(originalFilename).isMatched()) {
                remoteDirectory = AUDIO_DIRECTORY;
            } else if (RegexSupport.matchVedio(originalFilename).isMatched()) {
                remoteDirectory = VEDIO_DIRECTORY;
            } else {
                remoteDirectory = null;
            }
        } else {
            remoteDirectory = uploadParam.getRemoteDirectory();
        }
        return remoteDirectory;
    }

    @Override
    public UploadResult uploadFile(ServerConfig serverConfig, UploadParam uploadParam)
            throws FTPException {
        UploadResult uploadResult = new UploadResult();
        uploadResult.setServerConfig(serverConfig);
        uploadResult.setUploadParam(uploadParam);
        FTPClient ftpClient = null;
        try {
            ftpClient = borrowFTPClient(serverConfig);
            for (Map.Entry<String, InputStream> entry :
                    uploadParam.getInputStreamMap().entrySet()) {
                String remoteDirectory = getRemoteDirectory(entry.getKey(), uploadParam);
                UploadResultItem resultItem =
                        uploadFile(
                                ftpClient,
                                serverConfig,
                                remoteDirectory,
                                entry.getKey(),
                                entry.getValue());
                uploadResult.addResultItem(resultItem);
            }
            for (Map.Entry<String, String> entry : uploadParam.getContentMap().entrySet()) {
                String remoteDirectory = getRemoteDirectory(entry.getKey(), uploadParam);
                InputStream inputStream =
                        new BufferedInputStream(
                                new ByteArrayInputStream(
                                        entry.getValue().getBytes(ftpClient.getCharset())));
                UploadResultItem resultItem =
                        uploadFile(
                                ftpClient,
                                serverConfig,
                                remoteDirectory,
                                entry.getKey(),
                                inputStream);
                uploadResult.addResultItem(resultItem);
            }
            for (MultipartFile multipartFile : uploadParam.getMultipartFileList()) {
                String remoteDirectory =
                        getRemoteDirectory(multipartFile.getOriginalFilename(), uploadParam);
                UploadResultItem resultItem;
                try {
                    resultItem =
                            uploadFile(
                                    ftpClient,
                                    serverConfig,
                                    remoteDirectory,
                                    multipartFile.getOriginalFilename(),
                                    multipartFile.getInputStream());
                } catch (IOException e) {
                    resultItem = UploadResultItem.newFailure();
                    log.error("【FTP】multipartFile文件上传获取输入流失败", e);
                }
                uploadResult.addResultItem(resultItem);
            }
            for (File fileItem : uploadParam.getFileList()) {
                String remoteDirectory = getRemoteDirectory(fileItem.getName(), uploadParam);
                UploadResultItem resultItem;
                try {
                    FileInputStream inputStream = new FileInputStream(fileItem);
                    resultItem =
                            uploadFile(
                                    ftpClient,
                                    serverConfig,
                                    remoteDirectory,
                                    fileItem.getName(),
                                    inputStream);
                } catch (FileNotFoundException e) {
                    resultItem = UploadResultItem.newFailure();
                    log.error("【FTP】fileItem文件上传获取输入流失败", e);
                }
                uploadResult.addResultItem(resultItem);
            }
        } catch (Exception e) {
            log.error("【FTP】上传文件异常", e);
            if (ftpClient != null) {
                invalidateFTPClient(serverConfig, ftpClient);
                // 如果这里不设置为null,finally会执行，且在GenericKeyedObjectPool#returnObject方法中会抛出空指针异常
                ftpClient = null;
            }
        } finally {
            if (ftpClient != null) {
                returnFTPClient(serverConfig, ftpClient);
            }
        }
        return uploadResult;
    }

    // FTP操作失败时调用，正常情况下不应该调用
    private ListResult getFailureListResultByReply(
            FTPClient ftpClient, ServerConfig serverConfig, ListParam listParam) {
        ListResult listResult;
        int reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ReplayCode replayCode = ReplayCode.getByCode(reply);
            listResult = ListResult.newFailure(replayCode);
            listResult.setServerConfig(serverConfig);
            listResult.setListParam(listParam);
        } else {
            // 理论上，这里不应该被执行
            throw new FTPException("【FTP】警告，不应该在FTP操作成功情况下执行[getFailureListResultByReply]方法");
        }
        return listResult;
    }

    private boolean filterFTPFile(FTPFile ftpFile, ListableParam param) {
        if (!StringUtils.isEmpty(param.getPattern())) {
            if (!Pattern.matches(param.getPattern(), ftpFile.getName())) {
                return false;
            }
        }
        if (param.getFilenamePattern() != null) {
            if (!param.getFilenamePattern().matcher(ftpFile.getName()).find()) {
                return false;
            }
        }
        if (param.getFilenameFilter() != null) {
            if (!param.getFilenameFilter().accept(ftpFile.getName())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ListResult listFiles(ServerConfig serverConfig, ListParam listParam)
            throws FTPException {
        ListResult listResult;
        FTPClient ftpClient = null;
        try {
            ftpClient = borrowFTPClient(serverConfig);
            String remoteDirectory = listParam.getRemoteDirectory();
            FTPFile[] ftpFiles;
            if (listParam.getFtpFileFilter() != null) {
                ftpFiles = ftpClient.listFiles(remoteDirectory, listParam.getFtpFileFilter());

            } else {
                ftpFiles = ftpClient.listFiles(remoteDirectory);
            }
            if (ftpFiles != null) {
                listResult = ListResult.newSuccess();
                listResult.setServerConfig(serverConfig);
                listResult.setListParam(listParam);
                for (FTPFile ftpFile : ftpFiles) {
                    if (listParam.getLimit() <= listResult.getFilenameList().size()) {
                        break;
                    }
                    if (filterFTPFile(ftpFile, listParam)) {
                        listResult.addFilename(ftpFile.getName());
                        listResult.addFTPFile(ftpFile);
                    }
                }
            } else {
                listResult = getFailureListResultByReply(ftpClient, serverConfig, listParam);
            }
        } catch (Exception e) {
            log.error("【FTP】查看文件列表异常", e);
            listResult = ListResult.newFailure();
            listResult.setServerConfig(serverConfig);
            listResult.setListParam(listParam);
            listResult.setErrorMessage(e.getMessage());
            if (ftpClient != null) {
                invalidateFTPClient(serverConfig, ftpClient);
                // 如果这里不设置为null,finally会执行，且在GenericKeyedObjectPool#returnObject方法中会抛出空指针异常
                ftpClient = null;
            }
        } finally {
            if (ftpClient != null) {
                returnFTPClient(serverConfig, ftpClient);
            }
        }
        return listResult;
    }

    // FTP操作失败时调用，正常情况下不应该调用
    private DownloadResultItem getFailureDownloadResultItemByReply(FTPClient ftpClient) {
        DownloadResultItem resultItem;
        int reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ReplayCode replayCode = ReplayCode.getByCode(reply);
            resultItem = DownloadResultItem.newFailure(replayCode);
        } else {
            // 理论上，这里不应该被执行
            throw new FTPException("【FTP】警告，不应该在FTP操作成功情况下执行[getFailureUploadResultItemByReply]方法");
        }
        return resultItem;
    }

    // FTP操作失败时调用，正常情况下不应该调用
    private FTPException getFailureFTPExceptionByReply(FTPClient ftpClient) {
        FTPException ftpException;
        int reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ReplayCode replayCode = ReplayCode.getByCode(reply);
            ftpException =
                    new FTPException(
                            String.format("%s:%s", replayCode.getCode(), replayCode.getMsg()));
        } else {
            // 理论上，这里不应该被执行
            throw new FTPException("【FTP】警告，不应该在FTP操作成功情况下执行[getFailureFTPExceptionByReply]方法");
        }
        return ftpException;
    }

    @Override
    public DownloadResult downloadFile(ServerConfig serverConfig, DownloadParam downloadParam)
            throws FTPException {
        DownloadResult downloadResult = new DownloadResult();
        downloadResult.setServerConfig(serverConfig);
        downloadResult.setDownloadParam(downloadParam);
        FTPClient ftpClient = null;
        try {
            ftpClient = borrowFTPClient(serverConfig);
            String remoteDirectory = downloadParam.getRemoteDirectory();
            if (StringUtils.isEmpty(downloadParam.getLocalDirectory())) {
                throw new FTPException("【FTP】localDirectory不能为空，请指定下载目录");
            }
            File localDirectory = new File(downloadParam.getLocalDirectory());
            if (!localDirectory.exists()) {
                localDirectory.mkdirs();
            }
            FTPFile[] ftpFiles;
            if (downloadParam.getFtpFileFilter() != null) {
                ftpFiles = ftpClient.listFiles(remoteDirectory, downloadParam.getFtpFileFilter());
            } else {
                ftpFiles = ftpClient.listFiles(remoteDirectory);
            }
            if (changeWorkingDirectory(ftpClient, remoteDirectory)) {
                if (ftpFiles != null) {
                    for (FTPFile ftpFile : ftpFiles) {
                        if (filterFTPFile(ftpFile, downloadParam)) {
                            OutputStream outputStream = null;
                            try {
                                DownloadResultItem resultItem;
                                outputStream =
                                        new FileOutputStream(
                                                new File(localDirectory, ftpFile.getName()));
                                if (ftpClient.retrieveFile(ftpFile.getName(), outputStream)) {
                                    resultItem = DownloadResultItem.newSuccess();
                                    resultItem.setFilename(ftpFile.getName());
                                } else {
                                    resultItem = getFailureDownloadResultItemByReply(ftpClient);
                                    resultItem.setFilename(ftpFile.getName());
                                }
                                downloadResult.addResultItem(resultItem);
                            } finally {
                                if (outputStream != null) {
                                    outputStream.close();
                                }
                            }
                        }
                    }
                } else {
                    // 实际上，是不会存在ftpFiles=null的，会返回空数组，此处实际不可达
                    log.info("【FTP】目录 {} 不存在满足条件的文件", remoteDirectory);
                }
            } else {
                throw getFailureFTPExceptionByReply(ftpClient);
            }
        } catch (Exception e) {
            log.error("【FTP】删除文件异常", e);
            if (ftpClient != null) {
                invalidateFTPClient(serverConfig, ftpClient);
                // 如果这里不设置为null,finally会执行，且在GenericKeyedObjectPool#returnObject方法中会抛出空指针异常
                ftpClient = null;
            }
        } finally {
            if (ftpClient != null) {
                returnFTPClient(serverConfig, ftpClient);
            }
        }
        return downloadResult;
    }

    @Override
    public <T> T downloadFile(ServerConfig serverConfig, FTPCallback<T> callback)
            throws FTPException {
        return null;
    }
    // FTP操作失败时调用，正常情况下不应该调用
    private DeleteResultItem getFailureDeleteResultItemByReply(FTPClient ftpClient) {
        DeleteResultItem resultItem;
        int reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ReplayCode replayCode = ReplayCode.getByCode(reply);
            resultItem = DeleteResultItem.newFailure(replayCode);
        } else {
            // 理论上，这里不应该被执行
            throw new FTPException("【FTP】警告，不应该在FTP操作成功情况下执行[getFailureUploadResultItemByReply]方法");
        }
        return resultItem;
    }

    @Override
    public DeleteResult deleteFile(ServerConfig serverConfig, DeleteParam deleteParam)
            throws FTPException {
        DeleteResult deleteResult = new DeleteResult();
        deleteResult.setServerConfig(serverConfig);
        deleteResult.setDeleteParam(deleteParam);
        FTPClient ftpClient = null;
        try {
            ftpClient = borrowFTPClient(serverConfig);
            String remoteDirectory = deleteParam.getRemoteDirectory();
            FTPFile[] ftpFiles;
            if (deleteParam.getFtpFileFilter() != null) {
                ftpFiles = ftpClient.listFiles(remoteDirectory, deleteParam.getFtpFileFilter());

            } else {
                ftpFiles = ftpClient.listFiles(remoteDirectory);
            }
            if (ftpFiles != null) {
                for (FTPFile ftpFile : ftpFiles) {
                    if (filterFTPFile(ftpFile, deleteParam)) {
                        DeleteResultItem resultItem;
                        if (ftpClient.deleteFile(ftpFile.getName())) {
                            resultItem = DeleteResultItem.newSuccess();
                            resultItem.setFilename(ftpFile.getName());
                        } else {
                            resultItem = getFailureDeleteResultItemByReply(ftpClient);
                            resultItem.setFilename(ftpFile.getName());
                        }
                        deleteResult.addResultItem(resultItem);
                    }
                }
            } else {
                DeleteResultItem resultItem = getFailureDeleteResultItemByReply(ftpClient);
            }
        } catch (Exception e) {
            log.error("【FTP】删除文件异常", e);
            if (ftpClient != null) {
                invalidateFTPClient(serverConfig, ftpClient);
                // 如果这里不设置为null,finally会执行，且在GenericKeyedObjectPool#returnObject方法中会抛出空指针异常
                ftpClient = null;
            }
        } finally {
            if (ftpClient != null) {
                returnFTPClient(serverConfig, ftpClient);
            }
        }
        return deleteResult;
    }
}
