/*
 * 文件名称：HttpSupport.java
 * 系统名称：[系统名称]
 * 模块名称：Http辅助支持类
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180607 15:44
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180607-01         Rushing0711     M201806071544 新建文件
 ********************************************************************************/
package com.coding.coupon.common.http.support;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.util.Assert;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Http辅助支持类.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180607 15:44</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class HttpSupport {

    /**
     * 检查url是否包含参数，如果有则截取并追加到参数List中，返回截取参数后的url.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180607 16:01</font><br>
     * [请在此输入功能详述]
     *
     * @param url - url，形如：http://www.baidu.com?key1=val1&key2=val2
     * @param pairList - 以NameValuePair<key,val>作为元素的List
     * @return java.lang.String - 处理后的url，http://www.baidu.com?key1=val1&key2=val2 <font
     *     style="color:#FF0000"></font>=> http://www.baidu.com
     * @author Rushing0711
     * @since 1.0.0
     */
    public static String checkHasParams(String url, List<NameValuePair> pairList) {
        Assert.notNull(url, "url must be not null");
        if (url.contains("?") && url.indexOf("?") < url.indexOf("=")) {
            Map<String, String> paramMap = buildParams(url.substring(url.indexOf("?") + 1));
            pairList.addAll(convertToPairList(paramMap));
            url = url.substring(0, url.indexOf("?"));
        }
        return url;
    }

    /**
     * 参数Map转换为List<NameValuePair>形式.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180607 16:13</font><br>
     * [请在此输入功能详述]
     *
     * @param paramMap - 参数Map
     * @return java.util.List<org.apache.http.NameValuePair>
     * @author Rushing0711
     * @since 1.0.0
     */
    public static List<NameValuePair> convertToPairList(Map<String, String> paramMap) {
        Assert.notNull(paramMap, "paramMap must be not null");
        List<NameValuePair> pairList = new ArrayList<>();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            pairList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return pairList;
    }

    /**
     * 构建参数Map.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180607 16:05</font><br>
     * [请在此输入功能详述]
     *
     * @param params - 字符串形式的参数，形如：key1=val1&key2=val2......
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @author Rushing0711
     * @since 1.0.0
     */
    public static Map<String, String> buildParams(String params) {
        String[] pms = params.split("&");
        String[][] pmss = new String[pms.length][2];
        int pos = 0;
        for (int i = 0; i < pms.length; i++) {
            pos = pms[i].indexOf("=");
            pmss[i][0] = pms[i].substring(0, pos);
            pmss[i][1] = pms[i].substring(pos + 1);
            pos = 0;
        }
        return buildParams(pmss);
    }

    /**
     * 构建参数Map.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180607 15:58</font><br>
     * [请在此输入功能详述]
     *
     * @param params - 二维数组形式的请求参数，形如：params = {{key1,val1},{key2,val2}......}
     * @return java.util.Map<java.lang.String,java.lang.String> - 构建的参数Map
     * @author Rushing0711
     * @since 1.0.0
     */
    public static Map<String, String> buildParams(String[][] params) {
        Map<String, String> map = new HashMap<>();
        for (String[] param : params) {
            map.put(param[0], param[1]);
        }
        return map;
    }

    public static String getNetContentSize(long size) {
        int GB = 1024 * 1024 * 1024; // 定义GB的计算常量
        int MB = 1024 * 1024; // 定义MB的计算常量
        int KB = 1024; // 定义KB的计算常量
        DecimalFormat decimalFormat = new DecimalFormat("#,###.000"); // 格式化小数
        String result = "";
        if (size / GB >= 1) {
            // 如果当前Byte的值大于等于1GB
            result = decimalFormat.format(size / (float) GB) + "GB";
        } else if (size / MB >= 1) {
            // 如果当前Byte的值大于等于1MB
            result = decimalFormat.format(size / (float) MB) + "MB";
        } else if (size / KB >= 1) {
            // 如果当前Byte的值大于等于1KB
            result = decimalFormat.format(size / (float) KB) + "KB";
        } else {
            if (size < 0) {
                result = "0B";
            } else {
                result = size + "B";
            }
        }
        return result;
    }
}
