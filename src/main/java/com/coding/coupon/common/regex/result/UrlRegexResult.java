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
public class UrlRegexResult extends RegexResult {
    private String url;
    private String uri;
    private String schema;
    private String path;
    private String param;

    public static UrlRegexResult instance() {
        return new UrlRegexResult();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
