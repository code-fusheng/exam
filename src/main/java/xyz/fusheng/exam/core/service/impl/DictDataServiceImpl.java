package xyz.fusheng.exam.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import xyz.fusheng.exam.common.enums.StateEnums;
import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.dto.DictDataDto;
import xyz.fusheng.exam.core.entity.DictData;
import xyz.fusheng.exam.core.mapper.DictDataMapper;
import xyz.fusheng.exam.core.service.DictDataService;
import xyz.fusheng.exam.core.vo.DictDataVo;

import java.util.Arrays;
import java.util.List;

/**
  * @author:   code-fusheng
  * @Date:     2020/10/9 13:08
  */
@Service
public class DictDataServiceImpl implements DictDataService{

    @Resource
    private DictDataMapper dictDataMapper;

     /**
      * 添加字典数据
      * @param dictDataDto
      */
     @Override
     public void save(DictDataDto dictDataDto) {
         DictData dictData = new DictData();
         BeanUtils.copyProperties(dictDataDto, dictData);
         // 设置创建者
         dictData.setCreatedBy(dictDataDto.getOperationUserId());
         dictDataMapper.insert(dictData);
     }

    /**
     * 删除字典数据
     * @param dictCodes
     */
     @Override
     public void deleteByIds(Long[] dictCodes) {
         List<Long> ids = Arrays.asList(dictCodes);
         if (ids != null && ids.size() > 0) {
             dictDataMapper.deleteBatchIds(ids);
         }
     }

    /**
     * 查询字典数据详情
     * @param dictCode
     * @return
     */
    @Override
    public DictDataVo getById(Long dictCode) {
        // 关联查询创建者和更新者
        DictDataVo dictDataVo = dictDataMapper.getDictDataVoById(dictCode);
        return dictDataVo;
    }

    /**
     * 根据字典类型查询可用字典数据列表
     * @param dictType
     * @return
     */
    @Override
    public List<DictData> getList(String dictType) {
        QueryWrapper<DictData> qw = new QueryWrapper<>();
        qw.lambda().eq(DictData::getDictType, dictType);
        qw.lambda().eq(DictData::getIsEnabled, StateEnums.ENABLED.getCode());
        List<DictData> dictDataList = dictDataMapper.selectList(qw);
        return dictDataList;
    }

    /**
     * 自定义分页处查询数据
     * @param page
     * @return
     */
    @Override
    public Page<DictDataVo> getByPage(Page<DictDataVo> page) {
        // 查询数据
        List<DictDataVo> dictDataVoList = dictDataMapper.getByPage(page);
        page.setList(dictDataVoList);
        // 统计总数
        int totalCount = dictDataMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }
}
