package com.antd.antdprojava.system.controller;

import com.antd.antdprojava.common.page.PageResult;
import com.antd.antdprojava.common.response.Result;
import com.antd.antdprojava.system.entity.User;
import com.antd.antdprojava.system.entity.vo.UserInfoVO;
import com.antd.antdprojava.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制层
 *
 * @author Joshua
 * @version 1.0
 * @date 19/11/2023 01:16
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * 获取用户详情
     *
     * @return 用户
     */
    @GetMapping("/info")
    public Result<UserInfoVO> info() {
        return Result.success(userService.getUserInfo());
    }

    /**
     * 分页查询用户
     *
     * @param user 用户实体
     * @return 用户集合
     */
    @GetMapping("/pageList")
    public Result<PageResult<User>> page(User user) {
        return Result.success(userService.pageList(user));
    }

    /**
     * 查询用户列表
     *
     * @param user 用户实体
     * @return 用户集合
     */
    @GetMapping("/list")
    public Result<List<User>> list(User user) {
        return Result.success(userService.selectUserList(user));
    }

    /**
     * 新增用户
     *
     * @param user 用户实体
     * @return 结果
     */
    @PostMapping("/save")
    public Result<Boolean> addSave(@RequestBody User user) {
        return Result.success(userService.insertUser(user));
    }

    /**
     * 修改用户
     *
     * @param user 用户实体
     * @return 结果
     */
    @PostMapping("/update")
    public Result<Boolean> editSave(@RequestBody User user) {
        return Result.success(userService.updateUser(user));
    }

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(String ids) {
        return Result.success(userService.deleteUserByIds(ids));
    }

}
