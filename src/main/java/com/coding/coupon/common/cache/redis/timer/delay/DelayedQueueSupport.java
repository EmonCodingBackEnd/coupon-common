/*
 * 文件名称：DelayedQueueSupport.java
 * 系统名称：[系统名称]
 * 模块名称：延时队列守护线程
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180515 13:36
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180515-01         Rushing0711     M201805151336 新建文件
 ********************************************************************************/
package com.coding.coupon.common.cache.redis.timer.delay;

import com.coding.coupon.common.cache.redis.timer.TimerPoolConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * 延时队列守护线程.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180515 14:11</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@ConditionalOnBean({TimerPoolConfig.class, TimerPoolConfig.Delay.class})
@ConditionalOnMissingBean({RDelayedQueueSupport.class})
@Component
@Slf4j
public class DelayedQueueSupport {

    @Autowired TimerPoolConfig timerPoolConfig;

    private ThreadPoolTaskExecutor delayQueueExecutor;

    /** 创建一个最初为空的新 DelayQueue */
    private static DelayQueue<DelayedItem> delayedItems = new DelayQueue<>();

    public static DelayQueue<DelayedItem> getDelayedItems() {
        return delayedItems;
    }

    // 定义私有构造器，防止被外部初始化
    private DelayedQueueSupport() {}

    /**
     * 添加任务到延时队列.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180515 14:06</font><br>
     * [请在此输入功能详述]
     *
     * @param delayTask - 延时任务，继承Thread类或实现Runnable接口的类
     * @param timeout - 延时时间，单位毫秒
     * @author Rushing0711
     * @since 1.0.0
     */
    public static void put(DelayTask delayTask, long timeout) {
        long nanoTime = TimeUnit.NANOSECONDS.convert(timeout, TimeUnit.MILLISECONDS);
        // 创建一个任务
        DelayedItem delayedItem = new DelayedItem(delayTask, nanoTime);
        // 阻塞式将任务放在延时的队列中
        delayedItems.put(delayedItem);
    }

    /** 初始化守护线程 */
    @PostConstruct
    private void init() {
        log.info("【初始化单机版延时任务队列守护线程】开始......");
        delayQueueExecutor = timerPoolConfig.getDelayQueueExecutor();

        Thread daemonThread = new Thread(this::execute);
        daemonThread.setDaemon(true);
        daemonThread.setName(timerPoolConfig.getDelay().getDelayTaskQueueDaemonThreadName());
        daemonThread.start();
        log.info("【初始化单机版延时任务队列守护线程】完成......");
    }

    private void execute() {
        log.info("【单机版延时任务队列守护线程】开启,thread={}", Thread.currentThread().getId());
        while (true) {
            // 阻塞式获取
            DelayedItem item;
            try {
                item = DelayedQueueSupport.getDelayedItems().take();
                if (item != null) {
                    DelayTask task = item.getTask();
                    if (task == null) {
                        continue;
                    }
                    log.info("【单机版延时任务队列】任务已提取并加入线程池,taskId={}", task.getTaskId());
                    delayQueueExecutor.execute(task);
                }
            } catch (InterruptedException e) {
                log.error("【单机版延时任务队列守护线程】异常", e);
                break;
            }
        }
        log.info("【单机版延时任务队列守护线程】关闭,thread={}", Thread.currentThread().getId());
    }
}
