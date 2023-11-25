package com.antd.antdprojava.system.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.antd.antdprojava.common.page.PageFactory;
import com.antd.antdprojava.common.page.PageResult;
import com.antd.antdprojava.common.security.SecurityUtils;
import com.antd.antdprojava.system.entity.Menu;
import com.antd.antdprojava.system.entity.vo.MenuDataVO;
import com.antd.antdprojava.system.mapper.MenuMapper;
import com.antd.antdprojava.system.service.MenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 菜单业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 19/11/2023 01:16
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    /**
     * 根据权限获取菜单列表
     *
     * @return 菜单列表
     */
    @Override
    public List<MenuDataVO> tree() {
        return baseMapper.tree(SecurityUtils.me().getRoles());
    }

    /**
     * 分页查询菜单
     *
     * @param menu 菜单实体
     * @return 菜单集合
     */
    @Override
    public PageResult<Menu> pageList(Menu menu) {
        LambdaQueryWrapper<Menu> queryWrapper = Wrappers.lambdaQuery();
        if (ObjectUtil.isNotNull(menu)) {
            if (ObjectUtil.isNotEmpty(menu.getId())) {
                queryWrapper.eq(Menu::getId, menu.getId());
            }
            if (ObjectUtil.isNotEmpty(menu.getName())) {
                queryWrapper.like(Menu::getName, menu.getName());
            }
            if (ObjectUtil.isNotEmpty(menu.getTitle())) {
                queryWrapper.eq(Menu::getTitle, menu.getTitle());
            }
        }
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    /**
     * 查询菜单列表
     *
     * @param menu 菜单实体
     * @return 菜单集合
     */
    @Override
    public List<Menu> selectMenuList(Menu menu) {
        LambdaQueryWrapper<Menu> queryWrapper = Wrappers.lambdaQuery();
        return this.list(queryWrapper);
    }

    /**
     * 新增菜单
     *
     * @param menu 菜单实体
     * @return 结果
     */
    @Override
    public Boolean insertMenu(Menu menu) {
        return this.save(menu);
    }

    /**
     * 修改菜单
     *
     * @param menu 菜单实体
     * @return 结果
     */
    @Override
    public Boolean updateMenu(Menu menu) {
        return this.updateById(menu);
    }

    /**
     * 批量删除菜单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public Boolean deleteMenuByIds(String ids) {
        return this.removeByIds(Arrays.asList(Convert.toStrArray(ids)));
    }

}
