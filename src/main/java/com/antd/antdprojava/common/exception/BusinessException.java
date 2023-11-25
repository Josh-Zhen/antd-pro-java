package com.antd.antdprojava.common.exception;

import com.antd.antdprojava.common.exception.base.AbstractBaseExceptionEnum;
import com.antd.antdprojava.common.response.Result;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 自定义异常
 *
 * @author Joshua
 * @version 1.0
 * @date 18/11/2023 17:50
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 587725680775015580L;

    /**
     * 异常编码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String message;

    /**
     * 异常类
     */
    private Throwable e;

    /**
     * 自定义异常
     *
     * @param code    异常编码
     * @param message 异常信息
     */
    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * 自定义异常
     *
     * @param exception 自定义异常类
     */
    public BusinessException(AbstractBaseExceptionEnum exception) {
        super(exception.getMessage());
        this.code = exception.getCode();
        this.message = exception.getMessage();
    }

    /**
     * 系统异常
     *
     * @param e 系统异常类
     */
    public BusinessException(Exception e) {
        super(e);
        this.code = Result.FAIL_CODE;
        this.message = e.getMessage();
    }

    /**
     * 自定义异常
     *
     * @param code    异常编码
     * @param message 异常信息
     * @param e       异常类
     */
    public BusinessException(Integer code, String message, Throwable e) {
        super(message);
        this.code = code;
        this.message = message;
        this.e = e;
    }
}