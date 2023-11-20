package com.antd.antdprojava.system.service;

import com.antd.antdprojava.common.page.PageResult;
import com.antd.antdprojava.system.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 角色业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 19/11/2023 01:16
 */
public interface RoleService extends IService<Role> {

    /**
     * 分页查询角色
     *
     * @param role 角色实体
     * @return 角色集合
     */
    PageResult<Role> pageList(Role role);

    /**
     * 查询角色列表
     *
     * @param role 角色实体
     * @return 角色集合
     */
    List<Role> selectRoleList(Role role);

    /**
     * 新增角色
     *
     * @param role 角色实体
     * @return 结果
     */
    Boolean insertRole(Role role);

    /**
     * 修改角色
     *
     * @param role 角色实体
     * @return 结果
     */
    Boolean updateRole(Role role);

    /**
     * 批量删除角色
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    Boolean deleteRoleByIds(String ids);

}
