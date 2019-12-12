/*
 * 文件名称：AppRedisKey.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190327 18:09
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190327-01         Rushing0711     M201903271809 新建文件
 ********************************************************************************/
package com.coding.coupon.common.cache.redis;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Slf4j
public enum AppRedisKey implements AppDynamicRedisKey {
    /** 获取动态key的实例对象. */
    DYNAMIC("DYNAMIC", "示例"),
    EXAMPLE("serverName", "groupName", "example", "示例"),
    DELAY_QUEUE("DelayQueue", "延迟队列的名称"),
    MANAGE_PARAM_CACHE("manage", "param", "cache", "系统参数缓存"),
    ;

    private List<String> keyParts = new ArrayList<>();
    private String description;

    /** 不建议使用该构造器，仅为DYNAMIC使用而定义. */
    AppRedisKey(String serverName, String description) {
        keyParts.add(StringUtils.trimLeadingWhitespace(serverName));
        this.description = description;
    }

    AppRedisKey(String serverName, String groupName, String purpose, String description) {
        keyParts.add(StringUtils.trimLeadingWhitespace(serverName));
        keyParts.add(StringUtils.trimLeadingWhitespace(groupName));
        keyParts.add(StringUtils.trimLeadingWhitespace(purpose));
        this.description = description;
    }

    @Override
    public String getKey() {
        valid();
        return combineKeyParts(true, keyParts.toArray(new String[] {}));
    }

    @Override
    public String getKey(String keyVar) {
        valid();
        List<String> newKeyParts = new ArrayList<>(keyParts);
        newKeyParts.add(StringUtils.trimLeadingWhitespace(keyVar));
        return combineKeyParts(false, newKeyParts.toArray(new String[] {}));
    }

    private void valid() {
        if (this == DYNAMIC) {
            log.error("枚举常量 DYNAMIC 是调用父类定义的方法动态获取RedisKey的中间常量，不支持直接调用getKey()方法");
            throw new RuntimeException(
                    "枚举常量 DYNAMIC 是调用父类定义的方法动态获取RedisKey的中间常量，不支持直接调用getKey()方法");
        }
    }
}
