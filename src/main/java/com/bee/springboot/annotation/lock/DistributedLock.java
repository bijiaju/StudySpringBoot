package com.bee.springboot.annotation.lock;

import com.bee.springboot.util.redis.RedisUtil;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCommands;

/**
 * @author bee
 * created at 18-11-27
 */
@Component
public class DistributedLock {

    private static final String LOCK_PREFIX = "DistributedLock:";

    /**
     * default timeout is 5 minutes
     */
    private Long DEFAULT_LOCK_TIMEOUT = 5 * 60L;



    public JedisCommands getConnection() {
        return RedisUtil.connect();
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
        JedisCommands connection = getConnection();
        String fullKey = getFullKey(key);
        boolean locked = connection.setnx(fullKey, value) > 0;
        if (timeoutSeconds == null || timeoutSeconds < 0) {
            timeoutSeconds = DEFAULT_LOCK_TIMEOUT;
        }
        long ttl = connection.ttl(fullKey);
        //must reset ttl ,because if crashed here last time,It will be locked forever.
        if (ttl < 0 || ttl > timeoutSeconds) {
            connection.expire(fullKey, timeoutSeconds.intValue());
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
        String realKey = getFullKey(key);
        if (realKey == null) {
            return false;
        }
        if (!value.equals(new String(getConnection().get(realKey)))) {
            return false;
        }
        return getConnection().del(realKey) == 1;
    }

    private String getFullKey(String key) {
        return (LOCK_PREFIX + key);
    }
}
