package com.coding.coupon.common.cache.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 缘起于高韩.
 *
 * <p>创建时间: <font style="color:#00FFFF">20181112 09:52</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@Service
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class CacheUtils {

    private static StringRedisTemplate stringRedisTemplate;

    private static RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        CacheUtils.stringRedisTemplate = stringRedisTemplate;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        CacheUtils.redisTemplate = redisTemplate;
    }

    public static void deleteKey(String key) {
        if (stringRedisTemplate.hasKey(key)) {
            stringRedisTemplate.delete(String.valueOf(key));
            log.info("【缓存】删除缓存信息, key={}", key);
        } else {
            log.info("【缓存】删除缓存信息, 缓存不存在, key={}", key);
        }
    }

    public static void deleteHashKey(String key, String hashKey) {
        if (redisTemplate.opsForHash().hasKey(key, hashKey)) {
            redisTemplate.opsForHash().delete(key, hashKey);
            log.info("【缓存】删除缓存信息, key={}, hashKey", key, hashKey);
        } else {
            log.info("【缓存】删除缓存信息, 缓存不存在, key={}, hakey={}", key, hashKey);
        }
    }
}
