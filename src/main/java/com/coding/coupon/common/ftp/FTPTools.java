/*
 * 文件名称：FTPTools.java
 * 系统名称：[系统名称]
 * 模块名称：FTP工具类
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180621 00:44
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180621-01         Rushing0711     M201806210044 新建文件
 ********************************************************************************/
package com.coding.coupon.common.ftp;

import com.coding.coupon.common.ftp.config.FTPConfig;
import com.coding.coupon.common.ftp.param.*;
import com.coding.coupon.common.ftp.pool.GenericKeyedFTPClientPool;
import com.coding.coupon.common.ftp.result.DeleteResult;
import com.coding.coupon.common.ftp.result.DownloadResult;
import com.coding.coupon.common.ftp.result.ListResult;
import com.coding.coupon.common.ftp.result.UploadResult;
import com.coding.coupon.common.ftp.template.FTPTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * FTP工具类.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180621 00:44</font><br>
 * 我支持的比你看到的多的多，扩展起来也不麻烦，需要了直接联系我。<br>
 * 更强大的用法请看 ftpTemplate
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
@ConditionalOnBean(GenericKeyedFTPClientPool.class)
@Slf4j
public class FTPTools {
    private static FTPConfig ftpConfig;
    private static FTPTemplate ftpTemplate;

    @Autowired
    public FTPTools(FTPConfig ftpConfig, FTPTemplate ftpTemplate) {
        FTPTools.ftpConfig = ftpConfig;
        FTPTools.ftpTemplate = ftpTemplate;
    }

    public static UploadResult uploadFileAutoDetectDirectory(String filename, String fileContent) {
        UploadParam uploadParam =
                UploadParamBuilder.custom().autoDetect(true).content(filename, fileContent).build();
        return ftpTemplate.uploadFile(ftpConfig.getDefaultServer(), uploadParam);
    }

    public static UploadResult uploadFileAutoDetectDirectory(
            List<MultipartFile> multipartFileList) {
        UploadParam uploadParam =
                UploadParamBuilder.custom()
                        .autoDetect(true)
                        .multipartFileList(multipartFileList)
                        .build();
        return ftpTemplate.uploadFile(ftpConfig.getDefaultServer(), uploadParam);
    }

    public static UploadResult uploadFileAutoDetectDirectory(MultipartFile multipartFile) {
        UploadParam uploadParam =
                UploadParamBuilder.custom().autoDetect(true).multipartFile(multipartFile).build();
        return ftpTemplate.uploadFile(ftpConfig.getDefaultServer(), uploadParam);
    }

    public static UploadResult uploadFileAutoDetectDirectory(File file) {
        UploadParam uploadParam = UploadParamBuilder.custom().autoDetect(true).file(file).build();
        return ftpTemplate.uploadFile(ftpConfig.getDefaultServer(), uploadParam);
    }

    public static UploadResult uploadFile(
            String remoteDirectory, String filename, String fileContent) {
        UploadParam uploadParam =
                UploadParamBuilder.custom()
                        .remoteDirectory(remoteDirectory)
                        .content(filename, fileContent)
                        .build();
        return ftpTemplate.uploadFile(ftpConfig.getDefaultServer(), uploadParam);
    }

    public static UploadResult uploadFile(String remoteDirectory, MultipartFile multipartFile) {
        UploadParam uploadParam =
                UploadParamBuilder.custom()
                        .remoteDirectory(remoteDirectory)
                        .multipartFile(multipartFile)
                        .build();
        return ftpTemplate.uploadFile(ftpConfig.getDefaultServer(), uploadParam);
    }

    public static ListResult listFiles(String remoteDirectory, FTPFileFilter ftpFileFilter) {
        ListParam listParam =
                ListParamBuilder.custom()
                        .remoteDirectory(remoteDirectory)
                        .filter(ftpFileFilter)
                        .build();
        return ftpTemplate.listFiles(ftpConfig.getDefaultServer(), listParam);
    }

    public static DownloadResult downloadFile(String remoteDirectory, FTPFileFilter ftpFileFilter) {
        DownloadParam downloadParam =
                DownloadParamBuilder.custom()
                        .remoteDirectory(remoteDirectory)
                        .filter(ftpFileFilter)
                        .build();
        return ftpTemplate.downloadFile(ftpConfig.getDefaultServer(), downloadParam);
    }

    public static DeleteResult deleteFile(String remoteDirectory, FTPFileFilter ftpFileFilter) {
        DeleteParam deleteParam =
                DeleteParamBuilder.custom()
                        .remoteDirectory(remoteDirectory)
                        .filter(ftpFileFilter)
                        .build();
        return ftpTemplate.deleteFile(ftpConfig.getDefaultServer(), deleteParam);
    }
}
