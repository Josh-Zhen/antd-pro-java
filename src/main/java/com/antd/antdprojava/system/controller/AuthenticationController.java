package com.antd.antdprojava.system.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.antd.antdprojava.common.exception.BusinessException;
import com.antd.antdprojava.common.redis.RedisService;
import com.antd.antdprojava.common.response.Result;
import com.antd.antdprojava.common.security.enums.AuthExceptionEnum;
import com.antd.antdprojava.system.constant.SystemBusinessConstant;
import com.antd.antdprojava.system.entity.dto.RegisterDTO;
import com.antd.antdprojava.system.entity.dto.UserLoginDTO;
import com.antd.antdprojava.system.entity.vo.UserInfoToken;
import com.antd.antdprojava.system.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 身份认证控制层
 *
 * @author Joshua
 * @version 1.0
 * @date 20/11/2023 0:56
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final RedisService redisService;

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
     *
     * @return 结果
     */
    @GetMapping("/logout")
    public Result<Boolean> logout() {
        return Result.success(userService.logout());
    }

    /**
     * 注册
     *
     * @param dto 注册实体
     * @return 结果
     */
    @PostMapping("/register")
    public Result<Boolean> register(@RequestBody RegisterDTO dto) {
        String key = SystemBusinessConstant.REGISTER_CODE + dto.getUserName();
        String code = redisService.getCacheObject(key);
        // 校验验证码
        if (ObjectUtil.isEmpty(code) || !dto.getCode().equals(code)) {
            throw new BusinessException(AuthExceptionEnum.CODE_IS_INVALID);
        }
        // 移除验证码
        redisService.deleteObject(key);
        return Result.success(userService.register(dto));
    }

    /**
     * 获取验证码
     *
     * @param number 手机号码
     * @return 验证码
     */
    @GetMapping("/getCode/{number}")
    public Result<String> getCode(@PathVariable String number) {
        String code = RandomUtil.randomNumbers(6);
        log.info("---------- 验证码: {}", code);
        redisService.setCacheObject(SystemBusinessConstant.REGISTER_CODE + number, code);
        return Result.success(code);
    }

}
