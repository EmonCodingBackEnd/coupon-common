package com.coding.coupon.common.ftp.param;

import com.coding.coupon.common.ftp.filter.FilenameFilter;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.springframework.util.Assert;

import java.util.regex.Pattern;

public class DeleteParamBuilder {

    private DeleteParam deleteParam;

    private DeleteParamBuilder() {
        deleteParam = new DeleteParam();
    }

    public static DeleteParamBuilder custom() {
        return new DeleteParamBuilder();
    }

    public DeleteParamBuilder remoteDirectory(String remoteDirectory) {
        Assert.notNull(remoteDirectory, "remoteDirectory must be not null");
        this.deleteParam.setRemoteDirectory(remoteDirectory);
        return this;
    }

    public DeleteParamBuilder pattern(String pattern) {
        Assert.notNull(pattern, "pattern must be not null");
        this.deleteParam.setPattern(pattern);
        return this;
    }

    public DeleteParamBuilder pattern(Pattern filenamePattern) {
        Assert.notNull(filenamePattern, "filenamePattern must be not null");
        this.deleteParam.setFilenamePattern(filenamePattern);
        return this;
    }

    public DeleteParamBuilder filter(FilenameFilter filenameFilter) {
        Assert.notNull(filenameFilter, "filenameFilter must be not null");
        this.deleteParam.setFilenameFilter(filenameFilter);
        return this;
    }

    public DeleteParamBuilder filter(FTPFileFilter ftpFileFilter) {
        Assert.notNull(ftpFileFilter, "ftpFileFilter must be not null");
        this.deleteParam.setFtpFileFilter(ftpFileFilter);
        return this;
    }

    public DeleteParam build() {
        return deleteParam;
    }
}
