/*
 * 文件名称：Param.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180813 17:28
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180813-01         Rushing0711     M201808131728 新建文件
 ********************************************************************************/
package com.coding.coupon.common.param;

import com.coding.coupon.common.param.po.BaseParamPO;
import com.coding.coupon.common.param.po.CommonParamPO;

/**
 * 定义系统参数信息.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180813 17:28</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ParamDefinition {

    interface BaseParamDefinition<T extends BaseParamPO> {
        String getName();

        Class<T> getClazz();
    }

    // ==================================================华丽的分割线==================================================

    /** 通用参数管理设置. */
    enum Common implements BaseParamDefinition<CommonParamPO> {
        INSTANCE();

        @Override
        public String getName() {
            return "COMMON";
        }

        @Override
        public Class<CommonParamPO> getClazz() {
            return CommonParamPO.class;
        }
    }
}
