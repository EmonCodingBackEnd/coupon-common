/*
 * 文件名称：Dicts.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190309 19:59
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190309-01         Rushing0711     M201903091959 新建文件
 ********************************************************************************/
package com.coding.coupon.common;

import com.coding.helpers.tool.cmp.exception.AppBaseStatus;
import com.coding.helpers.tool.cmp.exception.AppException;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定义字典.
 *
 * <p>创建时间: <font style="color:#00FFFF">20190309 19:59</font><br>
 * 有两种定义形式：1、接口；2、枚举。如果需要校验数据是否合法，推荐枚举的定义方式。
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public interface DictDefinition {

    Logger log = LoggerFactory.getLogger(DictDefinition.class);

    interface BaseEnum<T> {

        T getCode();

        default String getDesc() {
            log.warn("当前枚举未实现getDesc方法");
            throw new AppException(AppBaseStatus.systemExpectedError(), "当前枚举未实现getDesc方法");
        }
    }

    /** 根据T类型的code值获取枚举实例，如果找不到则返回null. */
    static <T, E extends BaseEnum<T>> E getByCode(Class<E> enumClass, T code) {
        for (E each : enumClass.getEnumConstants()) {
            if (each.getCode().equals(code)) {
                return each;
            }
        }
        return null;
    }

    /** 根据T类型的code值获取枚举实例，如果找不到则抛异常. */
    static <T, E extends BaseEnum<T>> E getByCodeNoisy(Class<E> enumClass, T code) {
        E e = getByCode(enumClass, code);
        if (e == null) {
            log.error("【字典查询】根据字典值找不到对应字典, enumClazz={}, code={}", enumClass, code);
            throw new AppException(AppBaseStatus.systemExpectedError(), "根据字典值找不到对应字典");
        }
        return e;
    }

    /** 根据String类型的desc值获取枚举实例，如果枚举未实现getDesc方法会抛异常，如果找不到则返回null. */
    static <T, E extends BaseEnum<T>> E getByDesc(Class<E> enumClass, String desc) {
        for (E each : enumClass.getEnumConstants()) {
            if (each.getDesc().equals(desc)) {
                return each;
            }
        }
        return null;
    }

    /** 根据String类型的desc值获取枚举实例，如果枚举未实现getDesc方法会抛异常，如果找不到则抛异常. */
    static <T, E extends BaseEnum<T>> E getByDescNoisy(Class<E> enumClass, String desc) {
        E e = getByDesc(enumClass, desc);
        if (e == null) {
            log.error("【字典查询】根据字典描述找不到对应字典, enumClazz={}, desc={}", enumClass, desc);
            throw new AppException(AppBaseStatus.systemExpectedError(), "根据字典描述找不到对应字典");
        }
        return e;
    }

    // ==================================================华丽的分割线==================================================

    /** 【代码和数据库】数据是否已删除，1-已删除； 0-未删除 */
    interface Deleted {
        String NAME = "deleted";
        /** 已删除. */
        Integer YES = 1;
        /** 未删除. */
        Integer NO = 0;
    }

    /** 【仅定义在代码】是与否；1-是;0-否. */
    interface YesOrNo {
        String NAME = "yes_or_no";
        /** 是. */
        Integer YES = 1;
        /** 否. */
        Integer NO = 0;
    }

    /** 【代码和数据库】是否启用系统参数 */
    interface Enabled {
        String NAME = "enabled";
        /** 启用 */
        Integer ENABLED = 1;
        /** 停用 */
        Integer DISABLED = 0;
    }

    /** 【仅定义在代码】仅有code的枚举. */
    @RequiredArgsConstructor
    enum CodeEnum implements BaseEnum<Integer> {
        /** 第一个. */
        FIRST(1),
        /** 第二个. */
        SECOND(1),
        ;
        @NonNull @Getter private Integer code;

        public static final String NAME = "code";

        public static CodeEnum ofCode(Integer code) {
            return getByCode(CodeEnum.class, code);
        }

        public static CodeEnum ofCodeNoisy(Integer code) {
            return getByCodeNoisy(CodeEnum.class, code);
        }
    }

    /** 【仅定义在代码】code和desc并存的枚举. */
    @RequiredArgsConstructor
    enum CodeDescEnum implements BaseEnum<Integer> {
        /** 第一个. */
        FIRST(0, "第一个"),
        /** 第二个. */
        SECOND(1, "第二个"),
        ;
        @NonNull @Getter private Integer code;
        @NonNull @Getter private String desc;

        public static final String NAME = "code_desc";

        public static CodeDescEnum ofCode(Integer code) {
            return getByCode(CodeDescEnum.class, code);
        }

        public static CodeDescEnum ofCodeNoisy(Integer code) {
            return getByCodeNoisy(CodeDescEnum.class, code);
        }

        public static CodeDescEnum ofDesc(String desc) {
            return getByDesc(CodeDescEnum.class, desc);
        }

        public static CodeDescEnum ofDescNoisy(String desc) {
            return getByDescNoisy(CodeDescEnum.class, desc);
        }
    }

    /** 【仅定义在代码】优惠券类型. */
    @RequiredArgsConstructor
    enum CouponCategoryEnum implements BaseEnum<Integer> {
        MAN_JIAN(1, "满减券"),
        ZHE_KOU(2, "折扣券"),
        LI_JIAN(3, "满减券"),
        ;
        @NonNull @Getter private Integer code;
        @NonNull @Getter private String desc;

        public static final String NAME = "coupon_category";

        public static CouponCategoryEnum ofCode(Integer code) {
            return getByCode(CouponCategoryEnum.class, code);
        }

        public static CouponCategoryEnum ofCodeNoisy(Integer code) {
            return getByCodeNoisy(CouponCategoryEnum.class, code);
        }
    }

    /** 【仅定义在代码】产品线. */
    @RequiredArgsConstructor
    enum ProductLineEnum implements BaseEnum<Integer> {
        DA_MAO(1, "大猫"),
        DA_BAO(2, "大宝"),
        ;
        @NonNull @Getter private Integer code;
        @NonNull @Getter private String desc;

        public static final String NAME = "product_line";

        public static ProductLineEnum ofCode(Integer code) {
            return getByCode(ProductLineEnum.class, code);
        }

        public static ProductLineEnum ofCodeNoisy(Integer code) {
            return getByCodeNoisy(ProductLineEnum.class, code);
        }
    }

    /** 【仅定义在代码】分发目标. */
    @RequiredArgsConstructor
    enum DistributeTargetEnum implements BaseEnum<Integer> {
        SINGLE(1, "单用户"),
        MULTI(2, "多用户"),
        ;
        @NonNull @Getter private Integer code;
        @NonNull @Getter private String desc;

        public static final String NAME = "distribute_target";

        public static DistributeTargetEnum ofCode(Integer code) {
            return getByCode(DistributeTargetEnum.class, code);
        }

        public static DistributeTargetEnum ofCodeNoisy(Integer code) {
            return getByCodeNoisy(DistributeTargetEnum.class, code);
        }
    }

    /** 【仅定义在代码】有效期规则. */
    @RequiredArgsConstructor
    enum PeriodTypeEnum implements BaseEnum<Integer> {
        /** 固定的绝对日期. */
        REGULAR(1, "绝对日期"),
        /** 变动的相对日期. */
        SHIFT(2, "相对日期"),
        ;
        @NonNull @Getter private Integer code;
        @NonNull @Getter private String desc;

        public static final String NAME = "period_type";

        public static PeriodTypeEnum ofCode(Integer code) {
            return getByCode(PeriodTypeEnum.class, code);
        }

        public static PeriodTypeEnum ofCodeNoisy(Integer code) {
            return getByCodeNoisy(PeriodTypeEnum.class, code);
        }
    }
}
