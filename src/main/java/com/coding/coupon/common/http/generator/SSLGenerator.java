/*
 * 文件名称：SSLGenerator.java
 * 系统名称：[系统名称]
 * 模块名称：SSL生成器
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180607 22:26
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180607-01         Rushing0711     M201806072226 新建文件
 ********************************************************************************/
package com.coding.coupon.common.http.generator;

import com.coding.coupon.common.http.HttpException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.ssl.SSLContexts;
import org.springframework.util.Assert;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/** SSL生成器 */
@Slf4j
public class SSLGenerator {
    private static final SSLHandler simpleVerifier = new SSLHandler();

    private SSLConnectionSocketFactory sslConnFactory;

    private SSLContext sslContext;

    /**
     * 返回自定义实例
     *
     * @return -
     */
    public static SSLGenerator custom() {
        return new SSLGenerator();
    }

    /**
     * 设置为默认ssl
     *
     * @return
     */
    public synchronized SSLGenerator ssl() {
        try {
            sslContext = SSLContext.getInstance("SSLv3");
        } catch (NoSuchAlgorithmException e) {
            throw new HttpException(e);
        }
        return this;
    }

    /**
     * 自定义ssl
     *
     * @param keyStorePath - 密钥库路径
     * @param keyStorePass - 密钥库密码
     * @return -
     */
    public synchronized SSLGenerator ssl(String keyStorePath, String keyStorePass) {
        ssl(KeyStore.getDefaultType(), keyStorePath, keyStorePass);
        return this;
    }

    /**
     * 自定义ssl
     *
     * @param keyStoreType - 密钥库类型
     * @param keyStorePath - 密钥库路径
     * @param keyStorePass - 密钥库密码
     * @return -
     */
    public synchronized SSLGenerator ssl(
            String keyStoreType, String keyStorePath, String keyStorePass) {
        Assert.notNull(keyStorePath, "keyStorePath must be not null");
        Assert.notNull(keyStorePass, "keyStorePass must be not null");
        KeyStore keyStore;
        InputStream inputStream = null;

        try {
            keyStore = KeyStore.getInstance(keyStoreType);
            inputStream = this.getClass().getClassLoader().getResourceAsStream(keyStorePath);
            keyStore.load(inputStream, keyStorePass.toCharArray());
            // 相信提供的CA/自签名证书
            if (KeyStore.getDefaultType().equals(keyStoreType)) {
                sslContext =
                        SSLContexts.custom()
                                .loadTrustMaterial(keyStore, new TrustSelfSignedStrategy())
                                .build();
            } else {
                sslContext =
                        SSLContexts.custom()
                                .loadKeyMaterial(keyStore, keyStorePass.toCharArray())
                                .build();
                sslConnFactory =
                        new SSLConnectionSocketFactory(
                                sslContext,
                                new String[] {"TLSv1"},
                                null,
                                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            }
        } catch (KeyStoreException
                | IOException
                | NoSuchAlgorithmException
                | CertificateException
                | KeyManagementException
                | UnrecoverableKeyException e) {
            throw new HttpException(e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                log.error("【SSLGenerator】流关闭失败", e);
            }
        }
        return this;
    }

    /**
     * 返回证书上下文sslContext.
     *
     * @return -
     */
    private synchronized SSLContext getSSLContext() {
        try {
            if (sslContext == null) {
                // sslContext = SSLContexts.createSystemDefault();
                sslContext = SSLContext.getInstance("SSLv3");
            }
            return sslContext;
        } catch (NoSuchAlgorithmException e) {
            throw new HttpException(e);
        }
    }

    /**
     * 返回sslConnFactory实例，具有什么样的证书校验(CA/自签/默认校验)取决于SSLGenerator实例。
     *
     * @return -
     */
    public synchronized SSLConnectionSocketFactory getSslConnFactory() {
        if (sslConnFactory != null) {
            return sslConnFactory;
        }
        try {
            SSLContext sslContext = getSSLContext();
            sslContext.init(null, new TrustManager[] {simpleVerifier}, null);
            sslConnFactory = new SSLConnectionSocketFactory(sslContext, simpleVerifier);
        } catch (KeyManagementException e) {
            throw new HttpException(e);
        }
        return sslConnFactory;
    }

    /**
     * 信任主机
     *
     * @return -
     */
    public static HostnameVerifier getVerifier() {
        return simpleVerifier;
    }

    /** 重写X509TrustManager类的三个方法，信任服务器证书 */
    private static class SSLHandler implements X509TrustManager, HostnameVerifier {
        @Override
        public boolean verify(String s, SSLSession sslSession) {
            return true;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s)
                throws CertificateException {}

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s)
                throws CertificateException {}

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}
