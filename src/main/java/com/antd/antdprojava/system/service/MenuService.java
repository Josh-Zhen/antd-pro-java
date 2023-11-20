package com.antd.antdprojava.system.service;

import com.antd.antdprojava.common.page.PageResult;
import com.antd.antdprojava.system.entity.Menu;
import com.antd.antdprojava.system.entity.vo.MenuDataVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 菜单业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 19/11/2023 01:16
 */
public interface MenuService extends IService<Menu> {

    /**
     * 获取菜单列表
     *
     * @return 菜单列表
     */
    List<MenuDataVO> tree();

    /**
     * 分页查询菜单
     *
     * @param menu 菜单实体
     * @return 菜单集合
     */
    PageResult<Menu> pageList(Menu menu);

    /**
     * 查询菜单列表
     *
     * @param menu 菜单实体
     * @return 菜单集合
     */
    List<Menu> selectMenuList(Menu menu);

    /**
     * 新增菜单
     *
     * @param menu 菜单实体
     * @return 结果
     */
    Boolean insertMenu(Menu menu);

    /**
     * 修改菜单
     *
     * @param menu 菜单实体
     * @return 结果
     */
    Boolean updateMenu(Menu menu);

    /**
     * 批量删除菜单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    Boolean deleteMenuByIds(String ids);

}
