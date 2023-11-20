package com.antd.antdprojava.system.entity;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * 菜单实体
 *
 * @author Joshua
 * @version 1.0
 * @date 18/11/2023 0:29
 */
@Data
public class Menu implements Serializable {

    @Serial
    private static final long serialVersionUID = 7080297857368354453L;

    /**
     * id
     */
    private Long id;

    /**
     * 显示顺序
     * 99[def]
     */
    private Long orderNum;

    /**
     * 标题
     */
    private String title;

    /**
     * 图标
     */
    private String icon;

    /**
     * 组件(Iframe、RouteView、ComponentError)
     */
    private String component;

    /**
     * 路径
     */
    private String path;

    /**
     * 重定向路径
     */
    private String redirect;

    /**
     * 固定页签(0:否[def]，1:是)
     */
    private Boolean affix;

    /**
     * 父级菜单id
     * 0[def]
     */
    private Long parentId;

    /**
     * 同路由中的名称，用于保活的作用
     */
    private String name;

    /**
     * 是否隐藏菜单(0:否[def]，1:是)
     */
    private Boolean hideInMenu;

    /**
     * 如果使用了隐藏，那么点击当前菜单时，可以使用父级的key
     */
    private ArrayList<String> parentKeys;

    /**
     * 超链接
     * 如果当前是iframe的模式，需要有一個跳转的url支撑，其不能和path重复，path还是为路由
     */
    private String url;

    /**
     * 是否存在面包屑(0:否[def]，1:是)
     */
    private Boolean hideInBreadcrumb;

    /**
     * 是否显示所有子菜单(0:否[def]，1:是)
     */
    private Boolean hideChildrenInMenu;

    /**
     * 是否保活(0:否[def]，1：是)
     */
    private Boolean keepAlive;

    /**
     * 全连接跳转模式('_blank' | '_self' | '_parent')
     */
    private String target;

    /**
     * 备注
     */
    private String remark;

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
