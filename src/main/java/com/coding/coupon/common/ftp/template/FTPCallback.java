/*
 * 文件名称：FTPCallback.java
 * 系统名称：[系统名称]
 * 模块名称：FTP回调
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180622 22:35
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180622-01         Rushing0711     M201806222235 新建文件
 ********************************************************************************/
package com.coding.coupon.common.ftp.template;

import com.coding.coupon.common.ftp.exception.FTPException;
import org.apache.commons.net.ftp.FTPClient;

/**
 * FTP回调.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180622 22:36</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public interface FTPCallback<T> {

    /**
     * FTP回调方法.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180623 00:29</font><br>
     * [请在此输入功能详述]
     *
     * @param ftpClient - 无需关注连接与关闭的ftpClient实例
     * @return T - 返回结果
     * @author Rushing0711
     * @since 1.0.0
     */
    T doInCallback(FTPClient ftpClient) throws FTPException;
}
