package com.antd.antdprojava.common.security;

import com.antd.antdprojava.common.redis.RedisService;
import com.antd.antdprojava.common.security.entity.AuthReleaseProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * 权限配置类
 *
 * @author Joshua
 * @version 1.0
 * @date 20/11/2023 18:03
 */
@EnableCaching
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableConfigurationProperties(AuthReleaseProperties.class)
public class SecurityConfig {

    private final AuthReleaseProperties authReleaseProperties;
    @Lazy
    private final AuthenticationManager authenticationManager;
    private final RedisService redisService;
    private final UserDetailsService userDetailsService;

    /**
     * 过滤器
     *
     * @param http 认证设置
     * @return 响应
     * @throws Exception 异常
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 禁用 csrf 和 session 并开启跨域
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(corsConfigurer -> corsConfigurer.configurationSource(configurationSource()));
        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 资源设置
        http.authorizeHttpRequests(authorize -> authorize
                // 资源放行
                .requestMatchers(authReleaseProperties.getPermitStatic()).permitAll()
                .requestMatchers(authReleaseProperties.getPermitMethod()).permitAll()
                // 全部鉴权
                .anyRequest().authenticated()
        );

        // jwt配置
        http.addFilter(new JwtAuthenticationFilter(authenticationManager, redisService));

//        http.oauth2ResourceServer(oauth2 -> oauth2
//                .jwt(jwtConfigurer -> jwtConfigurer
//                        .jwtAuthenticationConverter(jwt -> {
//                            Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");
//                            Collection<String> roles = realmAccess.get("roles");
//                            List<SimpleGrantedAuthority> grantedAuthorities = roles.stream()
//                                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//                                    .toList();
//                            return new JwtAuthenticationToken(jwt, grantedAuthorities);
//                        })
//                )
//        );

        // 异常处理
        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(new UserAuthenticationEntryPoint())
                .accessDeniedHandler(new PerAccessDeniedHandler())
        );

        http.userDetailsService(userDetailsService);
        return http.build();
    }

    /**
     * 认证配置
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * 密码加密方式配置
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 跨域配置
     */
    CorsConfigurationSource configurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setMaxAge(3600L);
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}
