/*
 * 文件名称：AppDynamicRedisKey.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190327 18:07
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190327-01         Rushing0711     M201903271807 新建文件
 ********************************************************************************/
package com.coding.coupon.common.cache.redis;

import com.coding.coupon.common.ConstantDefinition;
import com.coding.helpers.tool.cmp.common.cache.AppBaseRedisKey;

public interface AppDynamicRedisKey extends AppBaseRedisKey {

    @Override
    default String getPrefix() {
        return ConstantDefinition.C_COMMON.SYSTEM_CODE;
    }

    /** 门店当日核销统计. */
    default String getDayVerifyStatRedisKeyByShopId(Long shopId) {
        String workdateShopId = ConstantDefinition.getWorkdateShopId(shopId);
        return combineKeyParts(false, "shop", "dayVerifyStat", workdateShopId);
    }
}
