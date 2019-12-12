/*
 * 文件名称：BaseEntity.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190310 00:41
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190310-01         Rushing0711     M201903100041 新建文件
 ********************************************************************************/
package com.coding.coupon.common.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 5179317806059013858L;

    /** 数据是否已删除，1-已删除； 0-未删除. */
    @Column(name = "deleted", nullable = false)
    private Integer deleted = 0;

    /** 创建时间 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    /** 更新时间 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version = 0;

    protected void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    protected void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    protected void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    protected void setVersion(Integer version) {
        this.version = version;
    }
}
