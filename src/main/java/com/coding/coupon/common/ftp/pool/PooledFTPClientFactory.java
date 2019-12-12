/*
 * 文件名称：PooledFTPClientFactory.java
 * 系统名称：[系统名称]
 * 模块名称：池化的FTPClient工厂
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：池化的FTPClient工厂，基于FTPClient创建对象
 * 开发人员：Rushing0711
 * 创建日期：20180622 12:15
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180622-01         Rushing0711     M201806221215 新建文件
 ********************************************************************************/
package com.coding.coupon.common.ftp.pool;

import com.coding.coupon.common.ftp.config.ServerConfig;
import com.coding.coupon.common.ftp.exception.FTPException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPCmd;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.pool2.KeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.TimeZone;

/**
 * 池化的FTPClient工厂.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180622 12:16</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
public class PooledFTPClientFactory implements KeyedPooledObjectFactory<ServerConfig, FTPClient> {

    /** FTP匿名用户. */
    private static final String ANONYMOUS = "anonymous";

    @Override
    public PooledObject<FTPClient> makeObject(ServerConfig key) throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setBufferSize(1024 * 2);
        FTPClientConfig ftpClientConfig = new FTPClientConfig();
        ftpClientConfig.setServerTimeZoneId(TimeZone.getDefault().getID());
        ftpClient.setControlEncoding(key.getEncoding());
        ftpClient.configure(ftpClientConfig);
        ftpClient.setConnectTimeout(key.getConnectTimeout());
        ftpClient.setDataTimeout(key.getDataTimeout());
        try {
            ftpClient.connect(key.getHost(), key.getPort());
        } catch (IOException e) {
            log.error(String.format("【FTP】连接异常, %s:%s", key.getHost(), key.getPort()), e);
            throw e;
        }
        int reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftpClient.disconnect();
            log.error("【FTP】连接失败,reply={},{}:{}", reply, key.getHost(), key.getPort());
            throw new FTPException(
                    String.format("【FTP】连接失败,reply=%s,%s:%s", reply, key.getHost(), key.getPort()));
        }
        log.info("【FTP】连接成功,reply={},{}:{}", reply, key.getHost(), key.getPort());

        boolean success;
        String username;
        if (StringUtils.isEmpty(key.getUsername())) {
            success = ftpClient.login(ANONYMOUS, ANONYMOUS);
            username = ANONYMOUS;
        } else {
            success = ftpClient.login(key.getUsername(), key.getPassword());
            username = key.getUsername();
        }
        if (!success) {
            ftpClient.disconnect();
            log.error("【FTP】登录失败,username={}", username);
            throw new FTPException(String.format("【FTP】登录失败,username=%s", username));
        }
        log.info("【FTP】登录成功,username={}", username);

        ftpClient.setFileType(key.getTransferFileType());
        if (Boolean.valueOf(key.getPassiveMode())) {
            ftpClient.enterLocalPassiveMode();
        }
        return new DefaultPooledObject<>(ftpClient);
    }

    @Override
    public void destroyObject(ServerConfig key, PooledObject<FTPClient> p) throws Exception {
        FTPClient ftpClient = p.getObject();
        if (ftpClient != null) {
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }

    @Override
    public boolean validateObject(ServerConfig key, PooledObject<FTPClient> p) {
        FTPClient ftpClient = p.getObject();
        if (ftpClient != null) {
            boolean isConnected = ftpClient.isConnected();
            boolean isAvailable = ftpClient.isAvailable();
            if (!isConnected && !isAvailable) {
                log.info("【FTP】连接失效,{}:{}", key.getHost(), key.getPort());
                return false;
            }
            try {
                // 心跳检测
                ftpClient.sendCommand(FTPCmd.SYSTEM);
                return true;
            } catch (IOException e) {
                log.error(String.format("【FTP】连接校验失败,%s:%s", key.getHost(), key.getPort()), e);
                return false;
            }
        }
        return false;
    }

    @Override
    public void activateObject(ServerConfig key, PooledObject<FTPClient> p) throws Exception {}

    @Override
    public void passivateObject(ServerConfig key, PooledObject<FTPClient> p) throws Exception {}
}
