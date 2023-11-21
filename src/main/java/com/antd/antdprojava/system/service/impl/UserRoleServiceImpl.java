package com.antd.antdprojava.system.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.antd.antdprojava.common.page.PageFactory;
import com.antd.antdprojava.common.page.PageResult;
import com.antd.antdprojava.system.entity.UserRole;
import com.antd.antdprojava.system.mapper.UserRoleMapper;
import com.antd.antdprojava.system.service.UserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 用户角色中间业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 19/11/2023 01:16
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    /**
     * 分页查询用户角色中间
     *
     * @param userRole 用户角色中间实体
     * @return 用户角色中间集合
     */
    @Override
    public PageResult<UserRole> pageList(UserRole userRole) {
        LambdaQueryWrapper<UserRole> queryWrapper = Wrappers.lambdaQuery();
        if (ObjectUtil.isNotNull(userRole)) {
            if (ObjectUtil.isNotEmpty(userRole.getId())) {
                queryWrapper.eq(UserRole::getId, userRole.getId());
            }
            if (ObjectUtil.isNotEmpty(userRole.getUserId())) {
                queryWrapper.eq(UserRole::getUserId, userRole.getUserId());
            }
            if (ObjectUtil.isNotEmpty(userRole.getRoleId())) {
                queryWrapper.eq(UserRole::getRoleId, userRole.getRoleId());
            }
        }
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    /**
     * 查询用户角色中间列表
     *
     * @param userRole 用户角色中间实体
     * @return 用户角色中间集合
     */
    @Override
    public List<UserRole> selectUserRoleList(UserRole userRole) {
        LambdaQueryWrapper<UserRole> queryWrapper = Wrappers.lambdaQuery();
        return this.list(queryWrapper);
    }

    /**
     * 新增用户角色中间
     *
     * @param userRole 用户角色中间实体
     * @return 结果
     */
    @Override
    public Boolean insertUserRole(UserRole userRole) {
        return this.save(userRole);
    }

    /**
     * 修改用户角色中间
     *
     * @param userRole 用户角色中间实体
     * @return 结果
     */
    @Override
    public Boolean updateUserRole(UserRole userRole) {
        return this.updateById(userRole);
    }

    /**
     * 批量删除用户角色中间
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public Boolean deleteUserRoleByIds(String ids) {
        return this.removeByIds(Arrays.asList(Convert.toStrArray(ids)));
    }

}
