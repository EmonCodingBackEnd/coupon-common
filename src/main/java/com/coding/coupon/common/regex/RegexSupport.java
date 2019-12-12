/*
 * 文件名称：RegexSupport.java
 * 系统名称：[系统名称]
 * 模块名称：正则表达式匹配解析类
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180424 15:05
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180424-01         Rushing0711     M201804241505 新建文件
 ********************************************************************************/
package com.coding.coupon.common.regex;

import com.coding.coupon.common.regex.result.*;

import java.util.regex.Matcher;

/**
 * 正则表达式匹配解析类.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180424 15:10</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class RegexSupport {

    /**
     * 示例1：完全匹配的正则表达式定义.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180424 15:06</font><br>
     * [请在此输入功能详述]
     *
     * @param value - 引用变量
     * @return com.ishanshan.regex.RegexResult 解析结果
     * @author Rushing0711
     * @since 1.0.0
     */
    public static RegexResult match$R(String value) {
        RegexResult result = RegexResult.instance();
        Matcher matcher = RegexDefine.$R_REGEX_PATTERN.matcher(value);
        if (matcher.matches()) {
            result.setMatched(true);
            result.setRegex(RegexDefine.$R_REGEX);
            result.setPattern(RegexDefine.$R_REGEX_PATTERN);
            result.setMatcher(matcher);
            result.setRawValue(matcher.group(0));
            result.setResult("refKey", matcher.group(2));
        }
        return result;
    }

    /**
     * 示例2：部分匹配的正则表达式定义.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180424 15:09</font><br>
     * [请在此输入功能详述]
     *
     * @param value - 格式化的日志
     * @return com.ishanshan.regex.RegexResult 解析结果
     * @author Rushing0711
     * @since 1.0.0
     */
    public static RegexResult matchLogFormater(String value) {
        RegexResult result = RegexResult.instance();
        Matcher matcher = RegexDefine.LOG_FORMATER_REGEX_PATTERN.matcher(value);
        boolean found = false;
        int index = 0;
        String formattedValue = null;
        while (matcher.find()) {
            formattedValue = matcher.replaceFirst(String.format("{%d}", index++));
            matcher.reset(formattedValue);
            found = true;
        }
        if (found) {
            result.setMatched(true);
            result.setRegex(RegexDefine.LOG_FORMATER_REGEX);
            result.setPattern(RegexDefine.LOG_FORMATER_REGEX_PATTERN);
            matcher.reset(value);
            result.setMatcher(matcher);
            result.setRawValue(value);
            result.setResult("formattedValue", formattedValue);
        }
        return result;
    }

    /**
     * 用户手机号正则表达式定义.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180424 15:06</font><br>
     *
     * <p>示例：
     *
     * @param value - 引用变量
     * @return com.ishanshan.regex.RegexResult 解析结果
     * @author Rushing0711
     * @since 1.0.0
     */
    public static MobileRegexResult matchMobile(String value) {
        MobileRegexResult result = MobileRegexResult.instance();
        Matcher matcher = RegexDefine.MOBILE_REGEX_PATTERN.matcher(value);
        if (matcher.matches()) {
            result.setMatched(true);
            result.setRegex(RegexDefine.MOBILE_REGEX);
            result.setPattern(RegexDefine.MOBILE_REGEX_PATTERN);
            result.setMatcher(matcher);
            result.setMobile(matcher.group(0));
            result.setMobileHead(matcher.group(1));
            result.setMobileTail(matcher.group(2));
        }
        return result;
    }

    public static UrlRegexResult matchUrl(String value) {
        UrlRegexResult result = UrlRegexResult.instance();
        Matcher matcher = RegexDefine.URL_REGEX_PATTERN.matcher(value);
        if (matcher.matches()) {
            result.setMatched(true);
            result.setRegex(RegexDefine.URL_REGEX);
            result.setPattern(RegexDefine.URL_REGEX_PATTERN);
            result.setMatcher(matcher);
            result.setUrl(matcher.group(0));
            result.setUri(matcher.group(1));
            result.setSchema(matcher.group(2));
            result.setPath(matcher.group(3));
            result.setParam(matcher.group(4));
        }
        return result;
    }

    public static UriRegexResult matchUri(String value) {
        UriRegexResult result = UriRegexResult.instance();
        Matcher matcher = RegexDefine.URI_REGEX_PATTERN.matcher(value);
        if (matcher.matches()) {
            result.setMatched(true);
            result.setRegex(RegexDefine.URI_REGEX);
            result.setPattern(RegexDefine.URI_REGEX_PATTERN);
            result.setMatcher(matcher);
            result.setUri(matcher.group(0));
            result.setSchema(matcher.group(1));
            result.setPath(matcher.group(2));
        }
        return result;
    }

    public static UrlParamRegexResult matchUrlParam(String value) {
        UrlParamRegexResult result = UrlParamRegexResult.instance();
        Matcher matcher = RegexDefine.URL_PARAM_REGEX_PATTERN.matcher(value);
        if (matcher.matches()) {
            result.setMatched(true);
            result.setRegex(RegexDefine.URL_PARAM_REGEX);
            result.setPattern(RegexDefine.URL_PARAM_REGEX_PATTERN);
            result.setMatcher(matcher);
            result.setParam(matcher.group(1));
        }
        return result;
    }

    private static void fetchFilenameResult(FilenameResult result, Matcher matcher) {
        if (matcher.group(3) != null) {
            result.setHasSuffix(false);
            result.setPrefix(matcher.group(3));
        } else {
            result.setHasSuffix(true);
            result.setPrefix(matcher.group(1));
            result.setSuffix(matcher.group(2));
        }
    }

    public static FilenameResult matchStrictFilename(String value) {
        FilenameResult result = FilenameResult.instance();
        Matcher matcher = RegexDefine.STRICT_FILENAME_REGEX_PATTERN.matcher(value);
        if (matcher.matches()) {
            result.setMatched(true);
            result.setRegex(RegexDefine.STRICT_FILENAME_REGEX);
            result.setPattern(RegexDefine.STRICT_FILENAME_REGEX_PATTERN);
            result.setMatcher(matcher);
            result.setFilename(matcher.group(0));
            fetchFilenameResult(result, matcher);
        }
        return result;
    }

    public static FilenameResult matchFilename(String value) {
        FilenameResult result = FilenameResult.instance();
        Matcher matcher = RegexDefine.FILENAME_REGEX_PATTERN.matcher(value);
        boolean found = false;
        while (matcher.find()) {
            found = true;
            break;
        }
        if (found) {
            result.setMatched(true);
            result.setRegex(RegexDefine.FILENAME_REGEX);
            result.setPattern(RegexDefine.FILENAME_REGEX_PATTERN);
            result.setMatcher(matcher);
            result.setFilename(matcher.group(0));
            fetchFilenameResult(result, matcher);
        }
        return result;
    }

    public static FilenameResult matchImage(String value) {
        FilenameResult result = FilenameResult.instance();
        Matcher matcher = RegexDefine.IMAGE_REGEX_PATTERN.matcher(value);
        boolean found = false;
        while (matcher.find()) {
            found = true;
            break;
        }
        if (found) {
            result.setMatched(true);
            result.setRegex(RegexDefine.IMAGE_REGEX);
            result.setPattern(RegexDefine.IMAGE_REGEX_PATTERN);
            result.setMatcher(matcher);
            result.setFilename(matcher.group(0));
            result.setHasSuffix(true);
            result.setPrefix(matcher.group(1));
            result.setSuffix(matcher.group(2));
        }
        return result;
    }

    public static FilenameResult matchAudio(String value) {
        FilenameResult result = FilenameResult.instance();
        Matcher matcher = RegexDefine.AUDIO_REGEX_PATTERN.matcher(value);
        boolean found = false;
        while (matcher.find()) {
            found = true;
            break;
        }
        if (found) {
            result.setMatched(true);
            result.setRegex(RegexDefine.AUDIO_REGEX);
            result.setPattern(RegexDefine.AUDIO_REGEX_PATTERN);
            result.setMatcher(matcher);
            result.setFilename(matcher.group(0));
            result.setHasSuffix(true);
            result.setPrefix(matcher.group(1));
            result.setSuffix(matcher.group(2));
        }
        return result;
    }

    public static FilenameResult matchVedio(String value) {
        FilenameResult result = FilenameResult.instance();
        Matcher matcher = RegexDefine.VEDIO_REGEX_PATTERN.matcher(value);
        boolean found = false;
        while (matcher.find()) {
            found = true;
            break;
        }
        if (found) {
            result.setMatched(true);
            result.setRegex(RegexDefine.VEDIO_REGEX);
            result.setPattern(RegexDefine.VEDIO_REGEX_PATTERN);
            result.setMatcher(matcher);
            result.setFilename(matcher.group(0));
            result.setHasSuffix(true);
            result.setPrefix(matcher.group(1));
            result.setSuffix(matcher.group(2));
        }
        return result;
    }
}
