package com.antd.antdprojava.system.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用戶token信息实体类
 *
 * @author Joshua
 * @version 1.0
 * @date 18/11/2023 21:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoToken implements Serializable {

    @Serial
    private static final long serialVersionUID = -1248012255701212382L;

    /**
     * id
     */
    private Long id;

    /**
     * token
     */
    private String token;

}
