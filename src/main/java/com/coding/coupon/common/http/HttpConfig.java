/*
 * 文件名称：HttpConfig.java
 * 系统名称：[系统名称]
 * 模块名称：Http配置参数
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180607 21:58
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180607-01         Rushing0711     M201806072158 新建文件
 ********************************************************************************/
package com.coding.coupon.common.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpHost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Http配置参数.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180607 21:58</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
public class HttpConfig {

    public static final String userAgent = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)";

    /*
     * 路由(MAX_PER_ROUTE)是对最大连接数(MAX_TOTAL)的细分，
     * 整个连接池的限制数量实际使用DefaultMaxPerRoute并非是MaxTotal。
     * 设置过小无法支持大并发(ConnectionPoolTimeoutException: Timeout waiting for connection from pool)
     */
    /** 最大支持的连接数. */
    public static final int DEFAULT_MAX_TOTAL = 1000;
    /** 针对某一个域名的最大连接数. */
    public static final int DEFAULT_MAX_PER_ROUTE = 128;

    /** 空闲永久连接检查间隔：官方推荐使用这个来检查永久链接的可用性，而不推荐每次请求的时候才去检查 */
    public static final int DEFAULT_VALIDATE_AFTER_INACTIVITY = 2000;

    /** 跟目标服务建立连接超时时间，根据业务情况而定. */
    public static final int DEFAULT_CONNECTION_TIMEOUT = 3000;
    /** 请求的超时时间(建立连接后，等待response返回的时间). */
    public static final int DEFAULT_SOCKET_TIMEOUT = 5000;
    /** 从连接池中获取连接的超时时间. */
    public static final int DEFAULT_TIMEOUT = 3000;

    /** 默认连接配置. */
    public static final ConnectionConfig defaultConnectionConfig;

    /** 默认链接保持策略. */
    public static final ConnectionKeepAliveStrategy defaultConnectionStrategy;

    /** 默认重试处理器. */
    public static final DefaultHttpRequestRetryHandler defaultHttpRequestRetryHandler =
            new DefaultHttpRequestRetryHandler();

    public static final String EMPTY = "";

    /**
     * 设置访问主机的长连接请求默认保持时间，单位：秒.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180609 13:55</font><br>
     * [请在此输入功能详述]
     *
     * @since 1.0.0
     */
    private static final Map<String, Integer> keepAliveHostMap;

    public static final boolean activeIdleConnectionMonitor = false;

    /**
     * 建议所有的maxPerRoute的总和，小于系统支持线程总量的一半.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180610 09:59</font><br>
     *
     * <p>示例如下： <br>
     * key=www.baidu.com, value=100<br>
     * key=http:www.baidu.com, value=100<br>
     * key=https:www.baidu.com, value=100<br>
     * key=http:www.baidu.com:80, value=100<br>
     * key=https:www.baidu.com:80, value=100<br>
     * 其中，key的默认schema是http，默认端口是80
     *
     * @since 1.0.0
     */
    public static final Map<String, Integer> maxPerRouteMap;

    static {
        keepAliveHostMap = new HashMap<>();
        keepAliveHostMap.put("exp.mynatapp.cc", 10);
        keepAliveHostMap.put("www.jingqu.cn", 30);
        keepAliveHostMap.put("dy.jingqu.cn", 30);
        
        maxPerRouteMap = new HashMap<>();
        maxPerRouteMap.put("exp.mynatapp.cc", 10);
        maxPerRouteMap.put("www.jingqu.cn", 200);
        maxPerRouteMap.put("dy.jingqu.cn", 200);
    }

    static {
        // 默认连接配置
        defaultConnectionConfig =
                ConnectionConfig.custom().setCharset(StandardCharsets.UTF_8).build();

        // 连接保持策略
        defaultConnectionStrategy =
                (response, context) -> {
                    HeaderElementIterator it =
                            new BasicHeaderElementIterator(
                                    response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                    HttpHost target =
                            (HttpHost) context.getAttribute(HttpClientContext.HTTP_TARGET_HOST);
                    String hostName = target.getHostName().toLowerCase();
                    while (it.hasNext()) {
                        HeaderElement he = it.nextElement();
                        String param = he.getName();
                        String value = he.getValue();
                        if (value != null && param.equalsIgnoreCase("timeout")) {
                            try {
                                log.info(
                                        "【连接保持策略】hostName={},key={},value={}",
                                        hostName,
                                        param,
                                        value);
                                return Long.parseLong(value) * 1000;
                            } catch (final NumberFormatException ignore) {
                            }
                        }
                    }
                    // otherwise keep alive for 60 seconds
                    return keepAliveHostMap.getOrDefault(hostName, 60) * 1000;
                };
    }
}
