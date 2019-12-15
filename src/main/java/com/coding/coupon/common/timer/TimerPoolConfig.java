/*
 * 文件名称：TimerPoolConfig.java
 * 系统名称：[系统名称]
 * 模块名称：特殊任务线程池配置
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180515 10:38
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180515-01         Rushing0711     M201805151038 新建文件
 ********************************************************************************/
package com.coding.coupon.common.timer;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 特殊任务线程池配置.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180613 15:36</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
@Configuration
@ConditionalOnProperty(value = "timerpool.enabled", havingValue = "true")
@ConfigurationProperties(prefix = "timerpool")
@Slf4j
public class TimerPoolConfig {

    private Delay delay;

    private Schedule schedule;

    private Async async;

    @Data
    public static class Delay {
        private String threadNamePrefix;

        private int corePoolSize;

        private int maxPoolSize;

        private int queueCapacity;

        private int keeyAliveSecond;

        private int awaitTerminationSeconds;

        private String delayTaskQueueDaemonThreadName;
    }

    @Data
    public static class Schedule {

        private String threadNamePrefix;

        private int poolSize;

        private int awaitTerminationSeconds;
    }

    @Data
    public static class Async {
        private String threadNamePrefix;

        private int corePoolSize;

        private int maxPoolSize;

        private int queueCapacity;

        private int keeyAliveSecond;

        private int awaitTerminationSeconds;
    }

    @ConditionalOnProperty(value = "timerpool.delay.enabled", havingValue = "true")
    @Bean
    public ThreadPoolTaskExecutor getDelayQueueExecutor() {
        log.info(
                "【延时任务线程池配置】threadNamePrefix={},corePoolSize={},maxPoolSize={},queueCapacity={},keeyAliveSecond={}",
                delay.getThreadNamePrefix(),
                delay.getCorePoolSize(),
                delay.getMaxPoolSize(),
                delay.getQueueCapacity(),
                delay.getKeeyAliveSecond());
        ThreadPoolTaskExecutor delayQueueExecutor;
        delayQueueExecutor = new ThreadPoolTaskExecutor();
        delayQueueExecutor.setThreadNamePrefix(delay.getThreadNamePrefix());
        delayQueueExecutor.setCorePoolSize(delay.getCorePoolSize());
        delayQueueExecutor.setMaxPoolSize(delay.getMaxPoolSize());
        delayQueueExecutor.setQueueCapacity(delay.getQueueCapacity());
        delayQueueExecutor.setKeepAliveSeconds(delay.getKeeyAliveSecond());

        delayQueueExecutor.setWaitForTasksToCompleteOnShutdown(true);
        delayQueueExecutor.setAwaitTerminationSeconds(delay.getAwaitTerminationSeconds());

        /*
         * Rejected-policy：当pool已经达到max size的时候，如何处理新任务。
         * CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行。
         */
        delayQueueExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        delayQueueExecutor.initialize();
        return delayQueueExecutor;
    }

    /**
     * 并行任务使用策略，多线程处理。
     *
     * @return -
     */
    @ConditionalOnProperty(value = "timerpool.schedule.enabled", havingValue = "true")
    @Bean
    public Executor getScheduleExecutor() {
        log.info(
                "【定时器线程池配置】threadNamePrefix={},poolSize={},awaitTerminationSeconds={}",
                schedule.getThreadNamePrefix(),
                schedule.getPoolSize(),
                schedule.getAwaitTerminationSeconds());
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix(schedule.getThreadNamePrefix());
        scheduler.setPoolSize(schedule.getPoolSize());

        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        scheduler.setAwaitTerminationSeconds(schedule.getAwaitTerminationSeconds());

        return scheduler;
    }

    /**
     * 异步任务使用策略，多线程处理。
     *
     * @return -
     */
    @ConditionalOnProperty(value = "timerpool.async.enabled", havingValue = "true")
    @Bean
    public Executor getAsyncExecutor() {
        log.info(
                "【异步任务线程池配置】threadNamePrefix={},corePoolSize={},maxPoolSize={},queueCapacity={},keeyAliveSecond={}",
                async.getThreadNamePrefix(),
                async.getCorePoolSize(),
                async.getMaxPoolSize(),
                async.getQueueCapacity(),
                async.getKeeyAliveSecond());
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix(async.getThreadNamePrefix());
        executor.setCorePoolSize(async.getCorePoolSize());
        executor.setMaxPoolSize(async.getMaxPoolSize());
        executor.setQueueCapacity(async.getQueueCapacity());
        executor.setKeepAliveSeconds(async.getKeeyAliveSecond());

        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(async.getAwaitTerminationSeconds());

        /*
         * Rejected-policy：当pool已经达到max size的时候，如何处理新任务。
         * CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行。
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
