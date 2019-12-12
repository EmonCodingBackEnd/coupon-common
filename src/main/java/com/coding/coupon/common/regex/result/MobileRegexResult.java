/*
 * 文件名称：MobileRegexResult.java
 * 系统名称：[系统名称]
 * 模块名称：手机号码正则表达式匹配结果子类
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180424 18:18
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180424-01         Rushing0711     M201804241818 新建文件
 ********************************************************************************/
package com.coding.coupon.common.regex.result;

import com.coding.coupon.common.regex.RegexResult;

/**
 * 手机号码正则表达式匹配结果子类.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180424 18:09</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public class MobileRegexResult extends RegexResult {

    /** 匹配的手机号. */
    private String mobile;

    /** 手机号前3位数字. */
    private String mobileHead;

    /** 手机号后4位数字. */
    private String mobileTail;

    public static MobileRegexResult instance() {
        return new MobileRegexResult();
    }

    private MobileRegexResult() {}

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobileHead() {
        return mobileHead;
    }

    public void setMobileHead(String mobileHead) {
        this.mobileHead = mobileHead;
    }

    public String getMobileTail() {
        return mobileTail;
    }

    public void setMobileTail(String mobileTail) {
        this.mobileTail = mobileTail;
    }
}
