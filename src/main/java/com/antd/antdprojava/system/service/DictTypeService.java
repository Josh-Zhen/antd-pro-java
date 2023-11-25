package com.antd.antdprojava.system.service;

import com.antd.antdprojava.common.page.PageResult;
import com.antd.antdprojava.system.entity.DictType;
import com.antd.antdprojava.system.entity.vo.DictVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 系统字典类型业务层（大类）
 *
 * @author Joshua
 * @version 1.0
 * @date 25/11/2023 16:40
 */
public interface DictTypeService extends IService<DictType> {

    /**
     * 根据code查询字典所有值
     *
     * @param code 编码
     * @return 结果集
     */
    List<DictVO> dropDown(String code);

    /**
     * 分页查询字典类型
     *
     * @param dictType 字典类型实体
     * @return 字典类型集合
     */
    PageResult<DictType> pageList(DictType dictType);

    /**
     * 新增字典类型
     *
     * @param dictType 字典类型实体
     * @return 结果
     */
    Boolean insertDictType(DictType dictType);

    /**
     * 修改字典类型
     *
     * @param dictType 字典类型实体
     * @return 结果
     */
    Boolean updateDictType(DictType dictType);

    /**
     * 删除字典类型信息，会删除子类内的值
     *
     * @param id 字典类型id
     * @return 结果
     */
    Boolean deleteDictTypeById(Long id);

}
