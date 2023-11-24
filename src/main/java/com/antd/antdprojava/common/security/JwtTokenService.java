package com.antd.antdprojava.common.security;

import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.antd.antdprojava.common.security.entity.TokenInfo;
import com.antd.antdprojava.common.utils.HttpServletUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author Joshua
 * @version 1.0
 * @date 22/11/2023 0:57
 */
public class JwtTokenService {

    /**
     * 请求头标识
     */
    public static final String AUTH_HEADER = "Authorization";

    /**
     * 过期时间 (分钟)
     */
    private static final Long EXPIRATION = 60 * 60 * 4L;

    /**
     * 密钥
     */
    private static final String SECRET = "DBL0I3QMYGO8W7KURAJ7S245EEB1V2AGJ3D8GA6NIJSU6OGHX9LLF6G5QH8UJ9WM";

    /**
     * token内容特征
     */
    private static final String PAYLOAD_FEATURE = "user";

    /**
     * 获取内容
     *
     * @param token 令牌
     * @return 用戶
     */
    public static TokenInfo getPayload(String token) {
        if (verify(token)) {
            JWT jwt = JWTUtil.parseToken(token);
            return JSONUtil.toBean(String.valueOf(jwt.getPayload(PAYLOAD_FEATURE)), TokenInfo.class);
        } else {
            return null;
        }
    }

    /**
     * 生成token
     *
     * @param user 用户信息
     * @return token
     */
    public static String createToken(TokenInfo user) {
        return createToken(user, null);
    }

    /**
     * 生成token
     *
     * @param user    用户信息
     * @param expTime 超时时间
     * @return token
     */
    public static String createToken(TokenInfo user, Long expTime) {
        if (expTime == null) {
            expTime = EXPIRATION;
        }
        return JWT.create()
                // 开始时间
                .setIssuedAt(new Date())
                // 有效时间
                .setExpiresAt(new Date(System.currentTimeMillis() + expTime * 1000))
                // 内容
                .setPayload(PAYLOAD_FEATURE, user)
                .setKey(SECRET.getBytes(StandardCharsets.UTF_8))
                .sign();
    }

    /**
     * 校验token
     *
     * @param token 令牌
     * @return 结果
     */
    public static Boolean verify(String token) {
        return JWTUtil.verify(token, SECRET.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 从请求头中获取token
     *
     * @return token
     */
    public static String getToken() {
        return HttpServletUtils.getRequest().getHeader(AUTH_HEADER);
    }

}
