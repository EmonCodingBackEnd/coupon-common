package com.coding.coupon.common.ftp.result;

import lombok.Data;

@Data
public class BaseResult {
    protected static Integer successCode = 2000;
    protected static String successMsg = "操作成功";
    protected static Integer failureCode = 9999;
    protected static String failureMsg = "操作失败";

    private boolean success;
    private Integer errorCode;
    private String errorMessage;
}
