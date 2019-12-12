package com.coding.coupon.common.ftp.param;

import com.coding.coupon.common.ftp.filter.FilenameFilter;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.springframework.util.Assert;

import java.util.regex.Pattern;

public class DownloadParamBuilder {

    private DownloadParam downloadParam;

    private DownloadParamBuilder() {
        downloadParam = new DownloadParam();
    }

    public static DownloadParamBuilder custom() {
        return new DownloadParamBuilder();
    }

    public DownloadParamBuilder remoteDirectory(String remoteDirectory) {
        Assert.notNull(remoteDirectory, "remoteDirectory must be not null");
        this.downloadParam.setRemoteDirectory(remoteDirectory);
        return this;
    }

    public DownloadParamBuilder localDirectory(String localDirectory) {
        Assert.notNull(localDirectory, "localDirectory must be not null");
        this.downloadParam.setLocalDirectory(localDirectory);
        return this;
    }

    public DownloadParamBuilder pattern(String pattern) {
        Assert.notNull(pattern, "pattern must be not null");
        this.downloadParam.setPattern(pattern);
        return this;
    }

    public DownloadParamBuilder pattern(Pattern filenamePattern) {
        Assert.notNull(filenamePattern, "filenamePattern must be not null");
        this.downloadParam.setFilenamePattern(filenamePattern);
        return this;
    }

    public DownloadParamBuilder filter(FilenameFilter filenameFilter) {
        Assert.notNull(filenameFilter, "filenameFilter must be not null");
        this.downloadParam.setFilenameFilter(filenameFilter);
        return this;
    }

    public DownloadParamBuilder filter(FTPFileFilter ftpFileFilter) {
        Assert.notNull(ftpFileFilter, "ftpFileFilter must be not null");
        this.downloadParam.setFtpFileFilter(ftpFileFilter);
        return this;
    }

    public DownloadParam build() {
        return downloadParam;
    }
}
