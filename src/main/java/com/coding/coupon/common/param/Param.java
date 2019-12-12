package com.coding.coupon.common.param;

import com.coding.coupon.common.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/** 系统参数表. */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "param")
@Data
public class Param extends BaseEntity {

    private static final long serialVersionUID = -2318631067518787139L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(
        name = "idGenerator",
        strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy"
    )
    private Long id;

    /** 系统参数代码 */
    @Column(name = "param_code")
    private String paramCode;

    /** 系统参数值 */
    @Column(name = "param_value")
    private String paramValue;

    /** 系统参数的描述 */
    @Column(name = "param_desc")
    private String paramDesc;

    /** 是否展示 */
    @Column(name = "showable")
    private Integer showable;

    /** 是否启用 */
    @Column(name = "enabled")
    private Integer enabled;
}
