package com.antd.antdprojava.common.security;

import cn.hutool.json.JSONUtil;
import com.antd.antdprojava.common.response.Result;
import com.antd.antdprojava.common.security.enums.AuthExceptionEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * 未登录异常处理
 *
 * @author Joshua
 * @version 1.0
 * @date 22/11/2023 1:16
 */
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().println(JSONUtil.toJsonStr(Result.fail(AuthExceptionEnum.NOT_LOGGED_IN)));
    }

}

