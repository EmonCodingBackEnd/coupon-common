/*
 * 文件名称：HttpAsyncClient.java
 * 系统名称：[系统名称]
 * 模块名称：Http异步
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180607 07:48
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180607-01         Rushing0711     M201806070748 新建文件
 ********************************************************************************/
package com.coding.coupon.common.http.client;

import com.coding.coupon.common.http.HttpConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.IOReactorException;

/**
 * Http异步.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180607 07:48</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
public class HttpAsyncClient {

    private static final CloseableHttpAsyncClient asyncHttpClient;

    static {
        DefaultConnectingIOReactor ioReactor = null;
        try {
            ioReactor =
                    new DefaultConnectingIOReactor(
                            IOReactorConfig.custom().setSoKeepAlive(true).build());
        } catch (IOReactorException e) {
            log.error("【Http异步】初始化失败", e);
        }

        PoolingNHttpClientConnectionManager poolingNConnMgr =
                new PoolingNHttpClientConnectionManager(ioReactor);
        poolingNConnMgr.setMaxTotal(HttpConfig.DEFAULT_MAX_TOTAL);
        poolingNConnMgr.setDefaultMaxPerRoute(HttpConfig.DEFAULT_MAX_PER_ROUTE);
        poolingNConnMgr.setDefaultConnectionConfig(HttpConfig.defaultConnectionConfig);

        asyncHttpClient =
                HttpAsyncClients.custom()
                        .setThreadFactory(
                                new BasicThreadFactory.Builder()
                                        .namingPattern("AysncHttpThread-%d")
                                        .build())
                        .setConnectionManager(poolingNConnMgr)
                        //
                        // .setDefaultRequestConfig(HttpConfig.defaultRequestConfig)
                        .setKeepAliveStrategy(HttpConfig.defaultConnectionStrategy)
                        .setUserAgent(HttpConfig.userAgent)
                        .build();
        asyncHttpClient.start();
    }

    public static CloseableHttpAsyncClient getInstance() {
        return asyncHttpClient;
    }
}
