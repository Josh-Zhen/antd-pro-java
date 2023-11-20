package com.antd.antdprojava.system.service;

import com.antd.antdprojava.common.page.PageResult;
import com.antd.antdprojava.system.entity.User;
import com.antd.antdprojava.system.entity.dto.UserLoginDTO;
import com.antd.antdprojava.system.entity.vo.UserInfoToken;
import com.antd.antdprojava.system.entity.vo.UserInfoVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 19/11/2023 01:16
 */
public interface UserService extends IService<User> {

    /**
     * 登录
     *
     * @param dto 登录信息
     * @return token信息
     */
    UserInfoToken login(UserLoginDTO dto);

    /**
     * 登出
     *
     * @return 结果
     */
    Boolean logout();

    /**
     * 获取用户详情
     *
     * @param id id
     * @return 用户
     */
    UserInfoVO getUserInfo(Long id);

    /**
     * 分页查询用户
     *
     * @param user 用户实体
     * @return 用户集合
     */
    PageResult<User> pageList(User user);

    /**
     * 查询用户列表
     *
     * @param user 用户实体
     * @return 用户集合
     */
    List<User> selectUserList(User user);

    /**
     * 新增用户
     *
     * @param user 用户实体
     * @return 结果
     */
    Boolean insertUser(User user);

    /**
     * 修改用户
     *
     * @param user 用户实体
     * @return 结果
     */
    Boolean updateUser(User user);

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    Boolean deleteUserByIds(String ids);

}
