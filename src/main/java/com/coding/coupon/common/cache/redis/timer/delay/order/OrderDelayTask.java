/*
 * 文件名称：OrderDelayTask.java
 * 系统名称：[系统名称]
 * 模块名称：订单延时任务
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180515 14:21
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180515-01         Rushing0711     M201805151421 新建文件
 ********************************************************************************/
package com.coding.coupon.common.cache.redis.timer.delay.order;

import com.coding.coupon.common.cache.redis.timer.delay.DelayRetryTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 订单延时任务.
 *
 * <p>
 *
 * <p>创建时间: <font style="color:#00FFFF">20180515 14:22</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
@Slf4j
public class OrderDelayTask extends DelayRetryTask implements ApplicationContextAware {

    private static final long serialVersionUID = -9157077217216143833L;

    private Long orderId;

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (OrderDelayTask.applicationContext == null) {
            OrderDelayTask.applicationContext = applicationContext;
        }
    }

    // 定义私有构造器，防止被外部初始化
    private OrderDelayTask() {}

    public OrderDelayTask(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public void run() {
        try {
        } catch (Exception e) {
            log.error(String.format("【延时任务】执行异常,taskId=%s", this.getTaskId()), e);
            if (canIRetry()) {
                this.retry();
                log.info("【延时任务】重试次数={},taskId={}", this.getRetryTimes(), this.getTaskId());
            } else {
                log.warn("【延时任务】重试已达最大次数={},taskId={}", this.getMaxRetryTimes(), this.getTaskId());
            }
        }
    }

    @Override
    public String getTaskId() {
        return String.valueOf(orderId);
    }
}
