package com.bench.service;

import org.springframework.data.redis.core.RedisCallback;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface IRedisService<T> {

    // 基础的操作
    void set(String key, T value);

    void set(String key, T value, long time);

    T get(String key);

    void delete(String key);

    void delete(Collection<String> keys);

    boolean expire(String key, long time);

    Long getExpire(String key);

    boolean hasKey(String key);

    Long increment(String key, long delta);

    Long decrement(String key, long delta);

    // set 操作
    void addSet(String key, T value);

    Set<T> getSet(String key);

    void deleteSet(String key, T value);

    // List 操作

    void lPush(String key, T value);

    T lPop(String key);

    void rPush(String key, T value);

    T rPop(String key);

    List<T> lRange(String key, long start, long end);

    // Hash 操作
    void hSet(String key, String hashKey, T value);

    T hGet(String key, String hashKey);

    void hDelete(String key, String... hashKeys);

    Map<Object, Object> hGetAll(String key);

    boolean hHasKey(String key, String hashKey);

    // Sorted set 操作

    void zAdd(String key, T value, double score);

    Set<T> zRange(String key, long start, long end);

    void zRemove(String key, T value);

    Long zCard(String key);

    // 其他操作，

    boolean renameKey(String oldKey, String newKey);

    boolean persistKey(String key);

    void setWithTimeout(String key, T value, long timeout, TimeUnit unit);

    boolean setIfAbsent(String key, T value);

    T execute(RedisCallback<T> redisCallback);

}
