package com.coding.coupon.common.param;

import com.coding.coupon.common.JsonCustomSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;

@Data
public class ParamItem implements Serializable {

    private static final long serialVersionUID = 6380139070220445901L;

    @JsonSerialize(using = JsonCustomSerializer.Long2StringSerializer.class)
    private Long id;

    /** 系统参数代码 */
    private String paramCode;

    /** 系统参数值 */
    private String paramValue;

    /** 系统参数的描述 */
    private String paramDesc;

    /** 是否展示 */
    private Integer showable;

    /** 是否启用 */
    private Integer enabled;
}
