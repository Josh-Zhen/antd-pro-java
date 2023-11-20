package com.antd.antdprojava.common.exception.base;

/**
 * 异常枚举类
 *
 * @author Joshua
 * @version 1.0
 * @date 18/11/2023 17:50
 */
public interface AbstractBaseExceptionEnum {

    /**
     * 错误码
     *
     * @return 编码
     */
    Integer getCode();

    /**
     * 错误信息
     *
     * @return 信息
     */
    String getMessage();
}
