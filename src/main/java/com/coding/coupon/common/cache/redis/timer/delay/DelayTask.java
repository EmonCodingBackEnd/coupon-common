/*
 * 文件名称：DelayTask.java
 * 系统名称：[系统名称]
 * 模块名称：延时任务抽象类
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180516 10:06
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180516-01         Rushing0711     M201805161006 新建文件
 ********************************************************************************/
package com.coding.coupon.common.cache.redis.timer.delay;

import java.io.Serializable;

/**
 * 延时任务抽象类.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180516 10:07</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public interface DelayTask extends Runnable, Serializable {

    /** 延时任务编号. */
    String getTaskId();
}
