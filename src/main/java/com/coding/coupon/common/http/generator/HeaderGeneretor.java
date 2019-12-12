/*
 * 文件名称：HeaderGeneretor.java
 * 系统名称：[系统名称]
 * 模块名称：Http请求头Header生成器
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180608 12:16
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180608-01         Rushing0711     M201806081216 新建文件
 ********************************************************************************/
package com.coding.coupon.common.http.generator;

import com.coding.coupon.common.http.property.HttpRequestHeader;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.util.HashMap;
import java.util.Map;

/**
 * Http请求头Header生成器.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180608 12:17</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public class HeaderGeneretor {

    private Map<String, Header> headerMap = new HashMap<>();

    private HeaderGeneretor() {}

    public static HeaderGeneretor custom() {
        return new HeaderGeneretor();
    }

    private void put(String key, String value) {
        headerMap.put(key, new BasicHeader(key, value));
    }

    /**
     * 指定客户端能够接收的内容类型.
     *
     * @param accept - 例如：Accept: text/plain, text/html
     * @return -
     */
    public HeaderGeneretor accept(String accept) {
        put(HttpRequestHeader.ACCEPT, accept);
        return this;
    }

    /**
     * 指定客户端可以接收的字符编码集.
     *
     * @param acceptCharset - 例如：Accept-Charset: iso-8859-5
     * @return -
     */
    public HeaderGeneretor acceptCharset(String acceptCharset) {
        put(HttpRequestHeader.ACCEPT_CHARSET, acceptCharset);
        return this;
    }

    /**
     * 指定客户端可以支持的Web服务器返回内容压缩编码类型
     *
     * @param acceptEncoding - 例如：Accept-Encoding: compress, gzip
     * @return -
     */
    public HeaderGeneretor acceptEncoding(String acceptEncoding) {
        put(HttpRequestHeader.ACCEPT_ENCODING, acceptEncoding);
        return this;
    }

    /**
     * 指定客户端可以接收的语言
     *
     * @param acceptLanguage - 例如：Accept-Language: en,zh
     * @return -
     */
    public HeaderGeneretor acceptLanguage(String acceptLanguage) {
        put(HttpRequestHeader.ACCEPT_LANGUAGE, acceptLanguage);
        return this;
    }

    /**
     * 指定客户端可以请求网页实体的一个或多个子范围字段.
     *
     * @param acceptRanges - 例如：Accept-Ranges: bytes
     * @return -
     */
    public HeaderGeneretor acceptRanges(String acceptRanges) {
        put(HttpRequestHeader.ACCEPT_RANGES, acceptRanges);
        return this;
    }

    /**
     * HTTP授权的授权证书.
     *
     * @param authorization - 例如：Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==
     * @return -
     */
    public HeaderGeneretor authorization(String authorization) {
        put(HttpRequestHeader.AUTHORIZATION, authorization);
        return this;
    }

    /**
     * 指定请求和相应遵循的缓存机制.
     *
     * @param cacheControl - 例如：Cache-Control: no-cache
     * @return -
     */
    public HeaderGeneretor cacheControl(String cacheControl) {
        put(HttpRequestHeader.CACHE_CONTROL, cacheControl);
        return this;
    }

    /**
     * 表示是否需要持久连接（HTTP1.1默认持久连接）
     *
     * @param connection - 例如：Connection:close 短连接；Connection:keep-alive 长连接
     * @return -
     */
    public HeaderGeneretor connection(String connection) {
        put(HttpRequestHeader.CONNECTION, connection);
        return this;
    }

    /**
     * HTTP请求发送时，会把保存在该请求域名下的所有cookie值一起发送给Web服务器.
     *
     * @param cookie - 例如：Cookie: $Version=1; Skin=new;
     * @return -
     */
    public HeaderGeneretor cookie(String cookie) {
        put(HttpRequestHeader.COOKIE, cookie);
        return this;
    }

    /**
     * 请求内容长度.
     *
     * @param contentLength - 例如：Content-Length: 348
     * @return -
     */
    public HeaderGeneretor contentLength(String contentLength) {
        put(HttpRequestHeader.CONTENT_LENGTH, contentLength);
        return this;
    }

    /**
     * 请求的与实体对应的MIME类型.
     *
     * @param contentType - 例如：Content-Type: application/x-www-form-urlencoded
     * @return -
     */
    public HeaderGeneretor contentType(String contentType) {
        put(HttpRequestHeader.CONTENT_TYPE, contentType);
        return this;
    }

    /**
     * 请求发送的日期和时间.
     *
     * @param date - 例如：Date: Tue, 15 Nov 2010 08:12:31 GMT
     * @return -
     */
    public HeaderGeneretor date(String date) {
        put(HttpRequestHeader.DATE, date);
        return this;
    }

    /**
     * 请求的特定的服务器行为
     *
     * @param expect - 例如：Expect: 100-continue
     * @return -
     */
    public HeaderGeneretor expect(String expect) {
        put(HttpRequestHeader.EXPECT, expect);
        return this;
    }

    /**
     * 发出请求的用户的Email
     *
     * @param from - 例如：From: user@email.com
     * @return -
     */
    public HeaderGeneretor from(String from) {
        put(HttpRequestHeader.FROM, from);
        return this;
    }

    /**
     * 指定请求的服务器的域名和端口号
     *
     * @param host - 例如：Host: www.zcmhi.com
     * @return -
     */
    public HeaderGeneretor host(String host) {
        put(HttpRequestHeader.HOST, host);
        return this;
    }

    /**
     * 只有请求内容与实体相匹配才有效
     *
     * @param ifMatch - 例如：If-Match: “737060cd8c284d8af7ad3082f209582d”
     * @return -
     */
    public HeaderGeneretor ifMatch(String ifMatch) {
        put(HttpRequestHeader.IF_MATCH, ifMatch);
        return this;
    }

    /**
     * 如果请求的部分在指定时间之后被修改则请求成功，未被修改则返回304代码
     *
     * @param ifModifiedSince - 例如：If-Modified-Since: Sat, 29 Oct 2010 19:43:31 GMT
     * @return -
     */
    public HeaderGeneretor ifModifiedSince(String ifModifiedSince) {
        put(HttpRequestHeader.IF_MODIFIED_SINCE, ifModifiedSince);
        return this;
    }

    /**
     * 如果内容未改变返回304代码，参数为服务器先前发送的Etag，与服务器回应的Etag比较判断是否改变
     *
     * @param ifNoneMatch - 例如：If-None-Match: “737060cd8c284d8af7ad3082f209582d”
     * @return -
     */
    public HeaderGeneretor ifNoneMatch(String ifNoneMatch) {
        put(HttpRequestHeader.IF_NONE_MATCH, ifNoneMatch);
        return this;
    }

    /**
     * 如果实体未改变，服务器发送客户端丢失的部分，否则发送整个实体。参数也为Etag
     *
     * @param ifRange - 例如：If-Range: “737060cd8c284d8af7ad3082f209582d”
     * @return -
     */
    public HeaderGeneretor ifRange(String ifRange) {
        put(HttpRequestHeader.IF_RANGE, ifRange);
        return this;
    }

    /**
     * 只在实体在指定时间之后未被修改才请求成功
     *
     * @param ifUnmodifiedSince - 例如：If-Unmodified-Since: Sat, 29 Oct 2010 19:43:31 GMT
     * @return -
     */
    public HeaderGeneretor ifUnmodifiedSince(String ifUnmodifiedSince) {
        put(HttpRequestHeader.IF_UNMODIFIED_SINCE, ifUnmodifiedSince);
        return this;
    }

    /**
     * 限制信息通过代理和网关传送的时间
     *
     * @param maxForwards - 例如：Max-Forwards: 10
     * @return -
     */
    public HeaderGeneretor maxForwards(String maxForwards) {
        put(HttpRequestHeader.MAX_FORWARDS, maxForwards);
        return this;
    }

    /**
     * 用来包含实现特定的指令
     *
     * @param pragma - 例如：Pragma: no-cache
     * @return -
     */
    public HeaderGeneretor pragma(String pragma) {
        put(HttpRequestHeader.PRAGMA, pragma);
        return this;
    }

    /**
     * 连接到代理的授权证书
     *
     * @param proxyAuthorization - 例如：Proxy-Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==
     * @return -
     */
    public HeaderGeneretor proxyAuthorization(String proxyAuthorization) {
        put(HttpRequestHeader.PROXY_AUTHORIZATION, proxyAuthorization);
        return this;
    }

    /**
     * 只请求实体的一部分，指定范围
     *
     * @param range - 例如：Range: bytes=500-999
     * @return -
     */
    public HeaderGeneretor range(String range) {
        put(HttpRequestHeader.RANGE, range);
        return this;
    }

    /**
     * 先前网页的地址，当前请求网页紧随其后,即来路
     *
     * @param referer - 例如：Referer: http://www.zcmhi.com/archives/71.html
     * @return -
     */
    public HeaderGeneretor referer(String referer) {
        put(HttpRequestHeader.REFERER, referer);
        return this;
    }

    /**
     * 客户端愿意接受的传输编码，并通知服务器接受接受尾加头信息
     *
     * @param te - 例如：TE:trailers,deflate;q=0.5
     * @return -
     */
    public HeaderGeneretor te(String te) {
        put(HttpRequestHeader.TE, te);
        return this;
    }

    /**
     * 向服务器指定某种传输协议以便服务器进行转换（如果支持）
     *
     * @param upgrade - 例如：Upgrade: HTTP/2.0, SHTTP/1.3, IRC/6.9, RTA/x11
     * @return -
     */
    public HeaderGeneretor upgrade(String upgrade) {
        put(HttpRequestHeader.UPGRADE, upgrade);
        return this;
    }

    /**
     * User-Agent的内容包含发出请求的用户信息
     *
     * <p>示例：User-Agent: Mozilla/5.0 (Linux; X11)
     *
     * @param userAgent -
     * @return -
     */
    public HeaderGeneretor userAgent(String userAgent) {
        put(HttpRequestHeader.USER_AGENT, userAgent);
        return this;
    }
    /**
     * 通知中间网关或代理服务器地址，通信协议
     *
     * @param via - 例如：Via: 1.0 fred, 1.1 nowhere.com (Apache/1.1)
     * @return -
     */
    public HeaderGeneretor via(String via) {
        put(HttpRequestHeader.VIA, via);
        return this;
    }

    /**
     * 关于消息实体的警告信息.
     *
     * @param warning - 例如：Warn: 199 Miscellaneous warning
     * @return -
     */
    public HeaderGeneretor warning(String warning) {
        put(HttpRequestHeader.WARNING, warning);
        return this;
    }

    /**
     * 设置此HTTP连接的持续时间.
     *
     * @param keepAlive - 例如：Keep-Alive:300
     * @return -
     */
    public HeaderGeneretor keepAlive(String keepAlive) {
        put(HttpRequestHeader.KEEP_ALIVE, keepAlive);
        return this;
    }

    private String get(String key) {
        if (headerMap.containsKey(key)) {
            return headerMap.get(key).getValue();
        }
        return null;
    }

    /** 指定客户端能够接收的内容类型. */
    public String accept() {
        return get(HttpRequestHeader.ACCEPT);
    }

    /** 指定客户端可以接收的字符编码集. */
    public String acceptCharset() {
        return get(HttpRequestHeader.ACCEPT_CHARSET);
    }

    /** 指定客户端可以支持的Web服务器返回内容压缩编码类型 */
    public String acceptEncoding() {
        return get(HttpRequestHeader.ACCEPT_ENCODING);
    }

    /** 指定客户端可以接收的语言 */
    public String acceptLanguage() {
        return get(HttpRequestHeader.ACCEPT_LANGUAGE);
    }

    /** 指定客户端可以请求网页实体的一个或多个子范围字段. */
    public String acceptRanges() {
        return get(HttpRequestHeader.ACCEPT_RANGES);
    }

    /** HTTP授权的授权证书. */
    public String authorization() {
        return get(HttpRequestHeader.AUTHORIZATION);
    }

    /** 指定请求和相应遵循的缓存机制. */
    public String cacheControl() {
        return get(HttpRequestHeader.CACHE_CONTROL);
    }

    /** 表示是否需要持久连接（HTTP1.1默认持久连接） */
    public String connection() {
        return get(HttpRequestHeader.CONNECTION);
    }

    /** HTTP请求发送时，会把保存在该请求域名下的所有cookie值一起发送给Web服务器. */
    public String cookie() {
        return get(HttpRequestHeader.COOKIE);
    }

    /** 请求内容长度. */
    public String contentLength() {
        return get(HttpRequestHeader.CONTENT_LENGTH);
    }

    /** 请求的与实体对应的MIME类型. */
    public String contentType() {

        return get(HttpRequestHeader.CONTENT_TYPE);
    }

    /** 请求发送的日期和时间. */
    public String date() {
        return get(HttpRequestHeader.DATE);
    }

    /** 请求的特定的服务器行为 */
    public String expect() {
        return get(HttpRequestHeader.EXPECT);
    }

    /** 发出请求的用户的Email */
    public String from() {
        return get(HttpRequestHeader.FROM);
    }

    /** 指定请求的服务器的域名和端口号 */
    public String host() {
        return get(HttpRequestHeader.HOST);
    }

    /** 只有请求内容与实体相匹配才有效 */
    public String ifMatch() {
        return get(HttpRequestHeader.IF_MATCH);
    }

    /** 如果请求的部分在指定时间之后被修改则请求成功，未被修改则返回304代码 */
    public String ifModifiedSince() {
        return get(HttpRequestHeader.IF_MODIFIED_SINCE);
    }

    /** 如果内容未改变返回304代码，参数为服务器先前发送的Etag，与服务器回应的Etag比较判断是否改变 */
    public String ifNoneMatch() {
        return get(HttpRequestHeader.IF_NONE_MATCH);
    }

    /** 如果实体未改变，服务器发送客户端丢失的部分，否则发送整个实体。参数也为Etag */
    public String ifRange() {
        return get(HttpRequestHeader.IF_RANGE);
    }

    /** 只在实体在指定时间之后未被修改才请求成功 */
    public String ifUnmodifiedSince() {
        return get(HttpRequestHeader.IF_UNMODIFIED_SINCE);
    }

    /** 限制信息通过代理和网关传送的时间 */
    public String maxForwards() {
        return get(HttpRequestHeader.MAX_FORWARDS);
    }

    /** 用来包含实现特定的指令 */
    public String pragma() {
        return get(HttpRequestHeader.PRAGMA);
    }

    /** 连接到代理的授权证书 */
    public String proxyAuthorization() {
        return get(HttpRequestHeader.PROXY_AUTHORIZATION);
    }

    /** 只请求实体的一部分，指定范围 */
    public String range() {
        return get(HttpRequestHeader.RANGE);
    }

    /** 先前网页的地址，当前请求网页紧随其后,即来路 */
    public String referer() {
        return get(HttpRequestHeader.REFERER);
    }

    /** 客户端愿意接受的传输编码，并通知服务器接受接受尾加头信息 */
    public String te() {
        return get(HttpRequestHeader.TE);
    }

    /** 向服务器指定某种传输协议以便服务器进行转换（如果支持） */
    public String upgrade() {
        return get(HttpRequestHeader.UPGRADE);
    }

    /** User-Agent的内容包含发出请求的用户信息 */
    public String userAgent() {
        return get(HttpRequestHeader.USER_AGENT);
    }

    /** 通知中间网关或代理服务器地址，通信协议 */
    public String via() {
        return get(HttpRequestHeader.VIA);
    }

    /** 关于消息实体的警告信息. */
    public String warning() {
        return get(HttpRequestHeader.WARNING);
    }

    public String keepAlive() {
        return get(HttpRequestHeader.KEEP_ALIVE);
    }

    public Header[] build() {
        Header[] headers = new Header[headerMap.size()];
        int i = 0;
        for (Header header : headerMap.values()) {
            headers[i++] = header;
        }
        headerMap.clear();
        headerMap = null;
        return headers;
    }
}
