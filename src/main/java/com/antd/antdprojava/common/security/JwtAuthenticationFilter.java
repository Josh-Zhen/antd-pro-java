package com.antd.antdprojava.common.security;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.antd.antdprojava.common.redis.RedisService;
import com.antd.antdprojava.common.response.Result;
import com.antd.antdprojava.common.security.entity.TokenInfo;
import com.antd.antdprojava.common.security.entity.UserInfo;
import com.antd.antdprojava.common.security.enums.AuthExceptionEnum;
import com.antd.antdprojava.system.constant.SystemBusinessConstant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author Joshua
 * @version 1.0
 * @date 24/11/2023 21:11
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final RedisService redisService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = JwtTokenService.getToken();
        // 如果请求头中没有Authorization信息则直接拒绝掉
        if (ObjectUtil.isEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 请求头有token但token无法解析
        TokenInfo tokenInfo = JwtTokenService.getPayload(token);
        if (ObjectUtil.isEmpty(tokenInfo)) {
            log.info("从token中未获取到用户名, token：{}, URI：{}", token, request.getServletPath());
            filterChain.doFilter(request, response);
            return;
        }

        assert tokenInfo != null;
        // 从缓存中验证token的存在性
        UserInfo user = redisService.getCacheObject(SystemBusinessConstant.USER_CACHE + tokenInfo.getUserName());
        if (ObjectUtil.isEmpty(user)) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(JSONUtil.toJsonStr(Result.fail(AuthExceptionEnum.NOT_LOGGED_IN)));
            response.getWriter().flush();
            return;
        } else {
            // 返回用户不存在信息到前端
            // 创建验证通过的令牌对象
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            // 设置令牌到安全上下文中
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

}
