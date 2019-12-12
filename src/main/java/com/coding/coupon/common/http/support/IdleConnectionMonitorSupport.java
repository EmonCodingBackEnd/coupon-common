/*
 * 文件名称：IdleConnectionMonitorSupport.java
 * 系统名称：[系统名称]
 * 模块名称：IdleConnection检测支持
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180609 14:54
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180609-01         Rushing0711     M201806091454 新建文件
 ********************************************************************************/
package com.coding.coupon.common.http.support;

import com.coding.coupon.common.http.HttpConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.HttpClientConnectionManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * IdleConnection检测支持.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180609 14:54</font><br>
 * [请在此输入功能详述]
 *
 * @deprecated 4.5版本变动很大，研究过，发现行不通，官方推荐使用{@linkplain
 *     org.apache.http.impl.client.HttpClientBuilder#setKeepAliveStrategy(ConnectionKeepAliveStrategy)}
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
@Slf4j
@Deprecated
public class IdleConnectionMonitorSupport {

    @PostConstruct
    public void init() {
        if (HttpConfig.activeIdleConnectionMonitor) {
            log.info("【IdleConnection检测】守护线程启动");
            HttpClientConnectionManager connMgr =
                    (HttpClientConnectionManager)
                            ClientSupport.httpSyncClient.getConnectionManager();
            IdleConnectionMonitorThread monitorThread = new IdleConnectionMonitorThread(connMgr);
            monitorThread.setDaemon(true);
            monitorThread.start();
        }
    }

    public static class IdleConnectionMonitorThread extends Thread {

        private final HttpClientConnectionManager connMgr;
        private volatile boolean shutdown;

        public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr) {
            super();
            this.connMgr = connMgr;
        }

        @Override
        public void run() {
            try {
                while (!shutdown) {
                    synchronized (this) {
                        wait(5000);
                        // Close expired connections
                        connMgr.closeExpiredConnections();
                        // Optionally, close connections that have been idle longer than 60 sec
                        connMgr.closeIdleConnections(60, TimeUnit.SECONDS);
                    }
                }
            } catch (InterruptedException ex) {
                log.error("【IdleConnection检测】执行异常", ex);
            }
        }

        public void shutdown() {
            shutdown = true;
            synchronized (this) {
                notifyAll();
            }
        }
    }
}
