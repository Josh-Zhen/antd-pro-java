package com.antd.antdprojava.common.security;

import cn.hutool.json.JSONUtil;
import com.antd.antdprojava.common.response.Result;
import com.antd.antdprojava.common.security.enums.AuthExceptionEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

/**
 * 权限异常处理
 *
 * @author Joshua
 * @version 1.0
 * @date 22/11/2023 1:17
 */
public class PerAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().println(JSONUtil.toJsonStr(Result.fail(AuthExceptionEnum.PERM_INVALID_ERROR)));
    }

}

