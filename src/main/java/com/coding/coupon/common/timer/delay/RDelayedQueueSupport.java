package com.coding.coupon.common.timer.delay;

import com.coding.coupon.common.cache.redis.AppRedisKey;
import com.coding.coupon.common.timer.TimerPoolConfig;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@ConditionalOnBean({TimerPoolConfig.class, TimerPoolConfig.Delay.class})
@Component
@Slf4j
public class RDelayedQueueSupport {

    @Autowired private TimerPoolConfig timerPoolConfig;
    private ThreadPoolTaskExecutor delayQueueExecutor;

    private static RBlockingQueue<DelayTask> blockingFairQueue;
    private static RDelayedQueue<DelayTask> delayedQueue;

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    private void setRedissonClient(RedissonClient redissonClient) {
        blockingFairQueue =
                redissonClient.getBlockingQueue(AppRedisKey.DELAY_QUEUE.getKey(profile));
        delayedQueue = redissonClient.getDelayedQueue(blockingFairQueue);
    }

    // 定义私有构造器，防止被外部初始化
    private RDelayedQueueSupport() {}

    public static void put(DelayTask delayTask, long timeout, TimeUnit timeUnit) {
        log.info("【延时任务队列】任务已加入延迟队列,taskId={}", delayTask.getTaskId());
        delayedQueue.offer(delayTask, timeout, timeUnit);
    }

    @PostConstruct
    private void init() {
        log.info("【初始化分布式延时任务队列守护线程】开始......");
        delayQueueExecutor = timerPoolConfig.getDelayQueueExecutor();

        Thread daemonThread = new Thread(this::execute);
        daemonThread.setDaemon(true);
        daemonThread.setName(timerPoolConfig.getDelay().getDelayTaskQueueDaemonThreadName());
        daemonThread.start();
        log.info("【初始化分布式延时任务队列守护线程】完成......");
    }

    private void execute() {
        log.info("【分布式延时任务队列守护线程】开启,thread={}", Thread.currentThread().getId());
        while (true) {
            // 阻塞式获取
            try {
                DelayTask task = blockingFairQueue.take();
                if (task == null) {
                    continue;
                }
                log.info("【分布式延时任务队列】任务已提取并加入线程池,taskId={}", task.getTaskId());
                delayQueueExecutor.execute(task);
            } catch (InterruptedException e) {
                log.error("【分布式延时任务队列守护线程】异常", e);
                break;
            }
        }
        log.info("【分布式延时任务队列守护线程】关闭,thread={}", Thread.currentThread().getId());
    }
}
