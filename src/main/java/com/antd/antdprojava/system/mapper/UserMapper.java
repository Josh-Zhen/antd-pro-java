package com.antd.antdprojava.system.mapper;

import com.antd.antdprojava.common.security.entity.UserInfo;
import com.antd.antdprojava.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户映射层
 *
 * @author Joshua
 * @version 1.0
 * @date 18/11/2023 21:32
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    UserInfo getUserInfo(String username);
}
