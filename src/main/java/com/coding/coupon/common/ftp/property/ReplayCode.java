/*
 * 文件名称：ReplayCode.java
 * 系统名称：[系统名称]
 * 模块名称：FTP状态码列表
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180626 16:36
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180626-01         Rushing0711     M201806261636 新建文件
 ********************************************************************************/
package com.coding.coupon.common.ftp.property;

import org.apache.commons.net.ftp.FTPReply;

import java.util.Objects;

/**
 * FTP状态码列表.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180626 16:39</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public enum ReplayCode {
    FILE_STATUS_OK(FTPReply.FILE_STATUS_OK, "文件状态良好，打开数据连接"),
    COMMAND_OK(FTPReply.COMMAND_OK, "命令成功"),
    COMMAND_IS_SUPERFLUOUS(FTPReply.COMMAND_IS_SUPERFLUOUS, "命令未实现"),
    SYSTEM_STATUS(FTPReply.SYSTEM_STATUS, "系统状态或系统帮助响应"),
    DIRECTORY_STATUS(FTPReply.DIRECTORY_STATUS, "目录状态"),
    FILE_STATUS(FTPReply.FILE_STATUS, "文件状态"),
    HELP_MESSAGE(FTPReply.HELP_MESSAGE, "帮助信息，信息仅对人类用户有用"),
    NAME_SYSTEM_TYPE(FTPReply.NAME_SYSTEM_TYPE, "名字系统类型"),
    SERVICE_READY(FTPReply.SERVICE_READY, "对新用户服务准备好"),
    SERVICE_CLOSING_CONTROL_CONNECTION(
            FTPReply.SERVICE_CLOSING_CONTROL_CONNECTION, "服务关闭控制连接，可以退出登录"),
    DATA_CONNECTION_OPEN(FTPReply.DATA_CONNECTION_OPEN, "数据连接打开，无传输正在进行"),
    CLOSING_DATA_CONNECTION(FTPReply.CLOSING_DATA_CONNECTION, "关闭数据连接，请求的文件操作成功"),
    ENTERING_PASSIVE_MODE(FTPReply.ENTERING_PASSIVE_MODE, "进入被动模式"),
    USER_LOGGED_IN(FTPReply.USER_LOGGED_IN, "用户登录"),
    FILE_ACTION_OK(FTPReply.FILE_ACTION_OK, "请求的文件操作完成"),
    PATHNAME_CREATED(FTPReply.PATHNAME_CREATED, "创建\"PATHNAME\""),
    NEED_PASSWORD(FTPReply.NEED_PASSWORD, "用户名正确，需要口令"),
    NEED_ACCOUNT(FTPReply.NEED_ACCOUNT, "登录时需要帐户信息"),
    FILE_ACTION_PENDING(FTPReply.FILE_ACTION_PENDING, "请求的文件操作需要进一步命令"),
    SERVICE_NOT_AVAILABLE(FTPReply.SERVICE_NOT_AVAILABLE, "连接用户过多"),
    CANNOT_OPEN_DATA_CONNECTION(FTPReply.CANNOT_OPEN_DATA_CONNECTION, "不能打开数据连接"),
    TRANSFER_ABORTED(FTPReply.TRANSFER_ABORTED, "关闭连接，中止传输"),
    FILE_ACTION_NOT_TAKEN(FTPReply.FILE_ACTION_NOT_TAKEN, "请求的文件操作未执行"),
    ACTION_ABORTED(FTPReply.ACTION_ABORTED, "中止请求的操作：有本地错误"),
    INSUFFICIENT_STORAGE(FTPReply.INSUFFICIENT_STORAGE, "未执行请求的操作：系统存储空间不足"),
    UNRECOGNIZED_COMMAND(FTPReply.UNRECOGNIZED_COMMAND, "格式错误，命令不可识别"),
    SYNTAX_ERROR_IN_ARGUMENTS(FTPReply.SYNTAX_ERROR_IN_ARGUMENTS, "参数语法错误"),
    COMMAND_NOT_IMPLEMENTED(FTPReply.COMMAND_NOT_IMPLEMENTED, "命令未实现"),
    BAD_COMMAND_SEQUENCE(FTPReply.BAD_COMMAND_SEQUENCE, "命令顺序错误"),
    COMMAND_NOT_IMPLEMENTED_FOR_PARAMETER(
            FTPReply.COMMAND_NOT_IMPLEMENTED_FOR_PARAMETER, "此参数下的命令功能未实现"),
    NOT_LOGGED_IN(FTPReply.NOT_LOGGED_IN, "账号或密码错误"),
    NEED_ACCOUNT_FOR_STORING_FILES(FTPReply.NEED_ACCOUNT_FOR_STORING_FILES, "存储文件需要帐户信息"),
    FILE_UNAVAILABLE(FTPReply.FILE_UNAVAILABLE, "未执行请求的操作"),
    PAGE_TYPE_UNKNOWN(FTPReply.PAGE_TYPE_UNKNOWN, "请求操作中止：页类型未知"),
    STORAGE_ALLOCATION_EXCEEDED(FTPReply.STORAGE_ALLOCATION_EXCEEDED, "请求的文件操作中止，存储分配溢出"),
    FILE_NAME_NOT_ALLOWED(FTPReply.FILE_NAME_NOT_ALLOWED, "未执行请求的操作：文件名不合法"),
    BAD_REQUEST(400, "失败请求，请检查请求内容"),
    UNAUTHORIZED(401, "未经认证"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源未找到"),
    SERVER_ERROR(500, "服务器错误"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),
    DEFAULT_REPLYCODE(999, "FTP未知replaycode"),
    ;

    private Integer code;
    private String msg;

    ReplayCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ReplayCode getByCode(Integer code) {
        for (ReplayCode each : ReplayCode.values()) {
            if (Objects.equals(each.getCode(), code)) {
                return each;
            }
        }
        return DEFAULT_REPLYCODE;
    }
}
