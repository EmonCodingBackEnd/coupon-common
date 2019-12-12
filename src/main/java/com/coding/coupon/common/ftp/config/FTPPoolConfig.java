/*
 * 文件名称：FTPPoolConfig.java
 * 系统名称：[系统名称]
 * 模块名称：FTPPool配置类
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：配置FTP连接池相关属性
 * 开发人员：Rushing0711
 * 创建日期：20180622 13:26
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180622-01         Rushing0711     M201806221326 新建文件
 ********************************************************************************/
package com.coding.coupon.common.ftp.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * FTPPool配置类.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180622 13:27</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Component
@ConditionalOnClass({GenericKeyedObjectPool.class, FTPClient.class})
@ConditionalOnProperty(value = "ftp.enabled", havingValue = "true")
@ConfigurationProperties(prefix = "ftp.pool")
@Slf4j
public class FTPPoolConfig extends GenericKeyedObjectPoolConfig {

    private long timeBetweenEvictionRunsMillis = 60000L;
    private int maxTotal;
    private int maxTotalPerKey;
    private int minIdlePerKey;
    private int maxIdlePerKey;
    private long maxWaitMillis;

    @PostConstruct
    public void init() {
        log.info("【FTP线程池配置】{}", this);
    }
}
