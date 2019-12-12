package com.coding.coupon.common.param.po;

import java.io.Serializable;

public interface BaseParamPO extends Serializable {

    /** 参数是否启用. */
    boolean isEnabled();

    void setEnabled(boolean enabled);
}
