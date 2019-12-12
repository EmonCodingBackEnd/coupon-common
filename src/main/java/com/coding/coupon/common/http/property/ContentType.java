/*
 * 文件名称：ContentType.java
 * 系统名称：[系统名称]
 * 模块名称：Http的ContentType定义
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180609 10:26
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180609-01         Rushing0711     M201806091026 新建文件
 ********************************************************************************/
package com.coding.coupon.common.http.property;

/**
 * Http的ContentType定义.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180609 10:26</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class ContentType {

    /** Http请求发送服务器之前的编码指定属性：在发送前编码所有字符（默认）. */
    public static final String APPLICATION_FORM_URLENCODED_VALUE =
            "application/x-www-form-urlencoded";

    /** Http请求发送服务器之前的编码指定属性：不对字符编码，在使用包含文件上传控件的表单时，必须使用该值. */
    public static final String MULTIPART_FORM_DATA_VALUE = "multipart/form-data";

    /** Http请求发送服务器之前的编码指定属性：空格转换为 "+" 号，但不对特殊字符编码. */
    public static final String TEXT_PLAIN_VALUE = "text/plain";

    public static final String APPLICATION_JSON_VALUE = "application/json";

    public static final String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";

    public static final String APPLICATION_XML_VALUE = "application/xml";

    public static final String APPLICATION_XML_UTF8_VALUE = "application/xml;charset=UTF-8";

    public static final String APPLICATION_XHTML_XML_VALUE = "application/xhtml+xml";

    public static final String TEXT_HTML_VALUE = "text/html";

    public static final String TEXT_JSON_VALUE = "text/json";

    public static final String TEXT_XML_VALUE = "text/xml";

    public static final String TEXT_MARKDOWN_VALUE = "text/markdown";
}
