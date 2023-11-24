package com.antd.antdprojava.system.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.antd.antdprojava.common.page.PageFactory;
import com.antd.antdprojava.common.page.PageResult;
import com.antd.antdprojava.system.entity.Role;
import com.antd.antdprojava.system.mapper.RoleMapper;
import com.antd.antdprojava.system.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 角色业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 19/11/2023 01:16
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    /**
     * 分页查询角色
     *
     * @param role 角色实体
     * @return 角色集合
     */
    @Override
    public PageResult<Role> pageList(Role role) {
        LambdaQueryWrapper<Role> queryWrapper = Wrappers.lambdaQuery();
        if (ObjectUtil.isNotNull(role)) {
            if (ObjectUtil.isNotEmpty(role.getId())) {
                queryWrapper.eq(Role::getId, role.getId());
            }
            if (ObjectUtil.isNotEmpty(role.getName())) {
                queryWrapper.like(Role::getName, role.getName());
            }
            if (ObjectUtil.isNotEmpty(role.getRoleKey())) {
                queryWrapper.eq(Role::getRoleKey, role.getRoleKey());
            }
            if (ObjectUtil.isNotEmpty(role.getState())) {
                queryWrapper.eq(Role::getState, role.getState());
            }
        }
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    /**
     * 查询角色列表
     *
     * @param role 角色实体
     * @return 角色集合
     */
    @Override
    public List<Role> selectRoleList(Role role) {
        LambdaQueryWrapper<Role> queryWrapper = Wrappers.lambdaQuery();
        return this.list(queryWrapper);
    }

    /**
     * 新增角色
     *
     * @param role 角色实体
     * @return 结果
     */
    @Override
    public Boolean insertRole(Role role) {
        role.setCreateDate(LocalDateTime.now());
        return this.save(role);
    }

    /**
     * 修改角色
     *
     * @param role 角色实体
     * @return 结果
     */
    @Override
    public Boolean updateRole(Role role) {
        return this.updateById(role);
    }

    /**
     * 批量删除角色
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public Boolean deleteRoleByIds(String ids) {
        return this.removeByIds(Arrays.asList(Convert.toStrArray(ids)));
    }

}
