package com.coding.coupon.common.ftp.param;

import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class UploadParamBuilder {

    private UploadParam uploadParam;

    private UploadParamBuilder() {
        uploadParam = new UploadParam();
    }

    public static UploadParamBuilder custom() {
        return new UploadParamBuilder();
    }

    public UploadParamBuilder remoteDirectory(String remoteDirectory) {
        Assert.notNull(remoteDirectory, "remoteDirectory must be not null");
        this.uploadParam.setRemoteDirectory(remoteDirectory);
        return this;
    }

    public UploadParamBuilder autoDetect(boolean autoDetect) {
        this.uploadParam.setAutoDetect(autoDetect);
        return this;
    }

    public UploadParamBuilder fileList(List<File> fileList) {
        Assert.notNull(fileList, "fileList must be not null");
        this.uploadParam.getFileList().addAll(fileList);
        return this;
    }

    public UploadParamBuilder file(File file) {
        Assert.notNull(file, "file must be not null");
        this.uploadParam.getFileList().add(file);
        return this;
    }

    public UploadParamBuilder multipartFileList(List<MultipartFile> multipartFileList) {
        Assert.notNull(multipartFileList, "multipartFileList must be not null");
        this.uploadParam.getMultipartFileList().addAll(multipartFileList);
        return this;
    }

    public UploadParamBuilder multipartFile(MultipartFile multipartFile) {
        Assert.notNull(multipartFile, "multipartFile must be not null");
        this.uploadParam.getMultipartFileList().add(multipartFile);
        return this;
    }

    public UploadParamBuilder contentMap(Map<String, String> contentMap) {
        Assert.notNull(contentMap, "contentMap must be not null");
        this.uploadParam.getContentMap().putAll(contentMap);
        return this;
    }

    public UploadParamBuilder content(String filename, String filecontent) {
        Assert.notNull(filename, "filename must be not null");
        Assert.notNull(filecontent, "filecontent must be not null");
        this.uploadParam.getContentMap().put(filename, filecontent);
        return this;
    }

    public UploadParamBuilder inputStreamMap(Map<String, InputStream> inputStreamMap) {
        Assert.notNull(inputStreamMap, "inputStreamMap must be not null");
        this.uploadParam.getInputStreamMap().putAll(inputStreamMap);
        return this;
    }

    public UploadParamBuilder inputStream(String key, InputStream inputStream) {
        Assert.notNull(key, "key must be not null");
        Assert.notNull(inputStream, "inputStream must be not null");
        this.uploadParam.getInputStreamMap().put(key, inputStream);
        return this;
    }

    public UploadParam build() {
        return uploadParam;
    }
}
