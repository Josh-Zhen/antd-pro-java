package com.antd.antdprojava.system.entity;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户实体
 *
 * @author Joshua
 * @version 1.0
 * @date 18/11/2023 21:04
 */
@Data
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = -1341896683867168331L;

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String number;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 状态（0正常 1停用）
     */
    private Boolean state;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime updateDate;

}
