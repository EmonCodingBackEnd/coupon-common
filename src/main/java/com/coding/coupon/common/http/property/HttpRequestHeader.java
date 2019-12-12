/*
 * 文件名称：HttpHeader.java
 * 系统名称：[系统名称]
 * 模块名称：Http请求头属性定义
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180608 14:16
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180608-01         Rushing0711     M201806081416 新建文件
 ********************************************************************************/
package com.coding.coupon.common.http.property;

import org.springframework.http.HttpHeaders;

/**
 * Http请求头属性定义.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180608 14:17</font><br>
 * 为什么要创建这个类，因为无论Apache、SpringFramework还是Google提供的HttpHeaders都有包含不到的属性。
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public class HttpRequestHeader extends HttpHeaders {

    /** 非标准Http请求头 */
    public static final String KEEP_ALIVE = "Keep-Alive";
}
