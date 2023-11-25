package com.antd.antdprojava.system.controller;

import com.antd.antdprojava.common.page.PageResult;
import com.antd.antdprojava.common.response.Result;
import com.antd.antdprojava.system.entity.DictType;
import com.antd.antdprojava.system.entity.vo.DictVO;
import com.antd.antdprojava.system.service.DictTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统字典类型控制层（大类）
 *
 * @author Joshua
 * @version 1.0
 * @date 25/11/2023 16:23
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/dict")
public class DictTypeController {

    private final DictTypeService dictTypeService;

    /**
     * 根据code查询字典所有值
     *
     * @param code 编码
     * @return 结果集
     */
    @GetMapping("/dropDown")
    public Result<List<DictVO>> dropDown(String code) {
        return Result.success(dictTypeService.dropDown(code));
    }

    /**
     * 分页查询字典类型
     *
     * @param dictType 字典类型实体
     * @return 字典类型集合
     */
    @GetMapping("/pageList")
    public Result<PageResult<DictType>> page(DictType dictType) {
        return Result.success(dictTypeService.pageList(dictType));
    }

    /**
     * 新增字典类型
     *
     * @param dictType 字典类型实体
     * @return 结果
     */
    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody DictType dictType) {
        return Result.success(dictTypeService.insertDictType(dictType));
    }

    /**
     * 修改字典类型
     *
     * @param dictType 字典类型实体
     * @return 结果
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody DictType dictType) {
        return Result.success(dictTypeService.updateDictType(dictType));
    }

    /**
     * 删除字典类型信息
     *
     * @param id 字典类型id
     * @return 结果
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(Long id) {
        return Result.success(dictTypeService.deleteDictTypeById(id));
    }

}
