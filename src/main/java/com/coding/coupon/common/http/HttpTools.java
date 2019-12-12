/*
 * 文件名称：HttpTools.java
 * 系统名称：[系统名称]
 * 模块名称：Http工具
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180607 12:06
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180607-01         Rushing0711     M201806071206 新建文件
 ********************************************************************************/
package com.coding.coupon.common.http;

import com.coding.coupon.common.http.client.HttpSyncClient;
import com.coding.coupon.common.http.property.HttpMethod;
import com.coding.coupon.common.http.support.ClientSupport;
import com.coding.coupon.common.http.support.HeaderSupport;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Http工具.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180607 12:07</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
public abstract class HttpTools {

    // ==============================GET-Beg==============================

    // ----------------------------------------Segment-1-Beg-----------------------------------------

    public static String doGet(String url) throws IOException {
        return doGet(url, new HashMap<>());
    }

    public static String doGet(String url, int timeout) throws IOException {
        return doGet(url, new HashMap<>(), timeout);
    }

    public static String doGet(String url, Map<String, String> paramMap) throws IOException {
        return doGet(url, paramMap, Integer.MIN_VALUE);
    }

    public static String doGet(String url, Map<String, String> paramMap, int timeout)
            throws IOException {
        return doGet(
                ClientSupport.httpSyncClient,
                url,
                paramMap,
                null,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    public static String doGet(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            HttpContext context,
            int timeout,
            Header[] headers,
            Charset charset)
            throws IOException {
        return doGet(client, url, paramMap, null, context, timeout, headers, charset);
    }

    public static String doGet(String url, HttpContext context) throws IOException {
        return doGet(url, new HashMap<>(), context);
    }

    public static String doGet(String url, HttpContext context, int timeout) throws IOException {
        return doGet(url, new HashMap<>(), context, timeout);
    }

    public static String doGet(String url, Map<String, String> paramMap, HttpContext context)
            throws IOException {
        return doGet(url, paramMap, context, Integer.MIN_VALUE);
    }

    public static String doGet(
            String url, Map<String, String> paramMap, HttpContext context, int timeout)
            throws IOException {
        return doGet(
                ClientSupport.httpSyncClient,
                url,
                paramMap,
                context,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    // ----------------------------------------Segment-1-End-----------------------------------------

    // ----------------------------------------Segment-2-Beg-----------------------------------------

    public static String doGet(String url, String paramString) throws IOException {
        return doGet(url, paramString, Integer.MIN_VALUE);
    }

    public static String doGet(String url, String paramString, int timeout) throws IOException {
        return doGet(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                null,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    public static String doGet(
            HttpClient client,
            String url,
            String paramString,
            HttpContext context,
            int timeout,
            Header[] headers,
            Charset charset)
            throws IOException {
        return doGet(client, url, null, paramString, context, timeout, headers, charset);
    }

    public static String doGet(String url, String paramString, HttpContext context)
            throws IOException {
        return doGet(url, paramString, context, Integer.MIN_VALUE);
    }

    public static String doGet(String url, String paramString, HttpContext context, int timeout)
            throws IOException {
        return doGet(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                context,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    // ----------------------------------------Segment-2-End-----------------------------------------

    // ----------------------------------------Segment-3-Beg-----------------------------------------
    public static String doGetRetry(
            String url, Map<String, String> paramMap, String paramString, int timeout)
            throws IOException {
        return doGet(
                ClientSupport.httpSyncRetryClient,
                url,
                paramMap,
                paramString,
                null,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    public static String doGetRetry(
            String url,
            Map<String, String> paramMap,
            String paramString,
            HttpContext context,
            int timeout)
            throws IOException {
        return doGet(
                ClientSupport.httpSyncRetryClient,
                url,
                paramMap,
                paramString,
                context,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    // ----------------------------------------Segment-3-End-----------------------------------------

    private static String doGet(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            String paramString,
            HttpContext context,
            int timeout,
            Header[] headers,
            Charset charset)
            throws IOException {
        return HttpSyncClient.execute(
                client,
                HttpMethod.GET,
                url,
                paramMap,
                paramString,
                context,
                timeout,
                headers,
                charset);
    }

    // ================================================================================

    // ----------------------------------------Segment-1-Beg-----------------------------------------

    public static void doGet(String url, OutputStream outputStream) throws IOException {
        doGet(url, new HashMap<>(), outputStream);
    }

    public static void doGet(String url, int timeout, OutputStream outputStream)
            throws IOException {
        doGet(url, new HashMap<>(), timeout, outputStream);
    }

    public static void doGet(String url, Map<String, String> paramMap, OutputStream outputStream)
            throws IOException {
        doGet(url, paramMap, Integer.MIN_VALUE, outputStream);
    }

    public static void doGet(
            String url, Map<String, String> paramMap, int timeout, OutputStream outputStream)
            throws IOException {
        doGet(
                ClientSupport.httpSyncClient,
                url,
                paramMap,
                null,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8,
                outputStream);
    }

    public static void doGet(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            HttpContext context,
            int timeout,
            Header[] headers,
            Charset charset,
            OutputStream outputStream)
            throws IOException {
        doGet(client, url, paramMap, null, context, timeout, headers, charset, outputStream);
    }

    public static void doGet(String url, HttpContext context, OutputStream outputStream)
            throws IOException {
        doGet(url, new HashMap<>(), context, outputStream);
    }

    public static void doGet(
            String url, HttpContext context, int timeout, OutputStream outputStream)
            throws IOException {
        doGet(url, new HashMap<>(), context, timeout, outputStream);
    }

    public static void doGet(
            String url,
            Map<String, String> paramMap,
            HttpContext context,
            OutputStream outputStream)
            throws IOException {
        doGet(url, paramMap, context, Integer.MIN_VALUE, outputStream);
    }

    public static void doGet(
            String url,
            Map<String, String> paramMap,
            HttpContext context,
            int timeout,
            OutputStream outputStream)
            throws IOException {
        doGet(
                ClientSupport.httpSyncClient,
                url,
                paramMap,
                context,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8,
                outputStream);
    }

    // ----------------------------------------Segment-1-End-----------------------------------------

    // ----------------------------------------Segment-2-Beg-----------------------------------------

    public static void doGet(String url, String paramString, OutputStream outputStream)
            throws IOException {
        doGet(url, paramString, Integer.MIN_VALUE, outputStream);
    }

    public static void doGet(String url, String paramString, int timeout, OutputStream outputStream)
            throws IOException {
        doGet(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                null,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8,
                outputStream);
    }

    public static void doGet(
            HttpClient client,
            String url,
            String paramString,
            HttpContext context,
            int timeout,
            Header[] headers,
            Charset charset,
            OutputStream outputStream)
            throws IOException {
        doGet(client, url, null, paramString, context, timeout, headers, charset, outputStream);
    }

    public static void doGet(
            String url, String paramString, HttpContext context, OutputStream outputStream)
            throws IOException {
        doGet(url, paramString, context, Integer.MIN_VALUE, outputStream);
    }

    public static void doGet(
            String url,
            String paramString,
            HttpContext context,
            int timeout,
            OutputStream outputStream)
            throws IOException {
        doGet(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                context,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8,
                outputStream);
    }

    // ----------------------------------------Segment-2-End-----------------------------------------

    // ----------------------------------------Segment-3-Beg-----------------------------------------

    public static void doGetRetry(
            String url,
            Map<String, String> paramMap,
            String paramString,
            int timeout,
            OutputStream outputStream)
            throws IOException {
        doGetRetry(url, paramMap, paramString, null, timeout, outputStream);
    }

    public static void doGetRetry(
            String url,
            Map<String, String> paramMap,
            String paramString,
            HttpContext context,
            int timeout,
            OutputStream outputStream)
            throws IOException {
        doGet(
                ClientSupport.httpSyncRetryClient,
                url,
                paramMap,
                paramString,
                context,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8,
                outputStream);
    }

    // ----------------------------------------Segment-3-End-----------------------------------------

    private static void doGet(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            String paramString,
            HttpContext context,
            int timeout,
            Header[] headers,
            Charset charset,
            OutputStream outputStream)
            throws IOException {
        HttpSyncClient.execute(
                client,
                HttpMethod.GET,
                url,
                paramMap,
                paramString,
                context,
                timeout,
                headers,
                charset,
                outputStream);
    }

    public static Long doGet(String url, ResponseHandler<Long> responseHandler) throws IOException {
        return doGet(url, Integer.MIN_VALUE, responseHandler);
    }

    public static Long doGet(String url, int timeout, ResponseHandler<Long> responseHandler)
            throws IOException {
        return HttpSyncClient.execute(
                ClientSupport.httpSyncClient,
                HttpMethod.GET,
                url,
                null,
                null,
                null,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8,
                responseHandler);
    }

    // ==============================GET-End==============================

    // ==============================HEAD-Beg==============================

    public static String doHead(String url) throws IOException {
        return doHead(url, new HashMap<>());
    }

    public static String doHead(String url, int timeout) throws IOException {
        return doHead(url, new HashMap<>(), timeout);
    }

    public static String doHead(String url, Map<String, String> paramMap) throws IOException {
        return doHead(url, paramMap, Integer.MIN_VALUE);
    }

    public static String doHead(String url, Map<String, String> paramMap, int timeout)
            throws IOException {
        return doHead(
                ClientSupport.httpSyncClient,
                url,
                paramMap,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    public static String doHead(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            int timeout,
            Header[] headers,
            Charset charset)
            throws IOException {
        return doHead(client, url, paramMap, null, timeout, headers, charset);
    }

    public static String doHead(String url, String paramString) throws IOException {
        return doHead(url, paramString, Integer.MIN_VALUE);
    }

    public static String doHead(String url, String paramString, int timeout) throws IOException {
        return doHead(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    public static String doHead(
            HttpClient client,
            String url,
            String paramString,
            int timeout,
            Header[] headers,
            Charset charset)
            throws IOException {
        return doHead(client, url, null, paramString, timeout, headers, charset);
    }

    public static String doHeadRetry(
            String url, Map<String, String> paramMap, String paramString, int timeout)
            throws IOException {
        return doHead(
                ClientSupport.httpSyncRetryClient,
                url,
                paramMap,
                paramString,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    private static String doHead(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            String paramString,
            int timeout,
            Header[] headers,
            Charset charset)
            throws IOException {
        return HttpSyncClient.execute(
                client,
                HttpMethod.HEAD,
                url,
                paramMap,
                paramString,
                null,
                timeout,
                headers,
                charset);
    }

    // ================================================================================

    public static void doHead(String url, OutputStream outputStream) throws IOException {
        doHead(url, new HashMap<>(), outputStream);
    }

    public static void doHead(String url, int timeout, OutputStream outputStream)
            throws IOException {
        doHead(url, new HashMap<>(), timeout, outputStream);
    }

    public static void doHead(String url, Map<String, String> paramMap, OutputStream outputStream)
            throws IOException {
        doHead(url, paramMap, Integer.MIN_VALUE, outputStream);
    }

    public static void doHead(
            String url, Map<String, String> paramMap, int timeout, OutputStream outputStream)
            throws IOException {
        doHead(
                ClientSupport.httpSyncClient,
                url,
                paramMap,
                timeout,
                null,
                StandardCharsets.UTF_8,
                outputStream);
    }

    public static void doHead(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            int timeout,
            Header[] headers,
            Charset charset,
            OutputStream outputStream)
            throws IOException {
        doHead(client, url, paramMap, null, timeout, headers, charset, outputStream);
    }

    public static void doHead(String url, String paramString, OutputStream outputStream)
            throws IOException {
        doHead(url, paramString, Integer.MIN_VALUE, outputStream);
    }

    public static void doHead(
            String url, String paramString, int timeout, OutputStream outputStream)
            throws IOException {
        doHead(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                timeout,
                null,
                StandardCharsets.UTF_8,
                outputStream);
    }

    public static void doHead(
            HttpClient client,
            String url,
            String paramString,
            int timeout,
            Header[] headers,
            Charset charset,
            OutputStream outputStream)
            throws IOException {
        doHead(client, url, null, paramString, timeout, headers, charset, outputStream);
    }

    public static void doHeadRetry(
            String url,
            Map<String, String> paramMap,
            String paramString,
            int timeout,
            OutputStream outputStream)
            throws IOException {
        doHead(
                ClientSupport.httpSyncRetryClient,
                url,
                paramMap,
                paramString,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8,
                outputStream);
    }

    private static void doHead(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            String paramString,
            int timeout,
            Header[] headers,
            Charset charset,
            OutputStream outputStream)
            throws IOException {
        HttpSyncClient.execute(
                client,
                HttpMethod.HEAD,
                url,
                paramMap,
                paramString,
                null,
                timeout,
                headers,
                charset,
                outputStream);
    }

    // ==============================HEAD-End==============================

    // ==============================POST-Beg==============================

    // ----------------------------------------Segment-1-Beg-----------------------------------------
    public static String doPost(String url) throws IOException {
        return doPost(url, new HashMap<>());
    }

    public static String doPost(String url, int timeout) throws IOException {
        return doPost(url, new HashMap<>(), timeout);
    }

    public static String doPost(String url, Map<String, String> paramMap) throws IOException {
        return doPost(url, paramMap, Integer.MIN_VALUE);
    }

    public static String doPost(String url, Map<String, String> paramMap, int timeout)
            throws IOException {
        return doPost(
                ClientSupport.httpSyncClient,
                url,
                paramMap,
                null,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    public static String doPost(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            HttpContext context,
            int timeout,
            Header[] headers,
            Charset charset)
            throws IOException {
        return doPost(client, url, paramMap, null, context, timeout, headers, charset);
    }

    public static String doPost(String url, HttpContext context) throws IOException {
        return doPost(url, new HashMap<>(), context);
    }

    public static String doPost(String url, HttpContext context, int timeout) throws IOException {
        return doPost(url, new HashMap<>(), context, timeout);
    }

    public static String doPost(String url, Map<String, String> paramMap, HttpContext context)
            throws IOException {
        return doPost(url, paramMap, context, Integer.MIN_VALUE);
    }

    public static String doPost(
            String url, Map<String, String> paramMap, HttpContext context, int timeout)
            throws IOException {
        return doPost(
                ClientSupport.httpSyncClient,
                url,
                paramMap,
                null,
                context,
                timeout,
                null,
                StandardCharsets.UTF_8);
    }

    // ----------------------------------------Segment-1-End-----------------------------------------

    // ----------------------------------------Segment-2-Beg-----------------------------------------

    public static String doPost(String url, String paramString) throws IOException {
        return doPost(url, paramString, Integer.MIN_VALUE);
    }

    public static String doPost(String url, String paramString, int timeout) throws IOException {
        return doPost(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                null,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    public static String doPostJson(String url, String paramString) throws IOException {
        return doPostJson(url, paramString, Integer.MIN_VALUE);
    }

    public static String doPostJson(String url, String paramString, int timeout)
            throws IOException {
        return doPost(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                null,
                timeout,
                HeaderSupport.KEEP_ALIVE_JSON_UTF8_HEADERS,
                StandardCharsets.UTF_8);
    }

    /*public static String doPostXmlWechat(String url, String paramString) throws IOException {
        return doPostXmlWechat(url, paramString, Integer.MIN_VALUE);
    }*/

    /*public static String doPostXmlWechat(String url, String paramString, int timeout)
            throws IOException {
        return doPost(
                ClientSupport.httpSyncWechatClient,
                url,
                paramString,
                null,
                timeout,
                HeaderSupport.KEEP_ALIVE_XML_UTF8_HEADERS,
                StandardCharsets.UTF_8);
    }*/

    public static String doPostXml(String url, String paramString) throws IOException {
        return doPostXml(url, paramString, Integer.MIN_VALUE);
    }

    public static String doPostXml(String url, String paramString, int timeout) throws IOException {
        return doPost(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                null,
                timeout,
                HeaderSupport.KEEP_ALIVE_XML_UTF8_HEADERS,
                StandardCharsets.UTF_8);
    }

    public static String doPost(
            HttpClient client,
            String url,
            String paramString,
            HttpContext context,
            int timeout,
            Header[] headers,
            Charset charset)
            throws IOException {
        return doPost(client, url, null, paramString, context, timeout, headers, charset);
    }

    public static String doPost(String url, String paramString, HttpContext context)
            throws IOException {
        return doPost(url, paramString, context, Integer.MIN_VALUE);
    }

    public static String doPost(String url, String paramString, HttpContext context, int timeout)
            throws IOException {
        return doPost(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                context,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    public static String doPostJson(String url, String paramString, HttpContext context)
            throws IOException {
        return doPostJson(url, paramString, context, Integer.MIN_VALUE);
    }

    public static String doPostJson(
            String url, String paramString, HttpContext context, int timeout) throws IOException {
        return doPost(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                context,
                timeout,
                HeaderSupport.KEEP_ALIVE_JSON_UTF8_HEADERS,
                StandardCharsets.UTF_8);
    }

    public static String doPostXml(String url, String paramString, HttpContext context)
            throws IOException {
        return doPostXml(url, paramString, context, Integer.MIN_VALUE);
    }

    public static String doPostXml(String url, String paramString, HttpContext context, int timeout)
            throws IOException {
        return doPost(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                context,
                timeout,
                HeaderSupport.KEEP_ALIVE_XML_UTF8_HEADERS,
                StandardCharsets.UTF_8);
    }

    // ----------------------------------------Segment-2-End-----------------------------------------

    // ----------------------------------------Segment-3-Beg-----------------------------------------

    public static String doPostRetry(
            String url, Map<String, String> paramMap, String paramString, int timeout)
            throws IOException {
        return doPostRetry(url, paramMap, paramString, null, timeout);
    }

    public static String doPostRetry(
            String url,
            Map<String, String> paramMap,
            String paramString,
            HttpContext context,
            int timeout)
            throws IOException {
        return doPost(
                ClientSupport.httpSyncRetryClient,
                url,
                paramMap,
                paramString,
                context,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }
    // ----------------------------------------Segment-3-End-----------------------------------------

    private static String doPost(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            String paramString,
            HttpContext context,
            int timeout,
            Header[] headers,
            Charset charset)
            throws IOException {
        return HttpSyncClient.execute(
                client,
                HttpMethod.POST,
                url,
                paramMap,
                paramString,
                context,
                timeout,
                headers,
                charset);
    }

    // ================================================================================

    // ----------------------------------------Segment-1-Beg-----------------------------------------

    public static void doPost(String url, OutputStream outputStream) throws IOException {
        doPost(url, new HashMap<>(), outputStream);
    }

    public static void doPost(String url, int timeout, OutputStream outputStream)
            throws IOException {
        doPost(url, new HashMap<>(), timeout, outputStream);
    }

    public static void doPost(String url, Map<String, String> paramMap, OutputStream outputStream)
            throws IOException {
        doPost(url, paramMap, Integer.MIN_VALUE, outputStream);
    }

    public static void doPost(
            String url, Map<String, String> paramMap, int timeout, OutputStream outputStream)
            throws IOException {
        doPost(
                ClientSupport.httpSyncClient,
                url,
                paramMap,
                null,
                timeout,
                null,
                StandardCharsets.UTF_8,
                outputStream);
    }

    public static void doPost(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            HttpContext context,
            int timeout,
            Header[] headers,
            Charset charset,
            OutputStream outputStream)
            throws IOException {
        doPost(client, url, paramMap, null, context, timeout, headers, charset, outputStream);
    }

    public static void doPost(String url, HttpContext context, OutputStream outputStream)
            throws IOException {
        doPost(url, new HashMap<>(), context, outputStream);
    }

    public static void doPost(
            String url, HttpContext context, int timeout, OutputStream outputStream)
            throws IOException {
        doPost(url, new HashMap<>(), context, timeout, outputStream);
    }

    public static void doPost(
            String url,
            Map<String, String> paramMap,
            HttpContext context,
            OutputStream outputStream)
            throws IOException {
        doPost(url, paramMap, context, Integer.MIN_VALUE, outputStream);
    }

    public static void doPost(
            String url,
            Map<String, String> paramMap,
            HttpContext context,
            int timeout,
            OutputStream outputStream)
            throws IOException {
        doPost(
                ClientSupport.httpSyncClient,
                url,
                paramMap,
                context,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8,
                outputStream);
    }

    // ----------------------------------------Segment-1-End-----------------------------------------

    // ----------------------------------------Segment-2-Beg-----------------------------------------

    public static void doPost(String url, String paramString, OutputStream outputStream)
            throws IOException {
        doPost(url, paramString, Integer.MIN_VALUE, outputStream);
    }

    public static void doPost(
            String url, String paramString, int timeout, OutputStream outputStream)
            throws IOException {
        doPost(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                null,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8,
                outputStream);
    }

    public static void doPostJson(String url, String paramString, OutputStream outputStream)
            throws IOException {
        doPostJson(url, paramString, Integer.MIN_VALUE, outputStream);
    }

    public static void doPostJson(
            String url, String paramString, int timeout, OutputStream outputStream)
            throws IOException {
        doPost(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                null,
                timeout,
                HeaderSupport.KEEP_ALIVE_JSON_UTF8_HEADERS,
                StandardCharsets.UTF_8,
                outputStream);
    }

    public static void doPostXml(String url, String paramString, OutputStream outputStream)
            throws IOException {
        doPostXml(url, paramString, Integer.MIN_VALUE, outputStream);
    }

    public static void doPostXml(
            String url, String paramString, int timeout, OutputStream outputStream)
            throws IOException {
        doPost(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                null,
                timeout,
                HeaderSupport.KEEP_ALIVE_XML_UTF8_HEADERS,
                StandardCharsets.UTF_8,
                outputStream);
    }

    public static void doPost(
            HttpClient client,
            String url,
            String paramString,
            HttpContext context,
            int timeout,
            Header[] headers,
            Charset charset,
            OutputStream outputStream)
            throws IOException {
        doPost(client, url, null, paramString, context, timeout, headers, charset, outputStream);
    }

    public static void doPost(
            String url, String paramString, HttpContext context, OutputStream outputStream)
            throws IOException {
        doPost(url, paramString, context, Integer.MIN_VALUE, outputStream);
    }

    public static void doPost(
            String url,
            String paramString,
            HttpContext context,
            int timeout,
            OutputStream outputStream)
            throws IOException {
        doPost(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                context,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8,
                outputStream);
    }

    public static void doPostJson(
            String url, String paramString, HttpContext context, OutputStream outputStream)
            throws IOException {
        doPostJson(url, paramString, context, Integer.MIN_VALUE, outputStream);
    }

    public static void doPostJson(
            String url,
            String paramString,
            HttpContext context,
            int timeout,
            OutputStream outputStream)
            throws IOException {
        doPost(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                context,
                timeout,
                HeaderSupport.KEEP_ALIVE_JSON_UTF8_HEADERS,
                StandardCharsets.UTF_8,
                outputStream);
    }

    public static void doPostXml(
            String url, String paramString, HttpContext context, OutputStream outputStream)
            throws IOException {
        doPostXml(url, paramString, context, Integer.MIN_VALUE, outputStream);
    }

    public static void doPostXml(
            String url,
            String paramString,
            HttpContext context,
            int timeout,
            OutputStream outputStream)
            throws IOException {
        doPost(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                context,
                timeout,
                HeaderSupport.KEEP_ALIVE_XML_UTF8_HEADERS,
                StandardCharsets.UTF_8,
                outputStream);
    }

    // ----------------------------------------Segment-2-End-----------------------------------------

    // ----------------------------------------Segment-3-Beg-----------------------------------------

    public static void doPostRetry(
            String url,
            Map<String, String> paramMap,
            String paramString,
            int timeout,
            OutputStream outputStream)
            throws IOException {
        doPostRetry(url, paramMap, paramString, null, timeout, outputStream);
    }

    public static void doPostRetry(
            String url,
            Map<String, String> paramMap,
            String paramString,
            HttpContext context,
            int timeout,
            OutputStream outputStream)
            throws IOException {
        doPost(
                ClientSupport.httpSyncRetryClient,
                url,
                paramMap,
                paramString,
                context,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8,
                outputStream);
    }

    // ----------------------------------------Segment-3-End-----------------------------------------

    private static void doPost(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            String paramString,
            HttpContext context,
            int timeout,
            Header[] headers,
            Charset charset,
            OutputStream outputStream)
            throws IOException {
        HttpSyncClient.execute(
                client,
                HttpMethod.POST,
                url,
                paramMap,
                paramString,
                context,
                timeout,
                headers,
                charset,
                outputStream);
    }

    public static Long doPost(String url, ResponseHandler<Long> responseHandler)
            throws IOException {
        return doPost(url, Integer.MIN_VALUE, responseHandler);
    }

    public static Long doPost(String url, int timeout, ResponseHandler<Long> responseHandler)
            throws IOException {
        return HttpSyncClient.execute(
                ClientSupport.httpSyncClient,
                HttpMethod.POST,
                url,
                null,
                null,
                null,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8,
                responseHandler);
    }

    // ==============================POST-End==============================

    // ==============================PUT-Beg==============================

    public static String doPut(String url) throws IOException {
        return doPut(url, new HashMap<>());
    }

    public static String doPut(String url, int timeout) throws IOException {
        return doPut(url, new HashMap<>(), timeout);
    }

    public static String doPut(String url, Map<String, String> paramMap) throws IOException {
        return doPut(url, paramMap, Integer.MIN_VALUE);
    }

    public static String doPut(String url, Map<String, String> paramMap, int timeout)
            throws IOException {
        return doPut(
                ClientSupport.httpSyncClient,
                url,
                paramMap,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    public static String doPut(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            int timeout,
            Header[] headers,
            Charset charset)
            throws IOException {
        return doPut(client, url, paramMap, null, timeout, headers, charset);
    }

    public static String doPut(String url, String paramString) throws IOException {
        return doPut(url, paramString, Integer.MIN_VALUE);
    }

    public static String doPut(String url, String paramString, int timeout) throws IOException {
        return doPut(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    public static String doPut(
            HttpClient client,
            String url,
            String paramString,
            int timeout,
            Header[] headers,
            Charset charset)
            throws IOException {
        return doPut(client, url, null, paramString, timeout, headers, charset);
    }

    public static String doPutRetry(
            String url, Map<String, String> paramMap, String paramString, int timeout)
            throws IOException {
        return doPut(
                ClientSupport.httpSyncRetryClient,
                url,
                paramMap,
                paramString,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    private static String doPut(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            String paramString,
            int timeout,
            Header[] headers,
            Charset charset)
            throws IOException {
        return HttpSyncClient.execute(
                client,
                HttpMethod.PUT,
                url,
                paramMap,
                paramString,
                null,
                timeout,
                headers,
                charset);
    }

    // ================================================================================

    public static void doPut(String url, OutputStream outputStream) throws IOException {
        doPut(url, new HashMap<>(), outputStream);
    }

    public static void doPut(String url, int timeout, OutputStream outputStream)
            throws IOException {
        doPut(url, new HashMap<>(), timeout, outputStream);
    }

    public static void doPut(String url, Map<String, String> paramMap, OutputStream outputStream)
            throws IOException {
        doPut(url, paramMap, Integer.MIN_VALUE, outputStream);
    }

    public static void doPut(
            String url, Map<String, String> paramMap, int timeout, OutputStream outputStream)
            throws IOException {
        doPut(
                ClientSupport.httpSyncClient,
                url,
                paramMap,
                timeout,
                null,
                StandardCharsets.UTF_8,
                outputStream);
    }

    public static void doPut(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            int timeout,
            Header[] headers,
            Charset charset,
            OutputStream outputStream)
            throws IOException {
        doPut(client, url, paramMap, null, timeout, headers, charset, outputStream);
    }

    public static void doPut(String url, String paramString, OutputStream outputStream)
            throws IOException {
        doPut(url, paramString, Integer.MIN_VALUE, outputStream);
    }

    public static void doPut(String url, String paramString, int timeout, OutputStream outputStream)
            throws IOException {
        doPut(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                timeout,
                null,
                StandardCharsets.UTF_8,
                outputStream);
    }

    public static void doPut(
            HttpClient client,
            String url,
            String paramString,
            int timeout,
            Header[] headers,
            Charset charset,
            OutputStream outputStream)
            throws IOException {
        doPut(client, url, null, paramString, timeout, headers, charset, outputStream);
    }

    public static void doPutRetry(
            String url,
            Map<String, String> paramMap,
            String paramString,
            int timeout,
            OutputStream outputStream)
            throws IOException {
        doPut(
                ClientSupport.httpSyncRetryClient,
                url,
                paramMap,
                paramString,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8,
                outputStream);
    }

    private static void doPut(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            String paramString,
            int timeout,
            Header[] headers,
            Charset charset,
            OutputStream outputStream)
            throws IOException {
        HttpSyncClient.execute(
                client,
                HttpMethod.PUT,
                url,
                paramMap,
                paramString,
                null,
                timeout,
                headers,
                charset,
                outputStream);
    }

    // ==============================PUT-End==============================

    // ==============================PATCH-Beg==============================

    public static String doPatch(String url) throws IOException {
        return doPatch(url, new HashMap<>());
    }

    public static String doPatch(String url, int timeout) throws IOException {
        return doPatch(url, new HashMap<>(), timeout);
    }

    public static String doPatch(String url, Map<String, String> paramMap) throws IOException {
        return doPatch(url, paramMap, Integer.MIN_VALUE);
    }

    public static String doPatch(String url, Map<String, String> paramMap, int timeout)
            throws IOException {
        return doPatch(
                ClientSupport.httpSyncClient,
                url,
                paramMap,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    public static String doPatch(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            int timeout,
            Header[] headers,
            Charset charset)
            throws IOException {
        return doPatch(client, url, paramMap, null, timeout, headers, charset);
    }

    public static String doPatch(String url, String paramString) throws IOException {
        return doPatch(url, paramString, Integer.MIN_VALUE);
    }

    public static String doPatch(String url, String paramString, int timeout) throws IOException {
        return doPatch(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    public static String doPatch(
            HttpClient client,
            String url,
            String paramString,
            int timeout,
            Header[] headers,
            Charset charset)
            throws IOException {
        return doPatch(client, url, null, paramString, timeout, headers, charset);
    }

    public static String doPatchRetry(
            String url, Map<String, String> paramMap, String paramString, int timeout)
            throws IOException {
        return doPatch(
                ClientSupport.httpSyncRetryClient,
                url,
                paramMap,
                paramString,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    private static String doPatch(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            String paramString,
            int timeout,
            Header[] headers,
            Charset charset)
            throws IOException {
        return HttpSyncClient.execute(
                client,
                HttpMethod.PATCH,
                url,
                paramMap,
                paramString,
                null,
                timeout,
                headers,
                charset);
    }

    // ================================================================================

    public static void doPatch(String url, OutputStream outputStream) throws IOException {
        doPatch(url, new HashMap<>(), outputStream);
    }

    public static void doPatch(String url, int timeout, OutputStream outputStream)
            throws IOException {
        doPatch(url, new HashMap<>(), timeout, outputStream);
    }

    public static void doPatch(String url, Map<String, String> paramMap, OutputStream outputStream)
            throws IOException {
        doPatch(url, paramMap, Integer.MIN_VALUE, outputStream);
    }

    public static void doPatch(
            String url, Map<String, String> paramMap, int timeout, OutputStream outputStream)
            throws IOException {
        doPatch(
                ClientSupport.httpSyncClient,
                url,
                paramMap,
                timeout,
                null,
                StandardCharsets.UTF_8,
                outputStream);
    }

    public static void doPatch(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            int timeout,
            Header[] headers,
            Charset charset,
            OutputStream outputStream)
            throws IOException {
        doPatch(client, url, paramMap, null, timeout, headers, charset, outputStream);
    }

    public static void doPatch(String url, String paramString, OutputStream outputStream)
            throws IOException {
        doPatch(url, paramString, Integer.MIN_VALUE, outputStream);
    }

    public static void doPatch(
            String url, String paramString, int timeout, OutputStream outputStream)
            throws IOException {
        doPatch(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                timeout,
                null,
                StandardCharsets.UTF_8,
                outputStream);
    }

    public static void doPatch(
            HttpClient client,
            String url,
            String paramString,
            int timeout,
            Header[] headers,
            Charset charset,
            OutputStream outputStream)
            throws IOException {
        doPatch(client, url, null, paramString, timeout, headers, charset, outputStream);
    }

    public static void doPatchRetry(
            String url,
            Map<String, String> paramMap,
            String paramString,
            int timeout,
            OutputStream outputStream)
            throws IOException {
        doPatch(
                ClientSupport.httpSyncRetryClient,
                url,
                paramMap,
                paramString,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8,
                outputStream);
    }

    private static void doPatch(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            String paramString,
            int timeout,
            Header[] headers,
            Charset charset,
            OutputStream outputStream)
            throws IOException {
        HttpSyncClient.execute(
                client,
                HttpMethod.PATCH,
                url,
                paramMap,
                paramString,
                null,
                timeout,
                headers,
                charset,
                outputStream);
    }

    // ==============================PATCH-End==============================

    // ==============================DELETE-Beg==============================

    public static String doDelete(String url) throws IOException {
        return doDelete(url, new HashMap<>());
    }

    public static String doDelete(String url, int timeout) throws IOException {
        return doDelete(url, new HashMap<>(), timeout);
    }

    public static String doDelete(String url, Map<String, String> paramMap) throws IOException {
        return doDelete(url, paramMap, Integer.MIN_VALUE);
    }

    public static String doDelete(String url, Map<String, String> paramMap, int timeout)
            throws IOException {
        return doDelete(
                ClientSupport.httpSyncClient,
                url,
                paramMap,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    public static String doDelete(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            int timeout,
            Header[] headers,
            Charset charset)
            throws IOException {
        return doDelete(client, url, paramMap, null, timeout, headers, charset);
    }

    public static String doDelete(String url, String paramString) throws IOException {
        return doDelete(url, paramString, Integer.MIN_VALUE);
    }

    public static String doDelete(String url, String paramString, int timeout) throws IOException {
        return doDelete(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    public static String doDelete(
            HttpClient client,
            String url,
            String paramString,
            int timeout,
            Header[] headers,
            Charset charset)
            throws IOException {
        return doDelete(client, url, null, paramString, timeout, headers, charset);
    }

    public static String doDeleteRetry(
            String url, Map<String, String> paramMap, String paramString, int timeout)
            throws IOException {
        return doDelete(
                ClientSupport.httpSyncRetryClient,
                url,
                paramMap,
                paramString,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    private static String doDelete(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            String paramString,
            int timeout,
            Header[] headers,
            Charset charset)
            throws IOException {
        return HttpSyncClient.execute(
                client,
                HttpMethod.DELETE,
                url,
                paramMap,
                paramString,
                null,
                timeout,
                headers,
                charset);
    }

    // ================================================================================

    public static void doDelete(String url, OutputStream outputStream) throws IOException {
        doDelete(url, new HashMap<>(), outputStream);
    }

    public static void doDelete(String url, int timeout, OutputStream outputStream)
            throws IOException {
        doDelete(url, new HashMap<>(), timeout, outputStream);
    }

    public static void doDelete(String url, Map<String, String> paramMap, OutputStream outputStream)
            throws IOException {
        doDelete(url, paramMap, Integer.MIN_VALUE, outputStream);
    }

    public static void doDelete(
            String url, Map<String, String> paramMap, int timeout, OutputStream outputStream)
            throws IOException {
        doDelete(
                ClientSupport.httpSyncClient,
                url,
                paramMap,
                timeout,
                null,
                StandardCharsets.UTF_8,
                outputStream);
    }

    public static void doDelete(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            int timeout,
            Header[] headers,
            Charset charset,
            OutputStream outputStream)
            throws IOException {
        doDelete(client, url, paramMap, null, timeout, headers, charset, outputStream);
    }

    public static void doDelete(String url, String paramString, OutputStream outputStream)
            throws IOException {
        doDelete(url, paramString, Integer.MIN_VALUE, outputStream);
    }

    public static void doDelete(
            String url, String paramString, int timeout, OutputStream outputStream)
            throws IOException {
        doDelete(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                timeout,
                null,
                StandardCharsets.UTF_8,
                outputStream);
    }

    public static void doDelete(
            HttpClient client,
            String url,
            String paramString,
            int timeout,
            Header[] headers,
            Charset charset,
            OutputStream outputStream)
            throws IOException {
        doDelete(client, url, null, paramString, timeout, headers, charset, outputStream);
    }

    public static void doDeleteRetry(
            String url,
            Map<String, String> paramMap,
            String paramString,
            int timeout,
            OutputStream outputStream)
            throws IOException {
        doDelete(
                ClientSupport.httpSyncRetryClient,
                url,
                paramMap,
                paramString,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8,
                outputStream);
    }

    private static void doDelete(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            String paramString,
            int timeout,
            Header[] headers,
            Charset charset,
            OutputStream outputStream)
            throws IOException {
        HttpSyncClient.execute(
                client,
                HttpMethod.DELETE,
                url,
                paramMap,
                paramString,
                null,
                timeout,
                headers,
                charset,
                outputStream);
    }

    // ==============================DELETE-End==============================

    // ==============================OPTIONS-Beg==============================

    public static String doOptions(String url) throws IOException {
        return doOptions(url, new HashMap<>());
    }

    public static String doOptions(String url, int timeout) throws IOException {
        return doOptions(url, new HashMap<>(), timeout);
    }

    public static String doOptions(String url, Map<String, String> paramMap) throws IOException {
        return doOptions(url, paramMap, Integer.MIN_VALUE);
    }

    public static String doOptions(String url, Map<String, String> paramMap, int timeout)
            throws IOException {
        return doOptions(
                ClientSupport.httpSyncClient,
                url,
                paramMap,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    public static String doOptions(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            int timeout,
            Header[] headers,
            Charset charset)
            throws IOException {
        return doOptions(client, url, paramMap, null, timeout, headers, charset);
    }

    public static String doOptions(String url, String paramString) throws IOException {
        return doOptions(url, paramString, Integer.MIN_VALUE);
    }

    public static String doOptions(String url, String paramString, int timeout) throws IOException {
        return doOptions(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    public static String doOptions(
            HttpClient client,
            String url,
            String paramString,
            int timeout,
            Header[] headers,
            Charset charset)
            throws IOException {
        return doOptions(client, url, null, paramString, timeout, headers, charset);
    }

    public static String doOptionsRetry(
            String url, Map<String, String> paramMap, String paramString, int timeout)
            throws IOException {
        return doOptions(
                ClientSupport.httpSyncRetryClient,
                url,
                paramMap,
                paramString,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    private static String doOptions(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            String paramString,
            int timeout,
            Header[] headers,
            Charset charset)
            throws IOException {
        return HttpSyncClient.execute(
                client,
                HttpMethod.OPTIONS,
                url,
                paramMap,
                paramString,
                null,
                timeout,
                headers,
                charset);
    }

    // ================================================================================

    public static void doOptions(String url, OutputStream outputStream) throws IOException {
        doOptions(url, new HashMap<>(), outputStream);
    }

    public static void doOptions(String url, int timeout, OutputStream outputStream)
            throws IOException {
        doOptions(url, new HashMap<>(), timeout, outputStream);
    }

    public static void doOptions(
            String url, Map<String, String> paramMap, OutputStream outputStream)
            throws IOException {
        doOptions(url, paramMap, Integer.MIN_VALUE, outputStream);
    }

    public static void doOptions(
            String url, Map<String, String> paramMap, int timeout, OutputStream outputStream)
            throws IOException {
        doOptions(
                ClientSupport.httpSyncClient,
                url,
                paramMap,
                timeout,
                null,
                StandardCharsets.UTF_8,
                outputStream);
    }

    public static void doOptions(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            int timeout,
            Header[] headers,
            Charset charset,
            OutputStream outputStream)
            throws IOException {
        doOptions(client, url, paramMap, null, timeout, headers, charset, outputStream);
    }

    public static void doOptions(String url, String paramString, OutputStream outputStream)
            throws IOException {
        doOptions(url, paramString, Integer.MIN_VALUE, outputStream);
    }

    public static void doOptions(
            String url, String paramString, int timeout, OutputStream outputStream)
            throws IOException {
        doOptions(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                timeout,
                null,
                StandardCharsets.UTF_8,
                outputStream);
    }

    public static void doOptions(
            HttpClient client,
            String url,
            String paramString,
            int timeout,
            Header[] headers,
            Charset charset,
            OutputStream outputStream)
            throws IOException {
        doOptions(client, url, null, paramString, timeout, headers, charset, outputStream);
    }

    public static void doOptionsRetry(
            String url,
            Map<String, String> paramMap,
            String paramString,
            int timeout,
            OutputStream outputStream)
            throws IOException {
        doOptions(
                ClientSupport.httpSyncRetryClient,
                url,
                paramMap,
                paramString,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8,
                outputStream);
    }

    private static void doOptions(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            String paramString,
            int timeout,
            Header[] headers,
            Charset charset,
            OutputStream outputStream)
            throws IOException {
        HttpSyncClient.execute(
                client,
                HttpMethod.OPTIONS,
                url,
                paramMap,
                paramString,
                null,
                timeout,
                headers,
                charset,
                outputStream);
    }

    // ==============================OPTIONS-End==============================

    // ==============================TRACE-Beg==============================

    public static String doTrace(String url) throws IOException {
        return doTrace(url, new HashMap<>());
    }

    public static String doTrace(String url, int timeout) throws IOException {
        return doTrace(url, new HashMap<>(), timeout);
    }

    public static String doTrace(String url, Map<String, String> paramMap) throws IOException {
        return doTrace(url, paramMap, Integer.MIN_VALUE);
    }

    public static String doTrace(String url, Map<String, String> paramMap, int timeout)
            throws IOException {
        return doTrace(
                ClientSupport.httpSyncClient,
                url,
                paramMap,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    public static String doTrace(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            int timeout,
            Header[] headers,
            Charset charset)
            throws IOException {
        return doTrace(client, url, paramMap, null, timeout, headers, charset);
    }

    public static String doTrace(String url, String paramString) throws IOException {
        return doTrace(url, paramString, Integer.MIN_VALUE);
    }

    public static String doTrace(String url, String paramString, int timeout) throws IOException {
        return doTrace(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    public static String doTrace(
            HttpClient client,
            String url,
            String paramString,
            int timeout,
            Header[] headers,
            Charset charset)
            throws IOException {
        return doTrace(client, url, null, paramString, timeout, headers, charset);
    }

    public static String doTraceRetry(
            String url, Map<String, String> paramMap, String paramString, int timeout)
            throws IOException {
        return doTrace(
                ClientSupport.httpSyncRetryClient,
                url,
                paramMap,
                paramString,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8);
    }

    private static String doTrace(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            String paramString,
            int timeout,
            Header[] headers,
            Charset charset)
            throws IOException {
        return HttpSyncClient.execute(
                client,
                HttpMethod.TRACE,
                url,
                paramMap,
                paramString,
                null,
                timeout,
                headers,
                charset);
    }

    // ================================================================================

    public static void doTrace(String url, OutputStream outputStream) throws IOException {
        doTrace(url, new HashMap<>(), outputStream);
    }

    public static void doTrace(String url, int timeout, OutputStream outputStream)
            throws IOException {
        doTrace(url, new HashMap<>(), timeout, outputStream);
    }

    public static void doTrace(String url, Map<String, String> paramMap, OutputStream outputStream)
            throws IOException {
        doTrace(url, paramMap, Integer.MIN_VALUE, outputStream);
    }

    public static void doTrace(
            String url, Map<String, String> paramMap, int timeout, OutputStream outputStream)
            throws IOException {
        doTrace(
                ClientSupport.httpSyncClient,
                url,
                paramMap,
                timeout,
                null,
                StandardCharsets.UTF_8,
                outputStream);
    }

    public static void doTrace(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            int timeout,
            Header[] headers,
            Charset charset,
            OutputStream outputStream)
            throws IOException {
        doTrace(client, url, paramMap, null, timeout, headers, charset, outputStream);
    }

    public static void doTrace(String url, String paramString, OutputStream outputStream)
            throws IOException {
        doTrace(url, paramString, Integer.MIN_VALUE, outputStream);
    }

    public static void doTrace(
            String url, String paramString, int timeout, OutputStream outputStream)
            throws IOException {
        doTrace(
                ClientSupport.httpSyncClient,
                url,
                paramString,
                timeout,
                null,
                StandardCharsets.UTF_8,
                outputStream);
    }

    public static void doTrace(
            HttpClient client,
            String url,
            String paramString,
            int timeout,
            Header[] headers,
            Charset charset,
            OutputStream outputStream)
            throws IOException {
        doTrace(client, url, null, paramString, timeout, headers, charset, outputStream);
    }

    public static void doTraceRetry(
            String url,
            Map<String, String> paramMap,
            String paramString,
            int timeout,
            OutputStream outputStream)
            throws IOException {
        doTrace(
                ClientSupport.httpSyncRetryClient,
                url,
                paramMap,
                paramString,
                timeout,
                HeaderSupport.KEEP_ALIVE_ENCODED_HEADERS,
                StandardCharsets.UTF_8,
                outputStream);
    }

    private static void doTrace(
            HttpClient client,
            String url,
            Map<String, String> paramMap,
            String paramString,
            int timeout,
            Header[] headers,
            Charset charset,
            OutputStream outputStream)
            throws IOException {
        HttpSyncClient.execute(
                client,
                HttpMethod.TRACE,
                url,
                paramMap,
                paramString,
                null,
                timeout,
                headers,
                charset,
                outputStream);
    }

    // ==============================TRACE-End==============================

}
