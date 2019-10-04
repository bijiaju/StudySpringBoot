package com.bee.springboot.annotation.disributedlock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author bee
 * created at 18-11-27
 * 基于RedisTemplate的分布式锁,现在这个分布式锁是可以用的
 */
@Component("distriRedisTemlateLock")//这个加参数是有一位内和下面的DistributedLock重复了
public class DistributedLock {

    private static final String LOCK_PREFIX = "DistributedLock:";

    /**
     * default timeout is 5 minutes
     */
    private long DEFAULT_LOCK_TIMEOUT = 5 * 60L;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public RedisConnection getConnection() {
        return redisTemplate.getConnectionFactory().getConnection();
    }

    public boolean lock(String key) {
        return lock(key, "1", DEFAULT_LOCK_TIMEOUT);
    }

    public boolean unlock(String key) {
        return getConnection().del(getFullKey(key)) == 1;
    }

    /**
     * lock a key with a value
     *
     * @param key
     * @param value
     * @param timeoutSeconds
     * @return
     */
    public boolean lock(String key, String value, Long timeoutSeconds) {
        RedisConnection connection = getConnection();
        byte[] bytes = getFullKey(key);
        boolean locked = connection.setNX(bytes, value.getBytes());
        if (timeoutSeconds == null || timeoutSeconds < 0) {
            timeoutSeconds = DEFAULT_LOCK_TIMEOUT; //NOSONAR
        }
        long ttl = connection.ttl(bytes);
        //must reset ttl ,because if crashed here last time,It will be locked forever.
        if (ttl < 0 || ttl > timeoutSeconds) {
            connection.expire(bytes, timeoutSeconds);
        }
        return locked;
    }

    /**
     * only if the locked value equals to the param value ,you can unlock it .
     * It means that only the person who locked it can unlock it, until time out.
     *
     * @param key
     * @param value
     */
    public boolean unlock(String key, String value) {
        byte[] keyByte = getFullKey(key);
        if (keyByte == null) {
            return false;
        }
        if (!value.equals(new String(getConnection().get(keyByte)))) {
            return false;
        }
        return getConnection().del(keyByte) == 1;
    }

    private byte[] getFullKey(String key) {
        return (LOCK_PREFIX + key).getBytes();
    }
}
