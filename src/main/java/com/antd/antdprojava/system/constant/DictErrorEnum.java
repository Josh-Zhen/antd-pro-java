package com.antd.antdprojava.system.constant;

import com.antd.antdprojava.common.exception.base.AbstractBaseExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 字典异常枚举类
 *
 * @author Joshua
 * @version 1.0
 * @date 25/11/2023 16:34
 */
@Getter
@AllArgsConstructor
public enum DictErrorEnum implements AbstractBaseExceptionEnum {

    /**
     * 异常枚举
     */
    DICT_TYPE_NOT_EXIST(20001, "字典类型不存在"),
    CODE_ALREADY_EXISTS(20002, "编号已存在"),
    NAME_ALREADY_EXISTS(20003, "名称已存在"),
    VALUE_ALREADY_EXISTS(20004, "字典值已存在");

    private Integer code;

    private String message;
}
