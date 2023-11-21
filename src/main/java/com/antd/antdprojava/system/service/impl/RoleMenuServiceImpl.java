package com.antd.antdprojava.system.service.impl;

import cn.hutool.core.convert.Convert;
import com.antd.antdprojava.system.entity.RoleMenu;
import com.antd.antdprojava.system.mapper.RoleMenuMapper;
import com.antd.antdprojava.system.service.RoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 角色菜单中间业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 19/11/2023 01:16
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    /**
     * 新增角色菜单中间
     *
     * @param roleMenu 角色菜单中间实体
     * @return 结果
     */
    @Override
    public Boolean insertRoleMenu(RoleMenu roleMenu) {
        return this.save(roleMenu);
    }

    /**
     * 修改角色菜单中间
     *
     * @param roleMenu 角色菜单中间实体
     * @return 结果
     */
    @Override
    public Boolean updateRoleMenu(RoleMenu roleMenu) {
        return this.updateById(roleMenu);
    }

    /**
     * 批量删除角色菜单中间
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public Boolean deleteRoleMenuByIds(String ids) {
        return this.removeByIds(Arrays.asList(Convert.toStrArray(ids)));
    }

}
