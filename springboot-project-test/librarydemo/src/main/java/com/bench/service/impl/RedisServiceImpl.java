package com.bench.service.impl;

import com.bench.service.IRedisService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl<T> implements IRedisService<T> {

    @Resource
    private RedisTemplate<String, T> redisTemplate;

    @Override
    // set key value
    // 在 Redis 中：SET key value
    public void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    // setex key seconds value
    // 在 Redis 中：SETEX key seconds value
    public void set(String key, T value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    @Override
    // get key
    // 在 Redis 中：GET key
    public T get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    // del key
    // 在 Redis 中：DEL key
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    // del key1 key2 ...
    // 在 Redis 中：DEL key1 key2 ...
    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    @Override
    // expire key seconds
    // 在 Redis 中：EXPIRE key seconds
    public boolean expire(String key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    @Override
    // ttl key
    // 在 Redis 中：TTL key
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    @Override  // exists key, 查询key值是否存在
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    // incr key
    // 在 Redis 中：INCRBY key delta
    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    // decr key
    // 在 Redis 中：DECRBY key delta
    public Long decrement(String key, long delta) {
        return redisTemplate.opsForValue().decrement(key, delta);
    }

    @Override
    // sadd key value
    // 在 Redis 中：SADD key value
    public void addSet(String key, T value) {
        redisTemplate.opsForSet().add(key, value);
    }

    @Override
    // smembers key
    // 在 Redis 中：SMEMBERS key
    public Set<T> getSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    // srem key value
    // 在 Redis 中：SREM key value
    public void deleteSet(String key, T value) {
        redisTemplate.opsForSet().remove(key, value);
    }

    @Override
    // lpush key value
    // 在 Redis 中：LPUSH key value
    public void lPush(String key, T value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    // lpop key
    // 在 Redis 中：LPOP key
    public T lPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public void rPush(String key, T value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    @Override
    public T rPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    @Override
    // lrange key start stop
    // 在 Redis 中：LRANGE key start stop
    public List<T> lRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    @Override
    // hset key field value
    // 在 Redis 中：HSET key field value
    public void hSet(String key, String hashKey, T value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    // hget key field
    // 在 Redis 中：HGET key field
    public T hGet(String key, String hashKey) {
        return (T)redisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    // hdel key field1 [field2]
    // 在 Redis 中：HDEL key field1 [field2]
    public void hDelete(String key, String... hashKeys) {
        redisTemplate.opsForHash().delete(key, hashKeys);
    }

    @Override
    // hgetall key
    // 在 Redis 中：HGETALL key
    public Map<Object, Object> hGetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    // hexists key field
    // 在 Redis 中：HEXISTS key field
    public boolean hHasKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    @Override
    // zadd key score member
    // 在 Redis 中：ZADD key score member
    public void zAdd(String key, T value, double score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    @Override
    // zrange key start stop
    // 在 Redis 中：ZRANGE key start stop
    public Set<T> zRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    @Override
    // zrem key member
    // 在 Redis 中：ZREM key member
    public void zRemove(String key, T value) {
        redisTemplate.opsForZSet().remove(key, value);
    }

    @Override
    // zcard key
    // 在 Redis 中：ZCARD key
    public Long zCard(String key) {
        return redisTemplate.opsForZSet().zCard(key);
    }

    @Override
    // rename key newkey
    // 在 Redis 中：RENAME oldkey newkey
    public boolean renameKey(String oldKey, String newKey) {
        try {
            redisTemplate.rename(oldKey, newKey);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    // persist key
    // 在 Redis 中：PERSIST key
    public boolean persistKey(String key) {
        try {
            return redisTemplate.persist(key);
        }catch (Exception e){
            return false;
        }
    }

    @Override
    // set key value ex timeout unit
    // 在 Redis 中：SET key value EX timeout (in the specified unit)
    public void setWithTimeout(String key, T value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    @Override
    // setnx key value
    // 在 Redis 中：SETNX key value
    public boolean setIfAbsent(String key, T value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    @Override
    // 执行Redis回调操作
    // 在 Redis 中：执行回调操作
    public T execute(RedisCallback<T> redisCallback) {
        return redisTemplate.execute(redisCallback);
    }
}
