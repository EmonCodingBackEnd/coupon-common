/*
 * 文件名称：FilenameResult.java
 * 系统名称：[系统名称]
 * 模块名称：文件名匹配结果
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180625 23:05
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180625-01         Rushing0711     M201806252305 新建文件
 ********************************************************************************/
package com.coding.coupon.common.regex.result;

import com.coding.coupon.common.regex.RegexResult;

/**
 * 文件名匹配结果.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180625 23:06</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public class FilenameResult extends RegexResult {
    private String filename;
    private String prefix;
    private String suffix;
    private boolean hasSuffix;

    public static FilenameResult instance() {
        return new FilenameResult();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public boolean isHasSuffix() {
        return hasSuffix;
    }

    public void setHasSuffix(boolean hasSuffix) {
        this.hasSuffix = hasSuffix;
    }
}
