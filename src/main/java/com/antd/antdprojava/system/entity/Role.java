package com.antd.antdprojava.system.entity;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色实体
 *
 * @author Joshua
 * @version 1.0
 * @date 19/11/2023 01:16
 */
@Data
public class Role implements Serializable {

    @Serial
    private static final long serialVersionUID = -50826044604873094L;

    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 权限标识
     */
    private String roleKey;

    /**
     * 状态
     */
    private Integer state;

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
