/*
 * 文件名称：UrlRegResult.java
 * 系统名称：[系统名称]
 * 模块名称：Url正则匹配结果
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180611 03:08
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180611-01         Rushing0711     M201806110308 新建文件
 ********************************************************************************/
package com.coding.coupon.common.regex.result;

import com.coding.coupon.common.regex.RegexResult;

/**
 * Url正则匹配结果.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180611 03:08</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public class UrlParamRegexResult extends RegexResult {
    private String param;

    public static UrlParamRegexResult instance() {
        return new UrlParamRegexResult();
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
