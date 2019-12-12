/*
 * 文件名称：Consts.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190309 19:58
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190309-01         Rushing0711     M201903091958 新建文件
 ********************************************************************************/
package com.coding.coupon.common;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 定义DictDefinition、ParamDefinition之外的常量.
 *
 * <p>创建时间: <font style="color:#00FFFF">20190309 19:59</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ConstantDefinition {

    DateTimeFormatter DATE_FORMATTER_SHORT = DateTimeFormatter.ofPattern("yyyyMMdd");

    /** 获取当日门店核核销量统计的 当日门店组合串. */
    static String getWorkdateShopId(Long shopId) {
        return LocalDate.now()
                .format(DATE_FORMATTER_SHORT)
                .concat(C_COMMON.UNDERLINE)
                .concat(String.valueOf(shopId));
    }

    interface C_COMMON {
        /** 系统代号. */
        String SYSTEM_CODE = "SYSTEM_CODE";

        /** 系统名称 */
        String SYSTEM_NAME = "SYSTEM_NAME";

        /** 系统中使用到的默认编码 */
        Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

        String ADMIN = "admin";

        /** 层级关系中，最顶层的记录，parent_id的取值. */
        Long TOP_PARENT_ID = 0L;

        /** 用户登录初始密码 */
        String INIT_PASS_WORD = "123456";

        /** 层级关系中，同层排序值sort的初始值. */
        Integer INIT_SORT_ORDER = 1;

        Integer INTEGER_ONE_NEGATIVE = -1;
        Integer INTEGER_ZERO = 0;
        Integer INTEGER_ONE = 1;
        Integer INTEGER_TWO = 2;
        Integer INTEGER_THREE = 3;
        Integer INTEGER_FOUR = 4;
        Integer INTEGER_FIVE = 5;
        Integer INTEGER_SIX = 6;
        Integer INTEGER_SEVEN = 7;
        Integer INTEGER_EIGHT = 8;
        Integer INTEGER_NINE = 9;
        Integer INTEGER_TEN = 10;

        Long LONG_ONE_NEGATIVE = -1L;
        Long LONG_ZERO = 0L;
        Long LONG_ONE = 1L;
        Long LONG_TWO = 2L;
        Long LONG_THREE = 3L;
        Long LONG_FOUR = 4L;
        Long LONG_FIVE = 5L;
        Long LONG_SIX = 6L;
        Long LONG_SEVEN = 7L;
        Long LONG_EIGHT = 8L;
        Long LONG_NINE = 9L;
        Long LONG_TEN = 10L;

        Boolean BOOLEAN_TRUE = true;
        Boolean BOOLEAN_FALSE = false;

        /** 分段尺寸, 1000. */
        Integer SECTION_SIZE = 1000;

        /** 排序 */
        String ASC = "asc";

        String DESC = "desc";

        /** 逗号. */
        String COMMA = ",";

        /** 冒号. */
        String COLON = ":";

        /** 下划线. */
        String UNDERLINE = "_";

        /** 空字符串. */
        String EMPTY = "";

        /** 短横线. */
        String TRANSVERSE = "-";

        /** 文件夹分隔符或者默认斜杠分隔符. */
        String FOLDER_SEPARATOR = "/";
    }
}
