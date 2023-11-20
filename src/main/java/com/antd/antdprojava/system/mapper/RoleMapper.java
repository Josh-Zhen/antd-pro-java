package com.antd.antdprojava.system.mapper;

import com.antd.antdprojava.system.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色映射层
 *
 * @author Joshua
 * @version 1.0
 * @date 19/11/2023 01:16
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
