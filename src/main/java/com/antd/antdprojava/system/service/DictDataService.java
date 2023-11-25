package com.antd.antdprojava.system.service;

import com.antd.antdprojava.common.page.PageResult;
import com.antd.antdprojava.system.entity.DictData;
import com.antd.antdprojava.system.entity.vo.DictVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 系统字典值业务层（小类）
 *
 * @author Joshua
 * @version 1.0
 * @date 25/11/2023 16:31
 */
public interface DictDataService extends IService<DictData> {

    /**
     * 通过字典类型id查询字典name和value
     *
     * @param dictTypeId 主键id
     * @return 结果集
     */
    List<DictVO> findDictDataListByDictTypeId(Long dictTypeId);

    /**
     * 分页查询字典值(以父id查询)
     *
     * @param dictData 字典值实体
     * @return 字典值集合
     */
    PageResult<DictData> pageList(DictData dictData);

    /**
     * 新增字典值
     *
     * @param dictData 字典值实体
     * @return 结果
     */
    Boolean insertDictData(DictData dictData);

    /**
     * 修改字典值
     *
     * @param dictData 字典值实体
     * @return 结果
     */
    Boolean updateDictData(DictData dictData);

}
