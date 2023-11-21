package com.antd.antdprojava.common.security.enums;

import com.antd.antdprojava.common.exception.base.AbstractBaseExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 权限异常枚举
 *
 * @author Joshua
 * @version 1.0
 * @date 20/11/2023 17:58
 */
@Getter
@AllArgsConstructor
public enum AuthExceptionEnum implements AbstractBaseExceptionEnum {

    /**
     * 账号密码错误
     */
    ACCOUNT_PWD_ERROR(1001, "账号或密码错误!"),

    /**
     * TOKEN无效或已过期
     */
    TOKEN_INVALID_ERROR(1002, "TOKEN无效或已过期!"),

    /**
     * 无访问权限
     */
    PERM_INVALID_ERROR(1003, "无权限!"),

    /**
     * 账户无效或冻结
     */
    ACCOUNT_INVALID_ERROR(1004, "账户无效或冻结"),

    /**
     * 验证码错误
     */
    CODE_INVALID_ERROR(1005, "验证码错误"),

    /**
     * 系统管理者密码错误
     */
    PASS_ERROR(1006, "密码错误"),

    /**
     * 未登录
     */
    NOT_LOGGED_IN(1007, "未登录"),

    /**
     * 无效验证码
     */
    CODE_IS_INVALID(1008, "无效验证码"),

    /**
     * 用户注册失败
     */
    USER_REGISTRATION_FAILED(1009, "注册失败"),

    /**
     * 用户已存在
     */
    USER_ALREADY_EXISTS(1010, "用户已存在"),

    ;
    private Integer code;
    private String message;

}
