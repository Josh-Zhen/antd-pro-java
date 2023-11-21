package com.antd.antdprojava.system.service;

import com.antd.antdprojava.common.page.PageResult;
import com.antd.antdprojava.system.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户角色中间业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 19/11/2023 01:16
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 分页查询用户角色中间
     *
     * @param userRole 用户角色中间实体
     * @return 用户角色中间集合
     */
    PageResult<UserRole> pageList(UserRole userRole);

    /**
     * 查询用户角色中间列表
     *
     * @param userRole 用户角色中间实体
     * @return 用户角色中间集合
     */
    List<UserRole> selectUserRoleList(UserRole userRole);

    /**
     * 新增用户角色中间
     *
     * @param userRole 用户角色中间实体
     * @return 结果
     */
    Boolean insertUserRole(UserRole userRole);

    /**
     * 修改用户角色中间
     *
     * @param userRole 用户角色中间实体
     * @return 结果
     */
    Boolean updateUserRole(UserRole userRole);

    /**
     * 批量删除用户角色中间
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    Boolean deleteUserRoleByIds(String ids);

}
