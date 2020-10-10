package xyz.fusheng.exam.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.entity.DictData;
import xyz.fusheng.exam.core.vo.DictDataVo;

import java.util.List;

/**
  * @author:   code-fusheng
  * @Date:     2020/10/9 13:08
  */
public interface DictDataMapper extends BaseMapper<DictData> {

    /**
     * 查询字典数据详情
     * @param dictCode
     * @return
     */
    DictDataVo getDictDataVoById(Long dictCode);

    /**
     * 分页查询字典数据视图
     * @param page
     * @return
     */
    List<DictDataVo> getByPage(Page<DictDataVo> page);

    /**
     * 统计字典数据总数
     * @param page
     * @return
     */
    int getCountByPage(Page<DictDataVo> page);
}
