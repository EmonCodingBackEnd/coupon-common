package com.coding.coupon.common.ftp.result;

import com.coding.coupon.common.ftp.property.ReplayCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class DeleteResultItem extends BaseResult {
    private static String successMsg = "删除成功";
    private static String failureMsg = "删除失败";

    private String filename;

    private DeleteResultItem() {}

    public static DeleteResultItem newSuccess() {
        DeleteResultItem item = new DeleteResultItem();
        item.setSuccess(true);
        item.setErrorCode(successCode);
        item.setErrorMessage(successMsg);
        return item;
    }

    public static DeleteResultItem newFailure() {
        DeleteResultItem item = new DeleteResultItem();
        item.setSuccess(false);
        item.setErrorCode(failureCode);
        item.setErrorMessage(failureMsg);
        return item;
    }

    public static DeleteResultItem newFailure(ReplayCode replayCode) {
        DeleteResultItem item = new DeleteResultItem();
        item.setSuccess(false);
        item.setErrorCode(replayCode.getCode());
        item.setErrorMessage(replayCode.getMsg());
        return item;
    }
}
