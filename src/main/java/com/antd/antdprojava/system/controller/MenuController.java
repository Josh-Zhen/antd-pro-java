package com.antd.antdprojava.system.controller;

import com.antd.antdprojava.common.page.PageResult;
import com.antd.antdprojava.common.response.Result;
import com.antd.antdprojava.system.entity.Menu;
import com.antd.antdprojava.system.entity.vo.MenuDataVO;
import com.antd.antdprojava.system.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单控制层
 *
 * @author Joshua
 * @version 1.0
 * @date 19/11/2023 01:16
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    /**
     * 根据权限获取菜单列表
     *
     * @return 菜单列表
     */
    @GetMapping("")
    public Result<List<MenuDataVO>> tree() {
        return Result.success(menuService.tree());
    }

    /**
     * 分页查询菜单
     *
     * @param menu 菜单实体
     * @return 菜单集合
     */
    @GetMapping("/pageList")
    public Result<PageResult<Menu>> page(Menu menu) {
        return Result.success(menuService.pageList(menu));
    }

    /**
     * 查询菜单列表
     *
     * @param menu 菜单实体
     * @return 菜单集合
     */
    @GetMapping("/list")
    public Result<List<Menu>> list(Menu menu) {
        return Result.success(menuService.selectMenuList(menu));
    }

    /**
     * 新增菜单
     *
     * @param menu 菜单实体
     * @return 结果
     */
    @PostMapping("/save")
    public Result<Boolean> addSave(@RequestBody Menu menu) {
        return Result.success(menuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     *
     * @param menu 菜单实体
     * @return 结果
     */
    @PostMapping("/update")
    public Result<Boolean> editSave(@RequestBody Menu menu) {
        return Result.success(menuService.updateMenu(menu));
    }

    /**
     * 批量删除菜单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(String ids) {
        return Result.success(menuService.deleteMenuByIds(ids));
    }

}
