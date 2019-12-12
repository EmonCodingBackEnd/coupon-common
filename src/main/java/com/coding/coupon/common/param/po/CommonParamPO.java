package com.coding.coupon.common.param.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/** 通用参数管理. */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class CommonParamPO implements BaseParamPO {

    private static final long serialVersionUID = -7122172399197025982L;
    @JsonIgnore
    private boolean enabled;

    /** 是否显示线上教培. */
    private Integer showOnlineEdu;
}
