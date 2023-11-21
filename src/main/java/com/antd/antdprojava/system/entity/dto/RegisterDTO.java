package com.antd.antdprojava.system.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 注册信息实体
 *
 * @author Joshua
 * @version 1.0
 * @date 20/11/2023 18:10
 */
@Data
public class RegisterDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -1521895622902341971L;

    /**
     * 用戶名
     */
    @NotNull(message = "用户名不能为空")
    private String userName;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

    /**
     * 验证码
     */
    @NotNull(message = "验证码不能为空")
    private String code;

}
