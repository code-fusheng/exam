package xyz.fusheng.exam.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.entity.DictType;
import xyz.fusheng.exam.core.vo.DictTypeVo;

import java.util.List;

/**
  * @author:   code-fusheng
  * @Date:     2020/10/9 8:32
  */
public interface DictTypeMapper extends BaseMapper<DictType> {

    /**
     * 根据id查询字典类型详情
     * @param dictId
     * @return
     */
    DictTypeVo getDictTypeVoById(Long dictId);

    /**
     * 分页查询字典类型视图
     * @param page
     * @return
     */
    List<DictTypeVo> getByPage(Page<DictTypeVo> page);

    /**
     * 统计字典类型总数
     * @param page
     * @return
     */
    int getCountByPage(Page<DictTypeVo> page);
}
