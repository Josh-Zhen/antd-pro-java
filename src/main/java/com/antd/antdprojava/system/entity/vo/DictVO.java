package com.antd.antdprojava.system.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 字典实体类
 *
 * @author Joshua
 * @version 1.0
 * @date 25/11/2023 16:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -1251015257102741241L;

    /**
     * 鍵值
     */
    private String key;

    /**
     * 名稱
     */
    private String name;

}
