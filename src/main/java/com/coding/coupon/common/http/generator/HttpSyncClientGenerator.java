/*
 * 文件名称：HttpSyncClientGenerator.java
 * 系统名称：[系统名称]
 * 模块名称：Http同步客户端生成器
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180607 20:26
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180607-01         Rushing0711     M201806072026 新建文件
 ********************************************************************************/
package com.coding.coupon.common.http.generator;

import com.coding.coupon.common.http.HttpConfig;
import com.coding.coupon.common.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.util.Map;

/** Http同步客户端生成器 */
public class HttpSyncClientGenerator extends HttpClientBuilder {

    /** 默认的ssl生成器，可以通过带参ssl方法重新指定证书. */
    private SSLGenerator defaultSSL = SSLGenerator.custom();

    /** 默认的连接池定义. */
    private int maxTotal = HttpConfig.DEFAULT_MAX_TOTAL;

    private int maxPerRoute = HttpConfig.DEFAULT_MAX_PER_ROUTE;
    private Map<String, Integer> maxPerRouteMap = HttpConfig.maxPerRouteMap;

    private HttpSyncClientGenerator() {}

    public static HttpSyncClientGenerator custom() {
        return (HttpSyncClientGenerator)
                new HttpSyncClientGenerator()
                        .setUserAgent(HttpConfig.userAgent)
                        .setKeepAliveStrategy(HttpConfig.defaultConnectionStrategy);
    }

    /**
     * 设置默认证书
     *
     * @return -
     */
    public HttpSyncClientGenerator ssl() throws HttpException {
        this.defaultSSL = SSLGenerator.custom().ssl();
        return this;
    }

    /**
     * 设置自定义的sslContext
     *
     * @param keyStorePath - 密钥库路径
     * @return -
     */
    public HttpSyncClientGenerator ssl(String keyStorePath) {
        return ssl(keyStorePath, "nopassword");
    }

    /**
     * 设置自定义证书
     *
     * @param keyStorePath - 密钥库路径
     * @param keyStorePass - 密钥库密码
     * @return -
     */
    public HttpSyncClientGenerator ssl(String keyStorePath, String keyStorePass) {
        this.defaultSSL = SSLGenerator.custom().ssl(keyStorePath, keyStorePass);
        return this;
    }

    /**
     * 设置自定义证书
     *
     * @param keyStoreType - 密钥库类型
     * @param keyStorePath - 密钥库路径
     * @param keyStorePass - 密钥库密码
     * @return -
     */
    public HttpSyncClientGenerator ssl(
            String keyStoreType, String keyStorePath, String keyStorePass) {
        this.defaultSSL = SSLGenerator.custom().ssl(keyStoreType, keyStorePath, keyStorePass);
        return this;
    }

    /**
     * 设置默认连接池.
     *
     * @return -
     */
    public HttpSyncClientGenerator pool() {
        return pool(HttpConfig.DEFAULT_MAX_TOTAL, HttpConfig.DEFAULT_MAX_PER_ROUTE);
    }

    /**
     * 设置连接池
     *
     * @param maxTotal - 最大连接数
     * @param maxPerRoute - 每个路由默认连接数
     * @return -
     */
    public HttpSyncClientGenerator pool(int maxTotal, int maxPerRoute) {
        return pool(maxTotal, maxPerRoute, null);
    }

    /**
     * 设置连接池
     *
     * @param maxTotal - 最大连接数
     * @param maxPerRoute - 路由默认的最大连接数
     * @param maxPerRouteMap - 针对具体具体路由的足底啊连接数，参见{@linkplain HttpConfig#maxPerRouteMap}
     * @return -
     */
    public HttpSyncClientGenerator pool(
            int maxTotal, int maxPerRoute, Map<String, Integer> maxPerRouteMap) {
        if (maxTotal > 0) {
            this.maxTotal = maxTotal;
        }
        if (maxPerRoute > 0) {
            this.maxPerRoute = maxPerRoute;
        }
        if (maxPerRouteMap != null) {
            this.maxPerRouteMap = maxPerRouteMap;
        }
        return this;
    }

    /**
     * 设置默认超时时间.
     *
     * @return -
     */
    public HttpSyncClientGenerator timeout() {
        return timeout(
                HttpConfig.DEFAULT_CONNECTION_TIMEOUT,
                HttpConfig.DEFAULT_SOCKET_TIMEOUT,
                HttpConfig.DEFAULT_TIMEOUT);
    }

    /**
     * 设置超时时间，单位：毫秒.
     *
     * @param connectionTimeout - 跟目标服务建立连接超时时间，根据业务情况而定
     * @param socketTimeout - 请求的超时时间(建立连接后，等待response返回的时间)
     * @param connectionRequestTimeout - 从连接池中获取连接的超时时间
     * @return -
     */
    public HttpSyncClientGenerator timeout(
            int connectionTimeout, int socketTimeout, int connectionRequestTimeout) {
        RequestConfig requestConfig =
                RequestConfig.custom()
                        .setConnectTimeout(connectionTimeout)
                        .setSocketTimeout(socketTimeout)
                        .setConnectionRequestTimeout(connectionRequestTimeout)
                        .build();
        return (HttpSyncClientGenerator) this.setDefaultRequestConfig(requestConfig);
    }

    /**
     * 设置代理.
     *
     * @param hostOrIP - 代理主机名或者IP地址
     * @param port - 代理端口
     * @return -
     */
    public HttpSyncClientGenerator proxy(String hostOrIP, int port) {
        HttpHost proxy = new HttpHost(hostOrIP, port, HttpHost.DEFAULT_SCHEME_NAME);
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
        return (HttpSyncClientGenerator) this.setRoutePlanner(routePlanner);
    }

    /**
     * 设置重试机制
     *
     * @return -
     */
    public HttpSyncClientGenerator retry() {
        return retry(HttpConfig.defaultHttpRequestRetryHandler);
    }

    public HttpSyncClientGenerator retry(HttpRequestRetryHandler httpRequestRetryHandler) {
        this.setRetryHandler(httpRequestRetryHandler);
        return this;
    }

    @Override
    public CloseableHttpClient build() {
        Registry<ConnectionSocketFactory> socketFactoryRegistry =
                RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http", PlainConnectionSocketFactory.INSTANCE)
                        .register("https", defaultSSL.getSslConnFactory())
                        .build();
        PoolingHttpClientConnectionManager poolingConnMgr =
                new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        poolingConnMgr.setMaxTotal(this.maxTotal);
        poolingConnMgr.setDefaultMaxPerRoute(this.maxPerRoute);
        for (Map.Entry<String, Integer> entry : this.maxPerRouteMap.entrySet()) {
            poolingConnMgr.setMaxPerRoute(
                    new HttpRoute(HttpHost.create(entry.getKey())), entry.getValue());
        }
        poolingConnMgr.setValidateAfterInactivity(HttpConfig.DEFAULT_VALIDATE_AFTER_INACTIVITY);
        poolingConnMgr.setDefaultConnectionConfig(HttpConfig.defaultConnectionConfig);
        this.setConnectionManager(poolingConnMgr);
        return super.build();
    }
}
