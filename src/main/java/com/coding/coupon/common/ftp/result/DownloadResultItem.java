package com.coding.coupon.common.ftp.result;

import com.coding.coupon.common.ftp.property.ReplayCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class DownloadResultItem extends BaseResult {
    private static String successMsg = "下载成功";
    private static String failureMsg = "下载失败";

    private String filename;

    private DownloadResultItem() {}

    public static DownloadResultItem newSuccess() {
        DownloadResultItem item = new DownloadResultItem();
        item.setSuccess(true);
        item.setErrorCode(successCode);
        item.setErrorMessage(successMsg);
        return item;
    }

    public static DownloadResultItem newFailure() {
        DownloadResultItem item = new DownloadResultItem();
        item.setSuccess(false);
        item.setErrorCode(failureCode);
        item.setErrorMessage(failureMsg);
        return item;
    }

    public static DownloadResultItem newFailure(ReplayCode replayCode) {
        DownloadResultItem item = new DownloadResultItem();
        item.setSuccess(false);
        item.setErrorCode(replayCode.getCode());
        item.setErrorMessage(replayCode.getMsg());
        return item;
    }
}
