/*
 * 文件名称：ClientSupport.java
 * 系统名称：[系统名称]
 * 模块名称：定义常用的Http客户端
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180609 11:42
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180609-01         Rushing0711     M201806091142 新建文件
 ********************************************************************************/
package com.coding.coupon.common.http.support;

import com.coding.coupon.common.http.generator.HttpSyncClientGenerator;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * 定义常用的Http客户端.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180609 11:43</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class ClientSupport {

    public static final CloseableHttpClient httpSyncClient;

    public static final CloseableHttpClient httpSyncRetryClient;

    //    public static final CloseableHttpClient httpSyncWechatClient;

    static {
        httpSyncClient = HttpSyncClientGenerator.custom().pool().timeout().build();
        httpSyncRetryClient = HttpSyncClientGenerator.custom().pool().timeout().retry().build();
        /*httpSyncWechatClient =
        HttpSyncClientGenerator.custom()
                .ssl(
                        "PKCS12",
                        ConstantDefinition.C_MP.CERTLOCALPATH,
                        ConstantDefinition.C_MP.CERTPASSWORD)
                .pool()
                .timeout()
                .build();*/
    }
}
