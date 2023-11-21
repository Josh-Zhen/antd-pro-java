package com.antd.antdprojava.common.security;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.antd.antdprojava.common.redis.RedisService;
import com.antd.antdprojava.common.response.Result;
import com.antd.antdprojava.common.security.entity.TokenInfo;
import com.antd.antdprojava.common.security.entity.UserInfo;
import com.antd.antdprojava.common.security.enums.AuthExceptionEnum;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

/**
 * JWT身份过滤器
 *
 * @author Joshua
 * @version 1.0
 * @date 22/11/2023 0:51
 */
@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private final RedisService redisService;

    /**
     * 实例化
     */
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, RedisService redisService) {
        super(authenticationManager);
        this.redisService = redisService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = JwtTokenService.getToken();
        // 如果请求头中没有Authorization信息则直接放行了
        if (ObjectUtil.isEmpty(token)) {
            chain.doFilter(request, response);
            return;
        }

        // 请求头有token但token无法解析
        TokenInfo tokenInfo = JwtTokenService.getPayload(token);
        if (ObjectUtil.isEmpty(tokenInfo)) {
            log.info("从token中未获取到用户名, token：{}, URI：{}", token, request.getServletPath());
            chain.doFilter(request, response);
            return;
        }

        assert tokenInfo != null;
        // 从缓存中验证token的存在性
        UserInfo user = redisService.getCacheObject("userCache: " + tokenInfo.getUserName());
        if (ObjectUtil.isEmpty(user)) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(JSONUtil.toJsonStr(Result.fail(AuthExceptionEnum.NOT_LOGGED_IN)));
            response.getWriter().flush();
            return;
        }

        // 如果从持久化存储中仍未查到，则执行后续操作，最后返回用户不存在信息到前端
        if (ObjectUtil.isNotEmpty(user)) {
            // 创建验证通过的令牌对象
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            // 设置令牌到安全上下文中
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }

}
