package com.antd.antdprojava.system.controller;

import com.antd.antdprojava.common.page.PageResult;
import com.antd.antdprojava.common.response.Result;
import com.antd.antdprojava.system.entity.Role;
import com.antd.antdprojava.system.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色控制层
 *
 * @author Joshua
 * @version 1.0
 * @date 19/11/2023 01:16
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    /**
     * 分页查询角色
     *
     * @param role 角色实体
     * @return 角色集合
     */
    @GetMapping("/pageList")
    public Result<PageResult<Role>> page(Role role) {
        return Result.success(roleService.pageList(role));
    }

    /**
     * 查询角色列表
     *
     * @param role 角色实体
     * @return 角色集合
     */
    @GetMapping("/list")
    public Result<List<Role>> list(Role role) {
        return Result.success(roleService.selectRoleList(role));
    }

    /**
     * 新增角色
     *
     * @param role 角色实体
     * @return 结果
     */
    @PostMapping("/save")
    public Result<Boolean> addSave(@RequestBody Role role) {
        return Result.success(roleService.insertRole(role));
    }

    /**
     * 修改角色
     *
     * @param role 角色实体
     * @return 结果
     */
    @PostMapping("/update")
    public Result<Boolean> editSave(@RequestBody Role role) {
        return Result.success(roleService.updateRole(role));
    }

    /**
     * 批量删除角色
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(String ids) {
        return Result.success(roleService.deleteRoleByIds(ids));
    }

}
