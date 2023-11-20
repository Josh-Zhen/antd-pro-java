package com.antd.antdprojava.common.response;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 响应結果集
 *
 * @author Joshua
 * @version 1.0
 * @date 18/11/2023 17:50
 */
@Data
public class Result<T> implements Serializable {

    public static final Integer SUCCESS_CODE = 200;
    public static final Integer FAIL_CODE = 500;

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 成功标志
     */
    private Boolean success;

    /**
     * 消息
     */
    private String message;

    /**
     * 编码
     */
    private Integer code;

    /**
     * 时间戳
     */
    private Long timestamp = System.currentTimeMillis();

    /**
     * 返回的数据
     */
    private T data;

    /**
     * 成功
     *
     * @param <T> 不带实体
     * @return 无内容响应
     */
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(SUCCESS_CODE);
        return result;
    }

    /**
     * 成功
     *
     * @param <T> 带实体
     * @return 有内容响应
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(SUCCESS_CODE);
        result.setData(data);
        return result;
    }

    /**
     * 失败
     *
     * @param <T> 不带实体
     * @return 无内容响应
     */
    public static <T> Result<T> fail() {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(FAIL_CODE);
        return result;
    }

    /**
     * 失败
     *
     * @param <T> 不带实体
     * @return 错误内容响应
     */
    public static <T> Result<T> fail(String errorMsg) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(FAIL_CODE);
        result.setMessage(errorMsg);
        return result;
    }

    /**
     * 失败
     *
     * @param <T> 不带实体
     * @return 带异常编码和错误内容响应
     */
    public static <T> Result<T> fail(Integer code, String msg) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

}
