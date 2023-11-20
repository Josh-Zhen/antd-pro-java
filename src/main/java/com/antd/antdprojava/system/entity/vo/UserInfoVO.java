package com.antd.antdprojava.system.entity.vo;

import lombok.Data;

import java.util.ArrayList;

/**
 * 登录返回的用户信息
 *
 * @author Joshua
 * @version 1.0
 * @date 18/11/2023 19:09
 */
@Data
public class UserInfoVO {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号码
     */
    private String number;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 权限
     */
    private ArrayList<String> roles;

}
