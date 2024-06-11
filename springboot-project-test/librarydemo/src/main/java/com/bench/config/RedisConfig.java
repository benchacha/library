package com.bench.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        // 设置键（key）的序列化器
        template.setKeySerializer(new StringRedisSerializer());
        // 设置哈希键（hash key）的序列化器
        template.setHashKeySerializer(new StringRedisSerializer());
        // 设置值（value）的序列化器
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 设置哈希值（hash value）的序列化器
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }
}
