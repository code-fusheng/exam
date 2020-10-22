package xyz.fusheng.exam.core.service;

import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.dto.PaperDto;
import xyz.fusheng.exam.core.dto.QuestionDto;
import xyz.fusheng.exam.core.vo.PaperVo;
import xyz.fusheng.exam.core.vo.QuestionVo;

import java.util.List;

/**
 * @author: code-fusheng
 * @Date: 2020/10/15 15:02
 */
public interface PaperService {

    /**
     * 添加试卷
     * @param paperDto
     */
    void save(PaperDto paperDto);

    /**
     * 删除试卷
     * @param paperIds
     */
    void deleteByIds(Long[] paperIds);

    /**
     * 修改试卷
     * @param paperDto
     */
    void update(PaperDto paperDto);

    /**
     * 根据id查询试卷详情
     * @param paperId
     * @return
     */
    PaperVo getById(Long paperId);

    /**
     * 自定义分页查询试卷
     * @param page
     * @return
     */
    Page<PaperVo> getByPage(Page<PaperVo> page);

    /**
     * 后台查询试卷视图以及规则信息以及试题答案列表
     * @param paperId
     * @return
     */
    PaperVo getPaperVoWithPaperRuleVoWithQuestionVosById(Long paperId);

    /**
     * 添加试卷试题
     * @param questionDto
     */
    void saveQuestionVoForPaper(QuestionDto questionDto);

    /**
     * 删除试卷试题
     * @param questionId
     * @param paperId
     */
    void deleteQuestionForPaperById(Long questionId, Long paperId);

    /**
     * 获取试卷与考试规则信息
     * @param paperId
     * @return
     */
    PaperVo getPaperVoWithRuleByPaperId(Long paperId);

    /**
     * 从题库选择试题添加
     * @param questionIds
     * @param paperId
     */
    void saveSelectQuestionVoForPaper(List<Long> questionIds, Long paperId);

    /**
     * 查询试卷中试题详情
     * @param questionId
     * @param paperId
     * @return
     */
    QuestionVo getQuestionVoForPaperByQuestionIdAndPaperId(Long questionId, Long paperId);

    /**
     * 修改试卷试题信息
     * @param questionDto
     */
    void updateQuestionVoForPaper(QuestionDto questionDto);
}
