package com.coding.coupon.common.ftp.param;

import com.coding.coupon.common.ftp.filter.FilenameFilter;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.springframework.util.Assert;

import java.util.regex.Pattern;

public class ListParamBuilder {

    private ListParam listParam;

    private ListParamBuilder() {
        listParam = new ListParam();
    }

    public static ListParamBuilder custom() {
        return new ListParamBuilder();
    }

    public ListParamBuilder remoteDirectory(String remoteDirectory) {
        Assert.notNull(remoteDirectory, "remoteDirectory must be not null");
        this.listParam.setRemoteDirectory(remoteDirectory);
        return this;
    }

    public ListParamBuilder pattern(String pattern) {
        Assert.notNull(pattern, "pattern must be not null");
        this.listParam.setPattern(pattern);
        return this;
    }

    public ListParamBuilder pattern(Pattern filenamePattern) {
        Assert.notNull(filenamePattern, "filenamePattern must be not null");
        this.listParam.setFilenamePattern(filenamePattern);
        return this;
    }

    public ListParamBuilder filter(FilenameFilter filenameFilter) {
        Assert.notNull(filenameFilter, "filenameFilter must be not null");
        this.listParam.setFilenameFilter(filenameFilter);
        return this;
    }

    public ListParamBuilder filter(FTPFileFilter ftpFileFilter) {
        Assert.notNull(ftpFileFilter, "ftpFileFilter must be not null");
        this.listParam.setFtpFileFilter(ftpFileFilter);
        return this;
    }

    public ListParamBuilder limit(int limit) {
        if (limit > 0) {
            this.listParam.setLimit(limit);
        }
        return this;
    }

    public ListParam build() {
        return listParam;
    }
}
