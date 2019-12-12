package com.coding.coupon.common.ftp.config;

import lombok.Data;
import org.apache.commons.net.ftp.FTPClient;

import java.nio.charset.StandardCharsets;

@Data
public class ServerConfig {

    private static final String DEFAULT_ACCESS_URL_PREFIX = "";
    private static final int DEFAULT_POST = 21;

    // 如果不配置，默认采用host作为别名
    private String alias;

    private String host;

    private int port = DEFAULT_POST;

    private String username;

    private String password;

    // FTP服务器被动模式
    private String passiveMode;

    private String encoding = StandardCharsets.UTF_8.name();

    private int connectTimeout = 5000;

    private int dataTimeout = 3000;

    private int transferFileType = FTPClient.BINARY_FILE_TYPE;

    // 访问上传的文件时，url前缀，比如 http://file.emon.vip/ 或者 http://192.168.1.116:80/
    private String accessUrlPrefixes = DEFAULT_ACCESS_URL_PREFIX;

    @Override
    public String toString() {
        return "ServerConfig{"
                + "alias='"
                + alias
                + '\''
                + ", host='"
                + host
                + '\''
                + ", port="
                + port
                + ", username='"
                + username
                + '\''
                + ", passiveMode='"
                + passiveMode
                + '\''
                + ", encoding='"
                + encoding
                + '\''
                + ", connectTimeout="
                + connectTimeout
                + ", dataTimeout="
                + dataTimeout
                + ", transferFileType="
                + transferFileType
                + ", accessUrlPrefixes='"
                + accessUrlPrefixes
                + '\''
                + '}';
    }
}
