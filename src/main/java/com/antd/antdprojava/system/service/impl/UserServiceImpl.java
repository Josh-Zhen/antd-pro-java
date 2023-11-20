package com.antd.antdprojava.system.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.antd.antdprojava.common.page.PageFactory;
import com.antd.antdprojava.common.page.PageResult;
import com.antd.antdprojava.system.entity.User;
import com.antd.antdprojava.system.entity.dto.UserLoginDTO;
import com.antd.antdprojava.system.entity.vo.UserInfoToken;
import com.antd.antdprojava.system.entity.vo.UserInfoVO;
import com.antd.antdprojava.system.mapper.UserMapper;
import com.antd.antdprojava.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 用户业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 19/11/2023 01:16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 登录
     *
     * @param dto 登录信息
     * @return token信息
     */
    @Override
    public UserInfoToken login(UserLoginDTO dto) {
        Long userId = 1L;
        String jwtToken = "aaaaaaaaaaa";
        return new UserInfoToken(userId, jwtToken);
    }

    /**
     * 登出
     *
     * @return 结果
     */
    @Override
    public Boolean logout() {
        return true;
    }

    /**
     * 获取用户详情
     *
     * @param id id
     * @return 用户
     */
    @Override
    public UserInfoVO getUserInfo(Long id) {
        return baseMapper.getUserInfo(id);
    }

    /**
     * 分页查询用户
     *
     * @param user 用户实体
     * @return 用户集合
     */
    @Override
    public PageResult<User> pageList(User user) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        if (ObjectUtil.isNotNull(user)) {
            if (ObjectUtil.isNotEmpty(user.getId())) {
                queryWrapper.eq(User::getId, user.getId());
            }
            if (ObjectUtil.isNotEmpty(user.getUserName())) {
                queryWrapper.like(User::getUserName, user.getUserName());
            }
            if (ObjectUtil.isNotEmpty(user.getNumber())) {
                queryWrapper.eq(User::getNumber, user.getNumber());
            }
            if (ObjectUtil.isNotEmpty(user.getState())) {
                queryWrapper.eq(User::getState, user.getState());
            }
        }
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    /**
     * 查询用户列表
     *
     * @param user 用户实体
     * @return 用户集合
     */
    @Override
    public List<User> selectUserList(User user) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        return this.list(queryWrapper);
    }

    /**
     * 新增用户
     *
     * @param user 用户实体
     * @return 结果
     */
    @Override
    public Boolean insertUser(User user) {
        user.setCreateDate(LocalDateTime.now());
        return this.save(user);
    }

    /**
     * 修改用户
     *
     * @param user 用户实体
     * @return 结果
     */
    @Override
    public Boolean updateUser(User user) {
        user.setUpdateDate(LocalDateTime.now());
        return this.updateById(user);
    }

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public Boolean deleteUserByIds(String ids) {
        return this.removeByIds(Arrays.asList(Convert.toStrArray(ids)));
    }

}
