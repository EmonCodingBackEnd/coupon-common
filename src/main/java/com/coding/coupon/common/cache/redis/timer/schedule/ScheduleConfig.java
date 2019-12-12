/*
 * 文件名称：ScheduleConfig.java
 * 系统名称：[系统名称]
 * 模块名称：并行任务配置
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180613 07:54
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180613-01         Rushing0711     M201806130754 新建文件
 ********************************************************************************/
package com.coding.coupon.common.cache.redis.timer.schedule;

import com.coding.coupon.common.cache.redis.timer.TimerPoolConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

/**
 * 并行任务配置.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180613 07:54</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@EnableScheduling
@ConditionalOnBean({TimerPoolConfig.class, TimerPoolConfig.Schedule.class})
@Component
@Slf4j
public class ScheduleConfig implements SchedulingConfigurer {

    @Autowired TimerPoolConfig timerPoolConfig;

    /**
     * 并行任务。
     *
     * @param taskRegistrar -
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(timerPoolConfig.getScheduleExecutor());
    }
}
