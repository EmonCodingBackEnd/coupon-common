/*
 * 文件名称：ExampleCo.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190327 18:01
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190327-01         Rushing0711     M201903271801 新建文件
 ********************************************************************************/
package com.coding.coupon.common.cache.redis.co;

import lombok.Data;

import java.io.Serializable;

/**
 * CO是什么？.
 *
 * <p>创建时间: <font style="color:#00FFFF">20190327 18:02</font><br>
 * PO(Persistant Object)持久对象<br>
 * BO(Business Object)业务对象<br>
 * VO(View Object)表现对象，要求可序列化<br>
 * DTO(Data Transfer Object)数据传输对象<br>
 * DAO(Data Acces Object)数据访问对象<br>
 * POJO(Plain Ordinary Java Object)简单Java对象<br>
 * CO(Cach Object)可缓存对象，要求可序列化<br>
 * EO(Export Object)可导出对象
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class ExampleCO implements Serializable {
    private static final long serialVersionUID = -7408671998067280689L;

    private String exampleData;
}
