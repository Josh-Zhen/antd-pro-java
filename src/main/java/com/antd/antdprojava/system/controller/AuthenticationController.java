package com.antd.antdprojava.system.controller;

import com.antd.antdprojava.common.response.Result;
import com.antd.antdprojava.system.entity.dto.UserLoginDTO;
import com.antd.antdprojava.system.entity.vo.UserInfoToken;
import com.antd.antdprojava.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 身份认证控制层
 *
 * @author Joshua
 * @version 1.0
 * @date 20/11/2023 0:56
 */
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    /**
     * 登录
     *
     * @param dto 实体
     * @return token信息
     */
    @PostMapping("/login")
    private Result<UserInfoToken> login(@RequestBody UserLoginDTO dto) {
        return Result.success(userService.login(dto));
    }

    /**
     * 登出
     */
    @GetMapping("/logout")
    public Result<Boolean> logout() {
        return Result.success(userService.logout());
    }

}
