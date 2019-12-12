package com.coding.coupon.common.ftp.result;

import com.coding.coupon.common.ftp.property.ReplayCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class UploadResultItem extends BaseResult {
    private static String successMsg = "上传成功";
    private static String failureMsg = "上传失败";

    private String originalFilename;
    private String virtualFilename;
    private String url;

    private UploadResultItem() {}

    public static UploadResultItem newSuccess() {
        UploadResultItem item = new UploadResultItem();
        item.setSuccess(true);
        item.setErrorCode(successCode);
        item.setErrorMessage(successMsg);
        return item;
    }

    public static UploadResultItem newFailure() {
        UploadResultItem item = new UploadResultItem();
        item.setSuccess(false);
        item.setErrorCode(failureCode);
        item.setErrorMessage(failureMsg);
        return item;
    }

    public static UploadResultItem newFailure(ReplayCode replayCode) {
        UploadResultItem item = new UploadResultItem();
        item.setSuccess(false);
        item.setErrorCode(replayCode.getCode());
        item.setErrorMessage(replayCode.getMsg());
        return item;
    }
}
