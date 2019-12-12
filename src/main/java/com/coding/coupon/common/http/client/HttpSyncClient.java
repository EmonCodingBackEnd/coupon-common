/*
 * 文件名称：HttpSyncClient.java
 * 系统名称：[系统名称]
 * 模块名称：Http同步
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
import com.coding.coupon.common.http.HttpException;
import com.coding.coupon.common.http.property.HttpMethod;
import com.coding.coupon.common.http.support.HttpSupport;
import com.coding.coupon.common.regex.RegexSupport;
import com.coding.coupon.common.regex.result.UriRegexResult;
import com.coding.coupon.common.regex.result.UrlParamRegexResult;
import com.coding.coupon.common.regex.result.UrlRegexResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractResponseHandler;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Http同步.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180607 07:48</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
public abstract class HttpSyncClient {

    /**
     * 该方法返回字符串格式的应答，因此会占用内存，不适用于应答内容超过1MB的请求.
     *
     * <p>特殊说明：paramMap与paramString二选一
     *
     * @param client - 自定义的同步HttpClient
     * @param httpMethod - 请求方法，参见{@linkplain HttpMethod}
     * @param url - 请求地址
     * @param paramMap - <key,value>格式的请求参数，仅限于POST、PUT、PATCH、DELETE方法
     * @param paramString - 字符串格式的请求参数，仅限于POST、PUT、PATCH、DELETE方法，适用于json、xml等请求参数
     * @param timeout - 超时时间，-1表示永不超时，小于-1会被重置为默认值，单位：毫秒
     * @param headers - 请求头
     * @param charset - 请求体编码
     * @param context - 上下文
     * @throws IOException -
     */
    public static String execute(
            HttpClient client,
            HttpMethod httpMethod,
            String url,
            Map<String, String> paramMap,
            String paramString,
            HttpContext context,
            int timeout,
            Header[] headers,
            Charset charset)
            throws IOException {
        return execute(
                client,
                httpMethod,
                url,
                paramMap,
                paramString,
                context,
                timeout,
                headers,
                charset,
                new AbstractResponseHandler<String>() {
                    @Override
                    public String handleEntity(HttpEntity entity) throws IOException {
                        return EntityUtils.toString(entity, charset);
                    }
                });
    }

    /**
     * 无返回结果调用，应答数据会输出到给定的输出流，该方法不会自动关闭输出流.
     *
     * <p>特殊说明：paramMap与paramString二选一
     *
     * @param client - 自定义的同步HttpClient
     * @param httpMethod - 请求方法，参见{@linkplain HttpMethod}
     * @param url - 请求地址
     * @param paramMap - <key,value>格式的请求参数，仅限于POST、PUT、PATCH、DELETE方法
     * @param paramString - 字符串格式的请求参数，仅限于POST、PUT、PATCH、DELETE方法，适用于json、xml等请求参数
     * @param context - 上下文
     * @param timeout - 超时时间，-1表示永不超时，小于-1会被重置为默认值，单位：毫秒
     * @param headers - 请求头
     * @param charset - 请求体编码
     * @param outputStream - 存放应答的输出流
     * @throws IOException -
     */
    public static void execute(
            HttpClient client,
            HttpMethod httpMethod,
            String url,
            Map<String, String> paramMap,
            String paramString,
            HttpContext context,
            int timeout,
            Header[] headers,
            Charset charset,
            OutputStream outputStream)
            throws IOException {
        execute(
                client,
                httpMethod,
                url,
                paramMap,
                paramString,
                context,
                timeout,
                headers,
                charset,
                new AbstractResponseHandler<String>() {
                    @Override
                    public String handleEntity(HttpEntity entity) throws IOException {
                        entity.writeTo(outputStream);
                        if (entity.isChunked()) {
                            log.info(
                                    "【Http】isStreaming={},isChunked={},isRepeatable={},Transfer-Encoding: chunked类型暂不支持应答内容大小的估算",
                                    entity.isStreaming(),
                                    entity.isChunked(),
                                    entity.isRepeatable());
                        } else {
                            log.info(
                                    "【Http】isStreaming={},isChunked={},isRepeatable={},应答内容大小={}",
                                    entity.isStreaming(),
                                    entity.isChunked(),
                                    entity.isRepeatable(),
                                    HttpSupport.getNetContentSize(entity.getContentLength()));
                        }
                        return null;
                    }
                });
    }

    /**
     * 基本请求方法，内部使用.
     *
     * <p>特殊说明：paramMap与paramString二选一
     *
     * @param client - 自定义的同步HttpClient
     * @param httpMethod - 请求方法，参见{@linkplain HttpMethod}
     * @param url - 请求地址
     * @param paramMap - <key,value>格式的请求参数，仅限于POST、PUT、PATCH、DELETE方法
     * @param paramString - 字符串格式的请求参数，仅限于POST、PUT、PATCH、DELETE方法，适用于json、xml等请求参数
     * @param context - 上下文
     * @param timeout - 超时时间，-1表示永不超时，小于-1会被重置为默认值，单位：毫秒
     * @param headers - 请求头
     * @param charset - 请求体编码
     * @param responseHandler - 应答处理器，用来处理应答数据
     * @param <T> - 处理后的应答数据返回类型
     * @return - 应答结果
     * @throws IOException -
     */
    public static <T> T execute(
            HttpClient client,
            HttpMethod httpMethod,
            String url,
            Map<String, String> paramMap,
            String paramString,
            HttpContext context,
            int timeout,
            Header[] headers,
            Charset charset,
            ResponseHandler<T> responseHandler)
            throws IOException {
        Assert.notNull(url, "url must be not null");
        if (!CollectionUtils.isEmpty(paramMap) && !StringUtils.isEmpty(paramString)) {
            log.error(
                    "【Http】{}请求paramMap={}与paramString={}不能同时存在",
                    httpMethod.name(),
                    paramMap,
                    paramString);
            throw new HttpException("【Http】请求paramMap与paramString不能同时存在");
        }

        String uri;
        String param;
        List<NameValuePair> pairList = new ArrayList<>();

        boolean isXmlOrJson = false;

        if (CollectionUtils.isEmpty(paramMap) && StringUtils.isEmpty(paramString)) {
            UrlRegexResult urlRegexResult = RegexSupport.matchUrl(url);
            if (!urlRegexResult.isMatched()) {
                log.error("【Http】{}请求不合法url={}", httpMethod.name(), url);
                throw new HttpException("【Http】url不合法");
            }
            uri = urlRegexResult.getUri();
            param = urlRegexResult.getParam();
            if (!StringUtils.isEmpty(param)) {
                List<NameValuePair> paramList =
                        HttpSupport.convertToPairList(
                                HttpSupport.buildParams(urlRegexResult.getParam()));
                pairList.addAll(paramList);
            }
        } else if (!CollectionUtils.isEmpty(paramMap) && StringUtils.isEmpty(paramString)) {
            UriRegexResult uriRegexResult = RegexSupport.matchUri(url);
            if (!uriRegexResult.isMatched()) {
                log.error("【Http】{}请求不合法url={}", httpMethod.name(), url);
                throw new HttpException("【Http】参数paramMap或者paramString存在时，url不能附带参数及定位信息");
            }
            List<NameValuePair> paramList = HttpSupport.convertToPairList(paramMap);
            pairList.addAll(paramList);
            uri = url;
            param = URLEncodedUtils.format(pairList, charset);
        } else {
            UriRegexResult uriRegexResult = RegexSupport.matchUri(url);
            if (!uriRegexResult.isMatched()) {
                log.error("【Http】{}请求不合法url={}", httpMethod.name(), url);
                throw new HttpException("【Http】参数paramMap或者paramString存在时，url不能附带参数及定位信息");
            }
            UrlParamRegexResult urlParamRegexResult = RegexSupport.matchUrlParam(paramString);
            if (urlParamRegexResult.isMatched()) {
                List<NameValuePair> paramList =
                        HttpSupport.convertToPairList(
                                HttpSupport.buildParams(urlParamRegexResult.getParam()));
                pairList.addAll(paramList);
                param = URLEncodedUtils.format(pairList, charset);
            } else {
                isXmlOrJson = true;
                param = paramString;
            }
            uri = url;
        }

        HttpRequestBase httpRequest = HttpMethod.getHttpRequest(uri, httpMethod);
        httpRequest.setHeaders(headers);

        // 实现了接口HttpEntityEnclosingRequest的类，可以支持设置Entity
        if (HttpEntityEnclosingRequest.class.isAssignableFrom(httpRequest.getClass())) {
            if (isXmlOrJson) {
                ((HttpEntityEnclosingRequestBase) httpRequest)
                        .setEntity(new StringEntity(paramString, charset));
            } else {
                ((HttpEntityEnclosingRequestBase) httpRequest)
                        .setEntity(new UrlEncodedFormEntity(pairList, charset));
            }
        } else {
            try {
                if (!StringUtils.isEmpty(param)) {
                    httpRequest.setURI(new URI(String.format("%s?%s", uri, param)));
                }
            } catch (URISyntaxException e) {
                log.error("【Http】请求地址解析错误", e);
                throw new HttpException(e);
            }
        }
        if (timeout != Integer.MIN_VALUE) {
            if (timeout < -1) {
                timeout = HttpConfig.DEFAULT_SOCKET_TIMEOUT;
            }
            RequestConfig requestConfig =
                    RequestConfig.custom()
                            .setConnectTimeout(timeout)
                            .setSocketTimeout(timeout)
                            .setConnectionRequestTimeout(timeout)
                            .build();
            httpRequest.setConfig(requestConfig);
        }
        if (log.isDebugEnabled()) {
            log.debug("【Http】{}请求url={},params=[{}]", httpMethod.name(), uri, param);
        } else {
            log.info("【Http】{}请求url={}", httpMethod.name(), uri);
        }

        return execute(client, httpRequest, charset, responseHandler, context);
    }

    private static <T> T execute(
            HttpClient client,
            HttpRequestBase httpRequest,
            Charset charset,
            ResponseHandler<T> responseHandler,
            HttpContext context)
            throws IOException {
        T result;
        if (context != null) {
            result = client.execute(httpRequest, responseHandler, context);
        } else {
            result = client.execute(httpRequest, responseHandler);
        }
        if (result != null) {
            if (Long.class.isAssignableFrom(result.getClass())
                    || Integer.class.isAssignableFrom(result.getClass())) {
                log.info("【Http】应答内容大小={}", HttpSupport.getNetContentSize((Long) result));
            } else {
                if (log.isDebugEnabled()) {
                    log.debug(
                            "【Http】应答内容大小={},应答内容={}",
                            HttpSupport.getNetContentSize(
                                    result.toString().getBytes(charset).length),
                            result);
                } else {
                    log.info(
                            "【Http】应答内容大小={}",
                            HttpSupport.getNetContentSize(
                                    result.toString().getBytes(charset).length));
                }
            }
        }

        return result;
    }
}
