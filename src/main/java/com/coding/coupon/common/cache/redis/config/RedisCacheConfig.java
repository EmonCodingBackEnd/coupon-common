/*
 * 文件名称：RedisConfig.java
 * 系统名称：[系统名称]
 * 模块名称：Redis配置类
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180516 15:02
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180516-01         Rushing0711     M201805161502 新建文件
 ********************************************************************************/
package com.coding.coupon.common.cache.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis配置类.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180516 15:03</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@EnableCaching
@Configuration
public class RedisCacheConfig {

    /**
     * 配置RedisTemplate.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180516 18:32</font><br>
     * 设置添加序列化器<br>
     * key 使用string序列化器<br>
     * value 使用json序列化器
     *
     * @return org.springframework.data.redis.core.RedisTemplate<java.lang.String,java.lang.Object>
     * @author Rushing0711
     * @since 1.0.0
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // 设置key的serializer
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer =
                new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        // 设置value的serializer
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    //    @Bean
    //    public CacheManager cacheManager(RedisTemplate redisTemplate) {
    //        /*return new RedisCacheManager(redisTemplate);*/
    //        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
    //        /*// 多个缓存的名称,目前只定义了一个
    //        rcm.setCacheNames(Arrays.asList("thisredis"));*/
    //
    //        // 设置需要缓存过期的时间，没有设置过期时间的cacheName默认是永不过期
    //        Map<String, Long> expireMap = new HashMap<>();
    //        expireMap.put("userCache", 72000L);
    //        rcm.setExpires(expireMap);
    //
    //        /*//设置缓存默认过期时间(秒)
    //        rcm.setDefaultExpiration(600);*/
    //        return rcm;
    //    }
}
