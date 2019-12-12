/*
 * 文件名称：HeaderSupport.java
 * 系统名称：[系统名称]
 * 模块名称：定义常用的Http请求头
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180609 10:17
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180609-01         Rushing0711     M201806091017 新建文件
 ********************************************************************************/
package com.coding.coupon.common.http.support;

import com.coding.coupon.common.http.generator.HeaderGeneretor;
import com.coding.coupon.common.http.property.ContentType;
import org.apache.http.Header;
import org.apache.http.protocol.HTTP;

/**
 * 定义常用的Http请求头.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180609 10:18</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class HeaderSupport {
    /**
     * 长连接请求头：Content-Type: application/x-www-form-urlencoded.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180609 10:20</font><br>
     *
     * <p>数据被编码为<key,value>名/值对，标准的编码格式
     *
     * @since 1.0.0
     */
    public static final Header[] KEEP_ALIVE_ENCODED_HEADERS =
            HeaderGeneretor.custom()
                    .keepAlive("300") // 浏览器请求保持300s
                    .connection(HTTP.CONN_KEEP_ALIVE)
                    .contentType(ContentType.APPLICATION_FORM_URLENCODED_VALUE)
                    .build();

    /**
     * 短连接请求头：Content-Type: application/x-www-form-urlencoded.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180609 13:31</font><br>
     * [请在此输入功能详述]
     *
     * @since 1.0.0
     */
    public static final Header[] CLOSE_ENCODED_HEADERS =
            HeaderGeneretor.custom()
                    .keepAlive("false")
                    .connection(HTTP.CONN_CLOSE)
                    .contentType(ContentType.APPLICATION_FORM_URLENCODED_VALUE)
                    .build();

    /**
     * Json数据格式的长连接请求头：Content-Type: application/json.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180609 10:22</font><br>
     * [请在此输入功能详述]
     *
     * @since 1.0.0
     */
    public static Header[] KEEP_ALIVE_JSON_HEADERS =
            HeaderGeneretor.custom()
                    .keepAlive("300") // 浏览器请求保持300s
                    .connection(HTTP.CONN_KEEP_ALIVE)
                    .contentType(ContentType.APPLICATION_JSON_VALUE)
                    .build();

    /**
     * Json数据格式的长连接请求头：Content-Type: application/json;charset=UTF-8.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180609 11:01</font><br>
     * [请在此输入功能详述]
     *
     * @since 1.0.0
     */
    public static Header[] KEEP_ALIVE_JSON_UTF8_HEADERS =
            HeaderGeneretor.custom()
                    .keepAlive("300") // 浏览器请求保持300s
                    .connection(HTTP.CONN_KEEP_ALIVE)
                    .contentType(ContentType.APPLICATION_JSON_UTF8_VALUE)
                    .build();

    /**
     * Xml数据格式的长连接请求头：ContentType: application/xml.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180609 10:58</font><br>
     * [请在此输入功能详述]
     *
     * @since 1.0.0
     */
    public static Header[] KEEP_ALIVE_XML_HEADERS =
            HeaderGeneretor.custom()
                    .keepAlive("300") // 浏览器请求保持300s
                    .connection(HTTP.CONN_KEEP_ALIVE)
                    .contentType(ContentType.APPLICATION_XML_VALUE)
                    .build();

    /**
     * Xml数据格式的长连接请求头：ContentType: application/xml;charset=UTF-8.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180609 11:03</font><br>
     * [请在此输入功能详述]
     *
     * @since 1.0.0
     */
    public static Header[] KEEP_ALIVE_XML_UTF8_HEADERS =
            HeaderGeneretor.custom()
                    .keepAlive("300") // 浏览器请求保持300s
                    .connection(HTTP.CONN_KEEP_ALIVE)
                    .contentType(ContentType.APPLICATION_XML_UTF8_VALUE)
                    .build();
}
