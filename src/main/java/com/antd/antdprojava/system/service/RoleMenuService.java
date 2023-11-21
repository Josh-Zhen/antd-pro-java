package com.antd.antdprojava.system.service;

import com.antd.antdprojava.system.entity.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 角色菜单中间业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 19/11/2023 01:16
 */
public interface RoleMenuService extends IService<RoleMenu> {

    /**
     * 新增角色菜单中间
     *
     * @param roleMenu 角色菜单中间实体
     * @return 结果
     */
    Boolean insertRoleMenu(RoleMenu roleMenu);

    /**
     * 修改角色菜单中间
     *
     * @param roleMenu 角色菜单中间实体
     * @return 结果
     */
    Boolean updateRoleMenu(RoleMenu roleMenu);

    /**
     * 批量删除角色菜单中间
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    Boolean deleteRoleMenuByIds(String ids);

}
