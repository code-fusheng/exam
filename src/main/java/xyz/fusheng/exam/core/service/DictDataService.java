package xyz.fusheng.exam.core.service;

import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.dto.DictDataDto;
import xyz.fusheng.exam.core.entity.DictData;
import xyz.fusheng.exam.core.vo.DictDataVo;

import java.util.List;

/**
 * @author: code-fusheng
 * @Date: 2020/10/9 13:08
 */
public interface DictDataService {

    /**
     * 添加字典数据
     * @param dictDataDto
     */
    void save(DictDataDto dictDataDto);

    /**
     * 删除字典数据
     * @param dictIds
     */
    void deleteByIds(Long[] dictIds);

    /**
     * 查询字典数据详情
     * @param dictCode
     * @return
     */
    DictDataVo getById(Long dictCode);

    /**
     * 根据字典类型查询所有可用字典数据
     * @param dictType
     * @return
     */
    List<DictData> getList(String dictType);

    /**
     * 自定义分页查询数据
     * @param page
     * @return
     */
    Page<DictDataVo> getByPage(Page<DictDataVo> page);
}
