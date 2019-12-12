/*
 * 文件名称：RedisLock.java
 * 系统名称：[系统名称]
 * 模块名称：Redis锁
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180517 11:18
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180517-01         Rushing0711     M201805171118 新建文件
 ********************************************************************************/
package com.coding.coupon.common.cache.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Redis锁.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180517 11:18</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
@Slf4j
public class RedisLock {

    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 加锁.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180516 13:20</font><br>
     * [请在此输入功能详述]
     *
     * @param key -
     * @param value - 当前时间 + 超时时间
     * @return boolean true:加锁成功;false:加锁失败
     * @author Rushing0711
     * @since 1.0.0
     */
    public boolean lock(String key, String value) {
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }
        String currentValue = redisTemplate.opsForValue().get(key);
        // 如果锁过期
        if ((!StringUtils.isEmpty(currentValue)
                && Long.parseLong(currentValue) < System.currentTimeMillis())) {
            // 获取上一个锁的时间
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180516 13:20</font><br>
     * [请在此输入功能详述]
     *
     * @param key -
     * @param value - 加锁时提供的值
     * @author Rushing0711
     * @since 1.0.0
     */
    public void unlock(String key, String value) {
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("【redis分布式锁】解锁异常", e);
        }
    }
}
