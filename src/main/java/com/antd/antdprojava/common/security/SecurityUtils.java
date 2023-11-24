package com.antd.antdprojava.common.security;

import com.antd.antdprojava.common.security.entity.TokenInfo;

/**
 * 权限工具类
 *
 * @author Joshua
 * @version 1.0
 * @date 24/11/2023 22:25
 */
public class SecurityUtils {

    /**
     * 用户信息
     *
     * @return token用户信息
     */
    public static TokenInfo me() {
        return JwtTokenService.getPayload(JwtTokenService.getToken());
    }

}
