package com.antd.antdprojava.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.antd.antdprojava.common.exception.BusinessException;
import com.antd.antdprojava.common.page.PageFactory;
import com.antd.antdprojava.common.page.PageResult;
import com.antd.antdprojava.system.constant.DictErrorEnum;
import com.antd.antdprojava.system.entity.DictData;
import com.antd.antdprojava.system.entity.DictType;
import com.antd.antdprojava.system.entity.vo.DictVO;
import com.antd.antdprojava.system.mapper.DictTypeMapper;
import com.antd.antdprojava.system.service.DictDataService;
import com.antd.antdprojava.system.service.DictTypeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 系统字典类型业务实现层（大类）
 *
 * @author Joshua
 * @version 1.0
 * @date 25/11/2023 16:42
 */
@Service
@RequiredArgsConstructor
public class DictTypeServiceImpl extends ServiceImpl<DictTypeMapper, DictType> implements DictTypeService {

    private final DictDataService dictDataService;

    /**
     * 根据code查询字典所有值
     *
     * @param code 编码
     * @return 结果集
     */
    @Override
    public List<DictVO> dropDown(String code) {
        LambdaQueryWrapper<DictType> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(DictType::getCode, code);
        DictType one = this.getOne(queryWrapper);
        if (ObjectUtil.isNull(one)) {
            throw new BusinessException(DictErrorEnum.DICT_TYPE_NOT_EXIST);
        }
        return dictDataService.findDictDataListByDictTypeId(one.getId());
    }

    /**
     * 分页查询字典类型
     *
     * @param dictType 字典类型实体
     * @return 字典类型集合
     */
    @Override
    public PageResult<DictType> pageList(DictType dictType) {
        LambdaQueryWrapper<DictType> queryWrapper = Wrappers.lambdaQuery();
        if (ObjectUtil.isNotEmpty(dictType)) {
            if (ObjectUtil.isNotEmpty(dictType.getName())) {
                queryWrapper.like(DictType::getName, dictType.getName());
            }
            if (ObjectUtil.isNotEmpty(dictType.getCode())) {
                queryWrapper.like(DictType::getCode, dictType.getCode());
            }
        }
        queryWrapper.orderByDesc(DictType::getCreateDate);
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    /**
     * 新增字典类型
     *
     * @param dictType 字典类型实体
     * @return 结果
     */
    @Override
    public Boolean insertDictType(DictType dictType) {
        this.checkCodeExists(dictType);
        dictType.setCreateDate(LocalDateTime.now());
        return this.save(dictType);
    }

    /**
     * 修改字典类型
     *
     * @param dictType 字典类型实体
     * @return 结果
     */
    @Override
    public Boolean updateDictType(DictType dictType) {
        this.checkCodeExists(dictType);
        return this.updateById(dictType);
    }

    /**
     * 删除字典类型信息，会删除子类内的值
     *
     * @param id 字典类型id
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 30)
    public Boolean deleteDictTypeById(Long id) {
        LambdaQueryWrapper<DictData> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(DictData::getTypeId, id);
        dictDataService.remove(wrapper);
        return this.removeById(id);
    }

    /**
     * 校验是否存在相同编号
     *
     * @param dictType 参数
     */
    private void checkCodeExists(DictType dictType) {
        LambdaQueryWrapper<DictType> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(DictType::getCode, dictType.getCode());
        DictType one = this.getOne(queryWrapper);
        if (null != one && !dictType.getId().equals(one.getId())) {
            if (dictType.getCode().equals(one.getCode())) {
                throw new BusinessException(DictErrorEnum.CODE_ALREADY_EXISTS);
            }
        }
    }

}
