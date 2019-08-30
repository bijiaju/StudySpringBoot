package com.bee.springboot.annotation.disributedlock;


import com.bee.springboot.util.AopUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;
/**
 * 分布式同步
 *
 * @author weichunhe
 * created at 2018/11/30
 */
@Aspect
@Component("synConfiguration")
public class SynchronizedConfiguration {

    private static Logger LOG = LoggerFactory.getLogger(SynchronizedConfiguration.class);

    private String LOCK_PREFIX = "synchronized:";

    @Autowired
    private DistributedLock lock;

    @Pointcut("@annotation(com.bee.springboot.annotation.disributedlock.Synchronized)")
    public void pointcut() {
    }

    private String getKey(JoinPoint joinPoint, Synchronized sync) {
        String key = sync.value();
        if (StringUtils.isEmpty(key)) {
            key = AopUtil.getFullMethodName(joinPoint);
        }
        return LOCK_PREFIX + key;
    }

    @Around("pointcut()&&@annotation(sync)")
    public void around(ProceedingJoinPoint joinPoint, Synchronized sync) {
        String key = getKey(joinPoint, sync);
        String value = UUID.randomUUID().toString();
        String methodName = AopUtil.getFullMethodName(joinPoint);
        boolean locked = lock.lock(key, value, sync.timeoutSeconds());
        if (locked) {
            LOG.info("lock {} to {}", key, value);
            try {
                joinPoint.proceed(joinPoint.getArgs());
            } catch (Throwable throwable) { //NOSONAR
                LOG.error("An error occurs while executing {} ", methodName, throwable);
            } finally {
                unlock(key, value);
            }
        } else {
            LOG.warn("It will do nothing because lock {} failed.", methodName);
        }
    }

    public void unlock(String key, String value) {
        if (lock.unlock(key, value)) {
            LOG.info("unlock {} from {}", key, value);
        } else {
            LOG.warn("unlock {} form {} failed.Maybe something is wrong.");
        }
    }
}
