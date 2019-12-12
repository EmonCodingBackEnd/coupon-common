package com.coding.coupon.common.ftp.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class ListParam extends ListableParam {
    private int limit = Integer.MAX_VALUE;
}
