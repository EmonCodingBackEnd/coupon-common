/*
 * 文件名称：RegexDefine.java
 * 系统名称：[系统名称]
 * 模块名称：正则表达式定义类
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180424 14:55
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180424-01         Rushing0711     M201804241455 新建文件
 ********************************************************************************/
package com.coding.coupon.common.regex;

import java.util.regex.Pattern;

/**
 * 正则表达式定义类.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180424 14:56</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class RegexDefine {

    /**
     * 示例1：完全匹配的正则表达式定义.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180424 15:19</font><br>
     *
     * <ul>
     *   <li>正则：^(\$R)\.(\w+)$
     *   <li>定义：$R.key
     *   <li>示例：$R.TransCode
     * </ul>
     *
     * @since 1.0.0
     */
    public static final String $R_REGEX = "^(\\$R)\\.(\\w+)$";

    /** {@linkplain #$R_REGEX} */
    public static final Pattern $R_REGEX_PATTERN = Pattern.compile($R_REGEX);

    /**
     * 示例2：部分匹配的正则表达式定义.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180424 15:24</font><br>
     *
     * <ul>
     *   <li>正则：{}
     *   <li>定义：{}
     *   <li>示例：{}决定重新改写{}日志的格式化方式为{}
     * </ul>
     *
     * @since 1.0.0
     */
    public static final String LOG_FORMATER_REGEX = "\\{\\}";

    /** 字段说明: {@linkplain #LOG_FORMATER_REGEX} */
    public static final Pattern LOG_FORMATER_REGEX_PATTERN = Pattern.compile(LOG_FORMATER_REGEX);

    /**
     * 用户手机号正则表达式定义.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180424 15:41</font><br>
     *
     * <p>匹配校验手机号的合法性，并能获取手机号开头3个数字，与尾号4个数字
     *
     * <p>正则：手机号（精确）
     *
     * <p>移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188、198
     *
     * <p>联通：130、131、132、145、155、156、175、176、185、186、166
     *
     * <p>电信：133、153、173、177、180、181、189、191、199
     *
     * <p>全球星：1349
     *
     * <p>虚拟运营商：170
     *
     * <ul>
     *   <li>正则：^((?:13[0-9])|(?:14[5|7|9])|(?:15(?:[0-3]|[5-9]))|(?:16[6])|(?:17[0135678])|(?:18[0-9])|(?:19[189]))\d{4}(\d{4})$
     *   <li>定义：11位手机号码
     *   <li>示例：18767188240
     * </ul>
     *
     * @since 1.0.0
     */
    public static final String MOBILE_REGEX =
            "^((?:13[0-9])|(?:14[5|7|9])|(?:15(?:[0-3]|[5-9]))|(?:16[6])|(?:17[0135678])|(?:18[0-9])|(?:19[189]))\\d{4}(\\d{4})$";

    /** 字段说明：{@linkplain #MOBILE_REGEX}. */
    public static final Pattern MOBILE_REGEX_PATTERN = Pattern.compile(MOBILE_REGEX);

    // (((^https?:(?:\/\/)?)(?:[-;:&=\+\$,\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=\+\$,\w]+@)[A-Za-z0-9.-]+)((?:\/[\+~%\/.\w-_]*)?\??(?:[-\+=&;%@.\w_]*)#?(?:[\w]*))?)$
    public static final String URL_REGEX =
            "(^(https?)://(?:[\\w:.-]+)?((?:\\/[\\+~%\\/.\\w-_]*)?))\\??([-\\+=&;%@.\\w_]*)#?(?:[\\w]*)$";
    public static final Pattern URL_REGEX_PATTERN = Pattern.compile(URL_REGEX);

    public static final String URI_REGEX = "^(https?)://(?:[\\w:.-]+)?((?:\\/[\\+~%\\/.\\w-_]*)?)$";
    public static final Pattern URI_REGEX_PATTERN = Pattern.compile(URI_REGEX);

    public static final String URL_PARAM_REGEX = "\\??([-\\+=&;%@.\\w_]*)#?(?:[\\w]*)";
    public static final Pattern URL_PARAM_REGEX_PATTERN = Pattern.compile(URL_PARAM_REGEX);
    /** 闸机字段 */
    public static final String MACHINE_PARAM_REGEX =
            "[^\\{\\}\\\\\\$\\@\\#\\%\\^\\(\\)\\-\\_\\?\\/0-9a-zA-Z\\u4e00-\\u9fa5]+";

    public static final Pattern MACHINE_PARAM_REGEX_PATTERN = Pattern.compile(MACHINE_PARAM_REGEX);

    public static final String PROXY_GOOD_URL_REGEX =
            "(?:url|src)=(?:\"([^\\n\\r\"']+)\"|'([^\\n\\r\"']+)')|url\\((?:\"([^\\n\\r\"']+)\"|'([^\\n\\r\"']+)'|&quot;([^\\n\\r\"']+)&quot;)\\)";
    public static final Pattern PROXY_GOOD_URL_REGEX_PATTERN =
            Pattern.compile(PROXY_GOOD_URL_REGEX);

    public static final String PROXY_BAD_URL_REGEX =
            "(?:href|url|src)=(?:\"([^\\n\\r\"']+)|'([^\\n\\r\"']+))$|url\\((?:\"([^\\n\\r\"']+)|'([^\\n\\r\"']+)|&quot;([^\\n\\r\"']+))$";
    public static final Pattern PROXY_BAD_URL_REGEX_PATTERN = Pattern.compile(PROXY_BAD_URL_REGEX);

    public static final String STRICT_FILENAME_REGEX =
            "^(?:([^<>/\\\\\\|:\"\"\\*\\?]+)\\.(\\w+))|(?:([^<>/\\\\\\|:\"\"\\*\\?]+))$";
    public static final Pattern STRICT_FILENAME_REGEX_PATTERN =
            Pattern.compile(STRICT_FILENAME_REGEX);

    public static final String FILENAME_REGEX =
            "(?:([^<>/\\\\\\|:\"\"\\*\\?]+)\\.(\\w+))|(?:([^<>/\\\\\\|:\"\"\\*\\?]+))$";
    public static final Pattern FILENAME_REGEX_PATTERN = Pattern.compile(FILENAME_REGEX);

    public static final String IMAGE_REGEX =
            "([^<>/\\\\\\|:\"\"\\*\\?]+)\\.(jpg|JPG|jpeg|JPEG|gif|GIF|png|PNG)$";
    public static final Pattern IMAGE_REGEX_PATTERN = Pattern.compile(IMAGE_REGEX);
    public static final String AUDIO_REGEX =
            "([^<>/\\\\\\|:\"\"\\*\\?]+)\\.(mp3|MP3|wav|WAV|ape|APE|flac|m4a|aac)$";
    public static final Pattern AUDIO_REGEX_PATTERN = Pattern.compile(AUDIO_REGEX);
    public static final String VEDIO_REGEX =
            "([^<>/\\\\\\|:\"\"\\*\\?]+)\\.(mp4|avi|rmvb|flv|wmv|vob|mkv|mov)$";
    public static final Pattern VEDIO_REGEX_PATTERN = Pattern.compile(VEDIO_REGEX);

    /** 字典权限控制校验规则. */
    public static final String DICT_TYPE_ACCESS_VALUE_REGEX = "^([1-4])(,[1-4])*$";
    /** {@linkplain #DICT_TYPE_ACCESS_VALUE_REGEX}. */
    public static final Pattern DICT_TYPE_ACCESS_VALUE_REGEX_PATTERN =
            Pattern.compile(DICT_TYPE_ACCESS_VALUE_REGEX);

    /** 字典代码校验规则. */
    public static final String DICT_TYPE_CODE_REGEX = "^(\\w{1,50})$";
    /** {@linkplain #DICT_TYPE_CODE_REGEX}. */
    public static final Pattern DICT_TYPE_CODE_REGEX_PATTERN =
            Pattern.compile(DICT_TYPE_CODE_REGEX);

    /** 字典项值校验规则. */
    public static final String DICT_ITEM_CODE_REGEX = "^(\\w{1,50})$";
    /** {@linkplain #DICT_ITEM_CODE_REGEX}. */
    public static final Pattern DICT_ITEM_CODE_REGEX_PATTERN =
            Pattern.compile(DICT_ITEM_CODE_REGEX);

    /** 字典项权限控制校验规则. */
    public static final String DICT_ITEM_ACCESS_VALUE_REGEX = "^([2-4])(,[2-4])*$";
    /** {@linkplain #DICT_ITEM_ACCESS_VALUE_REGEX}. */
    public static final Pattern DICT_ITEM_ACCESS_VALUE_REGEX_PATTERN =
            Pattern.compile(DICT_ITEM_ACCESS_VALUE_REGEX);

    /** 是或否的正则. */
    public static final String YES_OR_NO_REGEX = "^[0|1]$";

    /** {@linkplain #YES_OR_NO_REGEX}. */
    public static final Pattern YES_OR_NO_REGEX_PATTERN = Pattern.compile(YES_OR_NO_REGEX);

    /** 门票销售渠道校验，英文逗号分隔. */
    public static final String TICKET_SALE_CHANNEL_REGEX = "^(?:([1|2])(,[1|2])*|[9]|[8])$";

    /** 销售渠道校验，英文逗号分隔. */
    public static final String SALE_CHANNEL_REGEX = "^(?:([1|2])(,[1|2])*|[8])$";

    /** {@linkplain #SALE_CHANNEL_REGEX}. */
    public static final Pattern SALE_CHANNEL_REGEX_PATTERN = Pattern.compile(SALE_CHANNEL_REGEX);

    /** 针对Long类型的ID，如果是单个ID，可以通过该正则校验合法性. */
    public static final String SINGLE_ID_REGEX = "^(\\d{1,20})$";

    /** {@linkplain #SINGLE_ID_REGEX}. */
    public static final Pattern SINGLE_ID_REGEX_PATTERN = Pattern.compile(SINGLE_ID_REGEX);

    /** 针对Long类型的ID，如果是英文逗号分隔的多个ID，可以通过该正则校验合法性. */
    public static final String MULTI_IDS_REGEX = "^(\\d{1,20})(,\\d{1,20})*$";

    /** {@linkplain #MULTI_IDS_REGEX}. */
    public static final Pattern MULTI_IDS_REGEX_PATTERN = Pattern.compile(MULTI_IDS_REGEX);

    /** 系统参数代码校验规则. */
    public static final String PARAM_CODE_REGEX = "^(\\w{1,50})$";
    /** {@linkplain #PARAM_CODE_REGEX}. */
    public static final Pattern PARAM_CODE_REGEX_PATTERN = Pattern.compile(PARAM_CODE_REGEX);

    /** 系统参数值校验规则. */
    public static final String PARAM_VALUE_REGEX = "^(.{1,2000})$";
    /** {@linkplain #PARAM_VALUE_REGEX}. */
    public static final Pattern PARAM_VALUE_REGEX_PATTERN = Pattern.compile(PARAM_VALUE_REGEX);

    /** 邮箱地址校验规则. */
    public static final String EMAIL_VALUE_REGEX =
            "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
    /** {@linkplain #PARAM_VALUE_REGEX}. */
    public static final Pattern EMAIL_VALUE_REGEX_PATTERN = Pattern.compile(EMAIL_VALUE_REGEX);

    /** 匹配中文字符的正则表达式. */
    public static final String CN_REGEX = "^[\\u4e00-\\u9fa5]$";

    /** {@linkplain #CN_REGEX}. */
    public static final Pattern CN_REGEX_PATTERN = Pattern.compile(CN_REGEX);

    /** 匹配双字节字符. */
    public static final String TWO_BYTE_REGEX = "^[^\\x00-\\xff]$";

    /** {@linkplain #TWO_BYTE_REGEX}. */
    public static final Pattern TWO_BYTE_REGEX_PATTERN = Pattern.compile(TWO_BYTE_REGEX);

    /** 匹配6-16位的单字节字符串. */
    public static final String SIX_TO_SIXTEEN_ONE_BYTE_CHAR_REGEX = "^([\\x00-\\xff]){6,16}$";

    /** {@linkplain #SIX_TO_SIXTEEN_ONE_BYTE_CHAR_REGEX}. */
    public static final Pattern SIX_TO_SIXTEEN_ONE_BYTE_CHAR_REGEX_PATTERN =
            Pattern.compile(SIX_TO_SIXTEEN_ONE_BYTE_CHAR_REGEX);

    /** 暂时不提供. */
    public static final String PASSWORD_REGEX = "^(?=.*[0-9].*)(?=.*[a-z].*|(?=.*[A-Z].*)).{6,8}$";

    public static final Pattern PASSWORD_REGEX_PATTERN = Pattern.compile(PASSWORD_REGEX);

    /** 正整数. */
    public static final String POSITIVE_INTEGER_REGEX = "^[1-9]\\d*$";

    public static final Pattern POSITIVE_INTEGER_REGEX_PATTERN =
            Pattern.compile(POSITIVE_INTEGER_REGEX);

    /** 非负整数. */
    public static final String NON_NEGATIVE_INTEGER_REGEX = "^[1-9]\\d*|0$";

    public static final Pattern NON_NEGATIVE_INTEGER_REGEX_PATTERN =
            Pattern.compile(NON_NEGATIVE_INTEGER_REGEX);

    /** 非负浮点数，精度2位. */
    public static final String NON_NEGATIVE_FLOATING_POINT_REGEX =
            "^(?:[1-9]\\d*(?:\\.\\d{1,2})?|0(?:\\.\\d{1,2})?)$";

    public static final Pattern NON_NEGATIVE_FLOATING_POINT_REGEX_PATTERN =
            Pattern.compile(NON_NEGATIVE_FLOATING_POINT_REGEX);

    /** 非负浮点数，精度3位. */
    public static final String NON_NEGATIVE_FLOATING_3_POINT_REGEX =
            "^(?:[1-9]\\d*(?:\\.\\d{1,3})?|0(?:\\.\\d{1,3})?)$";

    public static final Pattern NON_NEGATIVE_FLOATING_3_POINT_REGEX_PATTERN =
            Pattern.compile(NON_NEGATIVE_FLOATING_3_POINT_REGEX);

    /** 区间[0-1)的浮点数，精度3位. */
    public static final String NON_NEGATIVE_LESS_1_FLOATING_2_POINT_REGEX = "^0(?:\\.\\d{1,3})?$";

    public static final Pattern NON_NEGATIVE_LESS_1_FLOATING_2_POINT_REGEX_PATTERN =
            Pattern.compile(NON_NEGATIVE_LESS_1_FLOATING_2_POINT_REGEX);

    /** 区间[0-1)的浮点数，精度2位. */
    public static final String NON_NEGATIVE_LESS_1_FLOATING_3_POINT_REGEX = "^0(?:\\.\\d{1,3})?$";

    public static final Pattern NON_NEGATIVE_LESS_1_FLOATING_3_POINT_REGEX_PATTERN =
            Pattern.compile(NON_NEGATIVE_LESS_1_FLOATING_3_POINT_REGEX);

    /** 区间[0-1]的浮点数，精度2位. */
    public static final String NON_NEGATIVE_LESS_EQUAL_1_FLOATING_2_POINT_REGEX =
            "^0(?:\\.\\d{1,2})?|1(?:\\.0{1,2})?$";

    public static final Pattern NON_NEGATIVE_LESS_EQUAL_1_FLOATING_2_POINT_REGEX_PATTERN =
            Pattern.compile(NON_NEGATIVE_LESS_EQUAL_1_FLOATING_2_POINT_REGEX);

    /** 区间[0-1]的浮点数，精度3位. */
    public static final String NON_NEGATIVE_LESS_EQUAL_1_FLOATING_3_POINT_REGEX =
            "^0(?:\\.\\d{1,3})?|1(?:\\.0{1,3})?$";

    public static final Pattern NON_NEGATIVE_LESS_EQUAL_1_FLOATING_3_POINT_REGEX_PATTERN =
            Pattern.compile(NON_NEGATIVE_LESS_EQUAL_1_FLOATING_3_POINT_REGEX);
}
