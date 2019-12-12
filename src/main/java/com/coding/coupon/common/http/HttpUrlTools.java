/*
 * 文件名称：HttpUtils.java
 * 系统名称：[系统名称]
 * 模块名称：HTTP工具类
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180604 18:45
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180604-01         Rushing0711     M201806041845 新建文件
 ********************************************************************************/
package com.coding.coupon.common.http;

import com.coding.coupon.common.http.property.HttpMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

/**
 * HTTP工具类.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180604 18:45</font><br>
 * 缺点：不太容易支持SSL。
 *
 * @deprecated 不推荐使用JDK自带的HttpURLConnection直接使用
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@Deprecated
public abstract class HttpUrlTools {

    private static final int TIMEOUT_IN_MILLIONS = 5000;

    public interface CallBack {
        void onRequestComplete(String result);
    }

    /**
     * 异步的Get请求
     *
     * @param urlStr -
     * @param callBack -
     */
    public static void doGetAsyn(final String urlStr, final CallBack callBack) {
        new Thread(
                        () -> {
                            try {
                                String result = doGet(urlStr);
                                if (callBack != null) {
                                    callBack.onRequestComplete(result);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        })
                .start();
    }

    /**
     * 异步的Post请求
     *
     * @param urlStr
     * @param params
     * @param callBack
     * @throws Exception
     */
    public static void doPostAsyn(final String urlStr, final String params, final CallBack callBack)
            throws Exception {
        new Thread(
                        () -> {
                            try {
                                String result = doPost(urlStr, params);
                                if (callBack != null) {
                                    callBack.onRequestComplete(result);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        })
                .start();
    }

    /**
     * 向指定URL发送GET方法的请求.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180604 17:53</font><br>
     * [请在此输入功能详述]
     *
     * @param urlStr - 发送请求的URL
     * @return java.lang.String
     * @author Rushing0711
     * @since 1.0.0
     */
    public static String doGet(String urlStr) {
        return doGet(urlStr, false);
    }

    /**
     * 向指定URL发送GET方法的请求.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180604 17:53</font><br>
     * [请在此输入功能详述]
     *
     * @param urlStr - 发送请求的URL
     * @param needDecode - 指url是否需要URLDecoder.decode解码
     * @return java.lang.String
     * @author Rushing0711
     * @since 1.0.0
     */
    public static String doGet(String urlStr, boolean needDecode) {
        if (StringUtils.isEmpty(urlStr)) {
            throw new IllegalArgumentException("request urlStr must be not null or empty string");
        }
        String result = "";

        HttpURLConnection connection = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            String requestUrl = urlStr;
            if (needDecode) {
                requestUrl = URLDecoder.decode(requestUrl, "UTF-8");
            }

            URL realUrl = new URL(requestUrl);
            connection = (HttpURLConnection) realUrl.openConnection();
            connection.setConnectTimeout(TIMEOUT_IN_MILLIONS);
            connection.setReadTimeout(TIMEOUT_IN_MILLIONS);
            connection.setRequestMethod(HttpMethod.GET.name());
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty(
                    "user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                baos = new ByteArrayOutputStream();

                int len;
                byte[] buf = new byte[1024];
                while ((len = is.read(buf)) != -1) {
                    baos.write(buf, 0, len);
                }
                baos.flush();
                result = baos.toString();
            } else {
                throw new RuntimeException(
                        String.format("responseCode=%s", connection.getResponseCode()));
            }
        } catch (Exception e) {
            log.error("【HTTP请求】method=GET,请求异常", e);
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    log.error("【HTTP请求】method=GET,流关闭异常", e);
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("【HTTP请求】method=GET,流关闭异常", e);
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }

    /**
     * 向指定的URL发送POST方法的请求.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180604 18:39</font><br>
     * [请在此输入功能详述]
     *
     * @param urlStr - 发送请求的URL
     * @param param - 请求参数
     * @return java.lang.String
     * @author Rushing0711
     * @since 1.0.0
     */
    public static String doPost(String urlStr, String param) {
        return doPost(urlStr, param, false);
    }

    /**
     * 向指定的URL发送POST方法的请求.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180604 18:39</font><br>
     * [请在此输入功能详述]
     *
     * @param urlStr - 发送请求的URL
     * @param param - 请求参数
     * @param needDecode - 指url是否需要URLDecoder.decode解码
     * @return java.lang.String
     * @author Rushing0711
     * @since 1.0.0
     */
    public static String doPost(String urlStr, String param, boolean needDecode) {
        if (StringUtils.isEmpty(urlStr)) {
            throw new IllegalArgumentException("request urlStr must be not null or empty string");
        }

        PrintWriter out = null;
        BufferedReader in = null;
        StringBuffer result = new StringBuffer();
        try {
            String requestUrl = urlStr;
            if (needDecode) {
                requestUrl = URLDecoder.decode(requestUrl, "UTF-8");
            }

            URL realUrl = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setConnectTimeout(TIMEOUT_IN_MILLIONS);
            connection.setReadTimeout(TIMEOUT_IN_MILLIONS);
            connection.setRequestMethod(HttpMethod.POST.name());
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("charset", "utf-8");
            connection.setUseCaches(false);
            connection.setRequestProperty(
                    "user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            connection.setDoOutput(true);
            connection.setDoInput(true);

            if (StringUtils.isEmpty(param)) {
                out = new PrintWriter(connection.getOutputStream());
                out.print(param);
                out.flush();
            }

            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            log.error("【HTTP请求】method=POST,请求异常", e);
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error("【HTTP请求】method=POST,流关闭异常", e);
                }
            }
        }
        return result.toString();
    }
}
