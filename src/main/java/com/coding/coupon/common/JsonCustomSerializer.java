/*
 * 文件名称：JsonCustomSerializer.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190522 10:22
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190522-01         Rushing0711     M201905221022 新建文件
 ********************************************************************************/
package com.coding.coupon.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface JsonCustomSerializer {

    String EMPTY = "";

    class BigDecimal2FormatStringSerializer extends JsonSerializer<BigDecimal> {
        // 默认的格式化字符样式 “#.00”  还可以是像“#.0000”
        private static final String DEFAULT_FORMAT_PATTERN = "#,##0.00";

        private DecimalFormat decimalFormat = new DecimalFormat(DEFAULT_FORMAT_PATTERN);

        @Override
        public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException, JsonProcessingException {
            if (value != null) {
                gen.writeString(decimalFormat.format(value));
            } else {
                gen.writeString(EMPTY);
            }
        }
    }

    class BigDecimal2SimpleStringSerializer extends JsonSerializer<BigDecimal> {
        // 默认的格式化字符样式 “#.##”  还可以是像“#.####”
        private static final String DEFAULT_FORMAT_PATTERN = "#.##";

        private DecimalFormat decimalFormat = new DecimalFormat(DEFAULT_FORMAT_PATTERN);

        @Override
        public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException, JsonProcessingException {
            if (value != null) {
                gen.writeString(decimalFormat.format(value));
            } else {
                gen.writeString(EMPTY);
            }
        }
    }

    class Date2DateStringSerializer extends JsonSerializer<Date> {

        @Override
        public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException, JsonProcessingException {
            if (value != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                gen.writeString(sdf.format(value));
            } else {
                gen.writeString(EMPTY);
            }
        }
    }

    class Date2DateTimeStringSerializer extends JsonSerializer<Date> {

        @Override
        public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException, JsonProcessingException {
            if (value != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                gen.writeString(sdf.format(value));
            } else {
                gen.writeString(EMPTY);
            }
        }
    }

    class Date2LongSerializer extends JsonSerializer<Date> {
        @Override
        public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException, JsonProcessingException {
            if (value != null) {
                gen.writeNumber(value.getTime() / 1000);
            } else {
                gen.writeString(EMPTY);
            }
        }
    }

    class Long2StringSerializer extends JsonSerializer<Long> {
        @Override
        public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException, JsonProcessingException {
            if (value != null) {
                gen.writeString(String.valueOf(value));
            } else {
                gen.writeString(EMPTY);
            }
        }
    }
}
