package com.antd.antdprojava.system.controller;

import com.antd.antdprojava.common.page.PageResult;
import com.antd.antdprojava.common.response.Result;
import com.antd.antdprojava.system.entity.DictData;
import com.antd.antdprojava.system.service.DictDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 系统字典值控制层（小类）
 *
 * @author Joshua
 * @version 1.0
 * @date 25/11/2023 16:23
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/dict/data")
public class DictDataController {

    private final DictDataService dictDataService;

    /**
     * 分页查询字典值(以父id查询)
     *
     * @param dictData 字典值实体
     * @return 字典值集合
     */
    @GetMapping("/pageList")
    public Result<PageResult<DictData>> page(DictData dictData) {
        return Result.success(dictDataService.pageList(dictData));
    }

    /**
     * 新增字典值
     *
     * @param dictData 字典值实体
     * @return 结果
     */
    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody DictData dictData) {
        return Result.success(dictDataService.insertDictData(dictData));
    }

    /**
     * 修改字典值
     *
     * @param dictData 字典值实体
     * @return 结果
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody DictData dictData) {
        return Result.success(dictDataService.updateDictData(dictData));
    }

    /**
     * 删除字典值
     *
     * @param id 主键
     * @return 结果
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(Long id) {
        return Result.success(dictDataService.removeById(id));
    }

}
