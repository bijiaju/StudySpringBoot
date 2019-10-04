/*
package com.bee.springboot.annotation.disributedlock;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

*/
/**
 分布式锁的应用
 *//*

@Component
public class StartBatchSendTask {
    private final String REDIS_PREFIX = "StartBatchSendTask:task:";

    private static Logger LOG = LoggerFactory.getLogger(StartBatchSendTask.class);

    @Autowired
    private BatchSendTaskService batchSendTaskService;

    @Autowired
    private DistributedLock lock;

    @Scheduled(initialDelay = 10000, fixedRate = 15 * 1000)
    @Async
    public void start() {
        List<xx> tasks = batchSendTaskService.query(null, null, BatchSendTaskStatus.WAITING.getCode(), null, new Date(), null,1);
        LOG.info("[{}] will start.", StringUtils.collectionToCommaDelimitedString(CollectionUtil.pick(tasks, t -> ((BatchSendTask) t).getTitle())));
        for (BatchSendTaskVM task : tasks) {
            //分布式锁 开始
            String lockKey = REDIS_PREFIX + task.getId();
            if (lock.lock(lockKey)) {
                try {
                    batchSendTaskService.start(task.getId());
                    LOG.info("[{}] starts.", task.getTitle());
                } catch (Exception e) {
                    LOG.error("An error occurs while starting {} task.", task.getTitle(), e);
                } finally {
                    lock.unlock(lockKey);
                }
            } else {
                LOG.warn("Lock task {} failed.", task.getTitle());
            }
        }
    }
}
*/
