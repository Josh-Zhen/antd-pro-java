package com.antd.antdprojava.system.entity;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统字典值实体类（小类）
 *
 * @author Joshua
 * @version 1.0
 * @date 25/11/2023 16:24
 */
@Data
public class DictData implements Serializable {

    @Serial
    private static final long serialVersionUID = -3355436418062495569L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 字典类型id
     */
    private Long typeId;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 编码
     */
    private String value;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态（0停用 1正常）
     */
    private Boolean state;

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
