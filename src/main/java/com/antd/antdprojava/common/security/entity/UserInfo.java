package com.antd.antdprojava.common.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 用户信息详情
 *
 * @author Joshua
 * @version 1.0
 * @date 21/11/2023 16:06
 */
@Data
@JsonIgnoreProperties({"enabled", "accountNonExpired", "accountNonLocked", "credentialsNonExpired", "authorities"})
public class UserInfo implements UserDetails {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 状态 0:禁用，1:正常
     */
    private Boolean state;

    /**
     * 手机号码
     */
    private String number;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 角色
     */
    private ArrayList<String> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        // 将权限列表放进集合内
        roles.forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role)));
        return grantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return state;
    }

    @Override
    public boolean isAccountNonLocked() {
        return state;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return state;
    }

    @Override
    public boolean isEnabled() {
        return state;
    }
}
