/*
 * 文件名称：GenericKeyedFTPClientPool.java
 * 系统名称：[系统名称]
 * 模块名称：<key,value>形式的FTPClient连接池
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180622 14:55
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180622-01         Rushing0711     M201806221455 新建文件
 ********************************************************************************/
package com.coding.coupon.common.ftp.pool;

import com.coding.coupon.common.ftp.config.ServerConfig;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.KeyedPooledObjectFactory;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;

/**
 * <key,value>形式的FTPClient连接池.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180622 14:56</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public class GenericKeyedFTPClientPool extends GenericKeyedObjectPool<ServerConfig, FTPClient> {

    public GenericKeyedFTPClientPool(
            KeyedPooledObjectFactory<ServerConfig, FTPClient> factory,
            GenericKeyedObjectPoolConfig config) {
        super(factory, config);
    }
}
