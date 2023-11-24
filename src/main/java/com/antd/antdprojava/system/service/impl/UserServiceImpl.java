package com.antd.antdprojava.system.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.antd.antdprojava.common.constant.CharacterConstant;
import com.antd.antdprojava.common.exception.BusinessException;
import com.antd.antdprojava.common.page.PageFactory;
import com.antd.antdprojava.common.page.PageResult;
import com.antd.antdprojava.common.redis.RedisService;
import com.antd.antdprojava.common.security.JwtTokenService;
import com.antd.antdprojava.common.security.SecurityUtils;
import com.antd.antdprojava.common.security.entity.TokenInfo;
import com.antd.antdprojava.common.security.entity.UserInfo;
import com.antd.antdprojava.common.security.enums.AuthExceptionEnum;
import com.antd.antdprojava.system.constant.BusinessConstant;
import com.antd.antdprojava.system.entity.User;
import com.antd.antdprojava.system.entity.UserRole;
import com.antd.antdprojava.system.entity.dto.RegisterDTO;
import com.antd.antdprojava.system.entity.dto.UserLoginDTO;
import com.antd.antdprojava.system.entity.vo.UserInfoToken;
import com.antd.antdprojava.system.entity.vo.UserInfoVO;
import com.antd.antdprojava.system.mapper.UserMapper;
import com.antd.antdprojava.system.service.UserRoleService;
import com.antd.antdprojava.system.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRoleService userRoleService;
    private final RedisService redisService;

    /**
     * 登录
     *
     * @param dto 登录信息
     * @return token信息
     */
    @Override
    public UserInfoToken login(UserLoginDTO dto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 从认证中获取用户信息
        UserInfo user = (UserInfo) authenticate.getPrincipal();
        user.setPassword(CharacterConstant.EMPTY);
        // 从创建token
        String username = user.getUsername();
        Long userId = user.getId();
        String jwtToken = JwtTokenService.createToken(new TokenInfo(userId, username));
        // 缓存到redis
        redisService.setCacheObject(BusinessConstant.USER_CACHE + username, user);
        return new UserInfoToken(userId, jwtToken);
    }

    /**
     * 登出
     *
     * @return 结果
     */
    @Override
    public Boolean logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfo userInfo = (UserInfo) authentication.getPrincipal();
        redisService.deleteObject(BusinessConstant.USER_CACHE + userInfo.getUsername());
        return true;
    }

    /**
     * 注册
     *
     * @param dto 注册实体
     * @return 结果
     */
    @Override
    public Boolean register(RegisterDTO dto) {
        // 用户是否存在
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(User::getUserName, dto.getUserName());
        if (ObjectUtil.isNotEmpty(this.getOne(queryWrapper))) {
            throw new BusinessException(AuthExceptionEnum.USER_ALREADY_EXISTS);
        }

        // 加密密码
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User(dto.getUserName(), encoder.encode(dto.getPassword()));
        try {
            this.save(user);
            return userRoleService.save(new UserRole(user.getId()));
        } catch (Exception e) {
            throw new BusinessException(AuthExceptionEnum.USER_REGISTRATION_FAILED);
        }
    }

    /**
     * 获取用户详情
     *
     * @return 用户
     */
    @Override
    public UserInfoVO getUserInfo() {
        UserInfoVO userVo = new UserInfoVO();
        UserInfo userInfo = baseMapper.getUserInfo(SecurityUtils.me().getUserName());
        BeanUtils.copyProperties(userInfo, userVo);
        return userVo;
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
