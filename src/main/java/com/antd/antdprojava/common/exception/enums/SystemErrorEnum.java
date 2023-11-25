package com.antd.antdprojava.common.exception.enums;

import com.antd.antdprojava.common.exception.base.AbstractBaseExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统异常枚举类
 *
 * @author Joshua
 * @version 1.0
 * @date 18/11/2023 17:50
 */
@Getter
@AllArgsConstructor
public enum SystemErrorEnum implements AbstractBaseExceptionEnum {

    //系统示知错误
    DEFAULT_ERROR(-1, "系统未知错误"),
    //成功
    SUCCESS(0, "成功"),
    //对象为空
    OBJECT_EMPTY(1, "对象为空"),
    ;

    private Integer code;
    private String message;
}
