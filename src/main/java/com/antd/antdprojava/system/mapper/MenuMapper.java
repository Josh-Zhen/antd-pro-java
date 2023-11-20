package com.antd.antdprojava.system.mapper;

import com.antd.antdprojava.system.entity.Menu;
import com.antd.antdprojava.system.entity.vo.MenuDataVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单映射层
 *
 * @author Joshua
 * @version 1.0
 * @date 19/11/2023 01:16
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 获取菜单列表
     *
     * @return 菜单列表
     */
    List<MenuDataVO> tree();

}
