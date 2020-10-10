package xyz.fusheng.exam.core.service;

import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.dto.DictTypeDto;
import xyz.fusheng.exam.core.entity.DictType;
import xyz.fusheng.exam.core.vo.DictTypeVo;

import java.util.List;

/**
 * @author: code-fusheng
 * @Date: 2020/10/9 8:32
 */
public interface DictTypeService {

    /**
     * 检查字典类型是否唯一
     * @param dictId
     * @param dictType
     * @return
     */
    boolean checkDictTypeUnique(Long dictId, String dictType);

    /**
     * 添加字典类型
     * @param dictTypeDto
     */
    void save(DictTypeDto dictTypeDto);

    /**
     * 删除字典类型
     * @param dictIds
     */
    void deleteByIds(Long[] dictIds);

    /**
     * 修改字典类型
     * @param dictTypeDto
     */
    void update(DictTypeDto dictTypeDto);

    /**
     * 根据id查询字典类型详情
     * @param dictId
     * @return
     */
    DictTypeVo getById(Long dictId);

    /**
     * 查询所有可用字典类型
     * @return
     */
    List<DictType> getList();

    /**
     * 自定义分页查询字典类型视图
     * @param page
     * @return
     */
    Page<DictTypeVo> getByPage(Page<DictTypeVo> page);
}
