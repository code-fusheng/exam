package xyz.fusheng.exam.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import xyz.fusheng.exam.common.enums.StateEnums;
import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.entity.DictType;
import xyz.fusheng.exam.core.dto.DictTypeDto;
import xyz.fusheng.exam.core.mapper.DictTypeMapper;
import xyz.fusheng.exam.core.service.DictTypeService;
import xyz.fusheng.exam.core.vo.DictTypeVo;

import java.util.Arrays;
import java.util.List;

/**
  * @author:   code-fusheng
  * @Date:     2020/10/9 8:32
  */
@Service
public class DictTypeServiceImpl implements DictTypeService{

    @Resource
    private DictTypeMapper dictTypeMapper;

     /**
      * 检查字典类型是否唯一
      * @param dictId
      * @param dictType
      * @return
      */
     @Override
     public boolean checkDictTypeUnique(Long dictId, String dictType) {
         // 修改和插入的时候检查是否已经存在相同的字典分类
         dictId = (dictId == null) ? -1L : dictId;
         QueryWrapper<DictType> qw = new QueryWrapper<>();
         qw.lambda().eq(DictType::getDictType, dictType);
         DictType sysDictType = dictTypeMapper.selectOne(qw);
         if (null != sysDictType && dictId.longValue() != sysDictType.getDictId().longValue()) {
             return true;
         }
         return false;
     }

     /**
      * 添加字典类型
      * @param dictTypeDto
      */
     @Override
     public void save(DictTypeDto dictTypeDto) {
         DictType dictType = new DictType();
         BeanUtils.copyProperties(dictTypeDto, dictType);
        // 设置创建人
         dictType.setCreatedBy(dictTypeDto.getOperationUserId());
         dictTypeMapper.insert(dictType);
     }

    /**
     * 删除字典类型
     * @param dictIds
     */
     @Override
     public void deleteByIds(Long[] dictIds) {
         List<Long> ids = Arrays.asList(dictIds);
         if (ids != null && ids.size() > 0) {
             dictTypeMapper.deleteBatchIds(ids);
         }
     }

    /**
     * 更新字典类型
     * @param dictTypeDto
     */
    @Override
    public void update(DictTypeDto dictTypeDto) {
        DictType dictType = new DictType();
        BeanUtils.copyProperties(dictTypeDto, dictType);
        // 设置更新者
        dictType.setUpdateBy(dictTypeDto.getOperationUserId());
        dictTypeMapper.updateById(dictType);
    }

    /**
     * 根据id查询字典类型视图
     * @param dictId
     * @return
     */
    @Override
    public DictTypeVo getById(Long dictId) {
        // 关联查询创建者和更新者用户名
        DictTypeVo dictTypeVo = dictTypeMapper.getDictTypeVoById(dictId);
        return dictTypeVo;
    }

    /**
     * 查询所有可用字典类型列表
     * @return
     */
    @Override
    public List<DictType> getList() {
        QueryWrapper<DictType> qw = new QueryWrapper<>();
        qw.lambda().eq(DictType::getIsEnabled, StateEnums.ENABLED.getCode());
        List<DictType> dictTypeList = dictTypeMapper.selectList(qw);
        return dictTypeList;
    }

    /**
     * 自定义分页查询字典类型
     * @param page
     * @return
     */
    @Override
    public Page<DictTypeVo> getByPage(Page<DictTypeVo> page) {
        // 查询数据
        List<DictTypeVo> dictTypeVoList = dictTypeMapper.getByPage(page);
        page.setList(dictTypeVoList);
        // 统计总数
        int totalCount = dictTypeMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

}
