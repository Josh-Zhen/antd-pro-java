package com.antd.antdprojava.system.mapper;

import com.antd.antdprojava.system.entity.User;
import com.antd.antdprojava.system.entity.vo.UserInfoVO;
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
     * 获取用户详情
     *
     * @param id id
     * @return 用户
     */
    UserInfoVO getUserInfo(Long id);
}
