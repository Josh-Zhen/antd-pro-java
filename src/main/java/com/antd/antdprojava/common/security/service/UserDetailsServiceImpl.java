package com.antd.antdprojava.common.security.service;

import cn.hutool.core.util.ObjectUtil;
import com.antd.antdprojava.common.exception.BusinessException;
import com.antd.antdprojava.common.security.entity.UserInfo;
import com.antd.antdprojava.common.security.enums.AuthExceptionEnum;
import com.antd.antdprojava.system.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户详情实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 21/11/2023 16:16
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取用户详情
        UserInfo userInfo = userMapper.getUserInfo(username);
        System.out.println(userInfo);
        if (ObjectUtil.isEmpty(userInfo)) {
            throw new BusinessException(AuthExceptionEnum.ACCOUNT_PWD_ERROR);
        }
        if (!userInfo.isEnabled()) {
            throw new BusinessException(AuthExceptionEnum.ACCOUNT_INVALID_ERROR);
        }
        return userInfo;
    }

}
