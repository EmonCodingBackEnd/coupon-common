/*
 * 文件名称：DelayTask.java
 * 系统名称：[系统名称]
 * 模块名称：可以重试的延时任务抽象类
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180516 10:06
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180516-01         Rushing0711     M201805161006 新建文件
 ********************************************************************************/
package com.coding.coupon.common.cache.redis.timer.delay;

import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * 可以重试的延时任务抽象类.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180516 10:07</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class DelayRetryTask implements DelayTask {

    private static final long serialVersionUID = 4411186426495911690L;

    /** 重试次数，初始值为0，每次重试该值增加1. */
    private Integer retryTimes = 0;

    /**
     * 默认重试间隔，单位：秒.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180517 10:14</font><br>
     *
     * <ul>
     *   <li>默认重试5次
     *   <li>默认频率为15/15/30/180/1800/1800/1800/1800/3600，单位：秒
     *   <li>如果重新设置了时间间隔，则间隔数等于最大重试次数
     * </ul>
     *
     * @since 1.0.0
     */
    private long[] retryInterval = {
        TimeUnit.SECONDS.toMillis(15),
        TimeUnit.SECONDS.toMillis(15),
        TimeUnit.SECONDS.toMillis(30),
        TimeUnit.SECONDS.toMillis(180),
        TimeUnit.SECONDS.toMillis(1800),
        TimeUnit.SECONDS.toMillis(1800),
        TimeUnit.SECONDS.toMillis(1800),
        TimeUnit.SECONDS.toMillis(1800),
        TimeUnit.SECONDS.toMillis(3600),
    };

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public Integer getMaxRetryTimes() {
        return this.retryInterval.length;
    }

    /**
     * 设置重试间隔，会覆盖默认设置.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180517 09:44</font><br>
     *
     * <p>说明如下：
     *
     * <ul>
     *   <li>单位是秒
     *   <li>分隔符是英文 ,
     *   <li>重试间隔的数量，会影响最大重试次数
     * </ul>
     *
     * @param retryInterval - 重试间隔字符串表示的值，例如 15,30,60 表示允许重试3次，频率为15/30/60，单位：秒
     * @author Rushing0711
     * @since 1.0.0
     */
    public void setRetryInterval(String retryInterval) {
        if (!StringUtils.isEmpty(retryInterval)) {
            String[] intervals = retryInterval.split(",");
            this.retryInterval = new long[intervals.length];
            for (int i = 0; i < this.retryInterval.length; i++) {
                this.retryInterval[i] = TimeUnit.SECONDS.toMillis(Long.parseLong(intervals[i]));
            }
        }
    }

    /**
     * 任务是否允许重试.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180517 09:26</font><br>
     * [请在此输入功能详述]
     *
     * @return boolean
     * @author Rushing0711
     * @since 1.0.0
     */
    public boolean canIRetry() {
        return this.getRetryTimes() < this.getMaxRetryTimes();
    }

    public void retry() {
        if (canIRetry()) {
            this.retryTimes++;
            RDelayedQueueSupport.put(this, this.retryTimeout(), TimeUnit.MILLISECONDS);
        }
    }

    // 返回本次重试时间间隔
    private long retryTimeout() {
        return this.retryInterval[this.getRetryTimes() - 1];
    }
}
