/*
 * 文件名称：AsyncUncaughtHandler.java
 * 系统名称：[系统名称]
 * 模块名称：异步非捕获异常处理类
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180515 10:41
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180515-01         Rushing0711     M201805151041 新建文件
 ********************************************************************************/
package com.coding.coupon.common.timer.schedule;

import com.coding.coupon.common.JsonConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * 异步非捕获异常处理类.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180514 18:34</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
public class AsyncUncaughtHandler implements AsyncUncaughtExceptionHandler {

    @Override
    @SuppressWarnings("all")
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        String message =
                String.format(
                        "【异步方法执行】异常,throwable=%s, method=%s, params=%s",
                        throwable.getMessage(), method.getName(), JsonConverter.toJson(objects));
        log.error(message, throwable);

        // TODO: 2019/12/15 发送邮件或短信，做进一步处理
    }
}
