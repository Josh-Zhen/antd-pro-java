package com.antd.antdprojava.common.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Token信息内容实体
 *
 * @author Joshua
 * @version 1.0
 * @date 22/11/2023 1:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenInfo {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户帐户
     */
    private String userName;

}

