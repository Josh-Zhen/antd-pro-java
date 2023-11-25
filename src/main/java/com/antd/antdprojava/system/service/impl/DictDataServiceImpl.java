package com.antd.antdprojava.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.antd.antdprojava.common.exception.BusinessException;
import com.antd.antdprojava.common.page.PageFactory;
import com.antd.antdprojava.common.page.PageResult;
import com.antd.antdprojava.system.constant.DictErrorEnum;
import com.antd.antdprojava.system.entity.DictData;
import com.antd.antdprojava.system.entity.vo.DictVO;
import com.antd.antdprojava.system.mapper.DictDataMapper;
import com.antd.antdprojava.system.service.DictDataService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 系统字典值业务实现层（小类）
 *
 * @author Joshua
 * @version 1.0
 * @date 25/11/2023 16:33
 */
@Service
public class DictDataServiceImpl extends ServiceImpl<DictDataMapper, DictData> implements DictDataService {

    /**
     * 通过字典类型id查询字典name和value
     *
     * @param dictTypeId 主键id
     * @return 结果集
     */
    @Override
    public List<DictVO> findDictDataListByDictTypeId(Long dictTypeId) {
        // 构造查询条件
        LambdaQueryWrapper<DictData> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(DictData::getTypeId, dictTypeId).eq(DictData::getState, true).orderByAsc(DictData::getSort);
        // 查询dictTypeId下所有的字典值
        List<DictData> results = this.list(queryWrapper);

        // 抽取code和value封装到map返回
        List<DictVO> dictList = new ArrayList<>();
        results.forEach(dictData -> dictList.add(new DictVO(dictData.getValue(), dictData.getName())));
        return dictList;
    }

    /**
     * 分页查询字典值(以父id查询)
     *
     * @param dictData 字典值实体
     * @return 字典值集合
     */
    @Override
    public PageResult<DictData> pageList(DictData dictData) {
        LambdaQueryWrapper<DictData> queryWrapper = Wrappers.lambdaQuery();
        if (ObjectUtil.isNotEmpty(dictData)) {
            if (ObjectUtil.isNotEmpty(dictData.getTypeId())) {
                queryWrapper.eq(DictData::getTypeId, dictData.getTypeId());
            }
            if (ObjectUtil.isNotEmpty(dictData.getName())) {
                queryWrapper.eq(DictData::getName, dictData.getName());
            }
            if (ObjectUtil.isNotEmpty(dictData.getValue())) {
                queryWrapper.eq(DictData::getValue, dictData.getValue());
            }
        }
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    /**
     * 新增字典值
     *
     * @param dictData 字典值实体
     * @return 结果
     */
    @Override
    public Boolean insertDictData(DictData dictData) {
        this.checkValueExists(dictData);
        dictData.setCreateDate(LocalDateTime.now());
        return this.save(dictData);
    }

    /**
     * 修改字典值
     *
     * @param dictData 字典值实体
     * @return 结果
     */
    @Override
    public Boolean updateDictData(DictData dictData) {
        this.checkValueExists(dictData);
        return this.updateById(dictData);
    }

    /**
     * 校验是否存在相同名称或键
     *
     * @param dictData 字典值实体
     */
    private void checkValueExists(DictData dictData) {
        LambdaQueryWrapper<DictData> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(DictData::getTypeId, dictData.getTypeId())
                .and(wq -> wq.eq(DictData::getName, dictData.getName())
                        .or()
                        .eq(DictData::getValue, dictData.getValue()));
        Collection<DictData> list = this.list(queryWrapper);
        if (list.size() > 0) {
            for (DictData data : list) {
                if (dictData.getName().equals(data.getName()) && !dictData.getId().equals(data.getId())) {
                    throw new BusinessException(DictErrorEnum.NAME_ALREADY_EXISTS);
                }
                if (dictData.getValue().equals(data.getValue()) && !dictData.getId().equals(data.getId())) {
                    throw new BusinessException(DictErrorEnum.VALUE_ALREADY_EXISTS);
                }
            }
        }
    }
}
