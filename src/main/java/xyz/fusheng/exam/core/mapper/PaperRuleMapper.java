package xyz.fusheng.exam.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.entity.PaperRule;
import xyz.fusheng.exam.core.vo.PaperRuleVo;

import java.util.List;

/**
  * @author:   code-fusheng
  * @Date:     2020/10/14 12:29
  */
public interface PaperRuleMapper extends BaseMapper<PaperRule> {

    /**
     * 根据id查询试卷规则视图
     * @param paperRuleId
     * @return
     */
    PaperRuleVo getPaperRuleVoById(Long paperRuleId);

    /**
     * 分页查询总数
     * @param page
     * @return
     */
    List<PaperRuleVo> getByPage(Page<PaperRuleVo> page);

    /**
     * 统计分页总数
     * @param page
     * @return
     */
    int getCountByPage(Page<PaperRuleVo> page);

    /**
     * 获取规则列表
     * @return
     */
    List<PaperRule> getSimpleRuleList();
}
