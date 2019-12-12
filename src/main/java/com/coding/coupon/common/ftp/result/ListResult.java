package com.coding.coupon.common.ftp.result;

import com.coding.coupon.common.ftp.config.ServerConfig;
import com.coding.coupon.common.ftp.param.ListParam;
import com.coding.coupon.common.ftp.property.ReplayCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.net.ftp.FTPFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class ListResult extends BaseResult {
    private static String successMsg = "列表查询成功";
    private static String failureMsg = "列表查询失败";

    private ServerConfig serverConfig;
    private ListParam listParam;

    private List<String> filenameList = new ArrayList<>();
    private List<FTPFile> ftpFileList = new ArrayList<>();

    public static ListResult newSuccess() {
        ListResult listResult = new ListResult();
        listResult.setSuccess(true);
        listResult.setErrorCode(successCode);
        listResult.setErrorMessage(successMsg);
        return listResult;
    }

    public static ListResult newFailure() {
        ListResult listResult = new ListResult();
        listResult.setSuccess(false);
        listResult.setErrorCode(failureCode);
        listResult.setErrorMessage(failureMsg);
        return listResult;
    }

    public static ListResult newFailure(ReplayCode replayCode) {
        ListResult listResult = new ListResult();
        listResult.setSuccess(false);
        listResult.setErrorCode(replayCode.getCode());
        listResult.setErrorMessage(replayCode.getMsg());
        return listResult;
    }

    public void addFilename(String filename) {
        this.filenameList.add(filename);
    }

    public void addFilenameAll(String[] filenames) {
        this.filenameList.addAll(Arrays.asList(filenames));
    }

    public void addFilenameAll(List<String> filenameList) {
        this.filenameList.addAll(filenameList);
    }

    public void addFTPFile(FTPFile ftpFile) {
        this.ftpFileList.add(ftpFile);
    }

    public void addFTPFile(List<FTPFile> ftpFileList) {
        this.ftpFileList.addAll(ftpFileList);
    }
}
