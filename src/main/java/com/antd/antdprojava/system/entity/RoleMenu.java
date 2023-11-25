package com.antd.antdprojava.system.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色菜单中间表实体类
 *
 * @author Joshua
 * @version 1.0
 * @date 19/11/2023 01:16
 */
@Data
public class RoleMenu implements Serializable {

    @Serial
    private static final long serialVersionUID = -74475145554171069L;

    /**
     * id
     */
    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 目录id
     */
    private Long menuId;

}
