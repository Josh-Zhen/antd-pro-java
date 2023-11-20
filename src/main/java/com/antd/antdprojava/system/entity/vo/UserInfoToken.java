package com.antd.antdprojava.system.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用戶token信息
 *
 * @author Joshua
 * @version 1.0
 * @date 18/11/2023 21:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoToken {

    /**
     * id
     */
    private Long id;

    /**
     * token
     */
    private String token;

}
