package com.antd.antdprojava.system.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户角色中间表实体类
 *
 * @author Joshua
 * @version 1.0
 * @date 19/11/2023 01:16
 */
@Data
public class UserRole implements Serializable {

    @Serial
    private static final long serialVersionUID = -34450517493229737L;

    /**
     * id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 新增用户权限构造器
     *
     * @param userId 用户id
     */
    public UserRole(Long userId) {
        this.userId = userId;
    }

}

