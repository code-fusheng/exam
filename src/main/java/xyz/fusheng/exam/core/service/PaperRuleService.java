package xyz.fusheng.exam.core.service;

import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.dto.PaperRuleDto;
import xyz.fusheng.exam.core.entity.PaperRule;
import xyz.fusheng.exam.core.vo.PaperRuleVo;

import java.util.List;

/**
 * @author: code-fusheng
 * @Date: 2020/10/14 12:29
 */
public interface PaperRuleService {

    /**
     * 添加试卷规则
     * @param paperRuleDto
     */
    void save(PaperRuleDto paperRuleDto);

    /**
     * 删除试卷规则
     * @param paperRuleIds
     */
    void deleteByIds(Long[] paperRuleIds);

    /**
     * 修改试卷规则而
     * @param paperRuleDto
     */
    void update(PaperRuleDto paperRuleDto);

    /**
     * 查询试卷规则详情
     * @param paperRuleId
     * @return
     */
    PaperRuleVo getById(Long paperRuleId);

    /**
     * 分页查询试卷规则详情
     * @param page
     * @return
     */
    Page<PaperRuleVo> getByPage(Page<PaperRuleVo> page);

    /**
     * 查询试卷规则列表
     * @return
     */
    List<PaperRule> getSimpleRuleList();
}
