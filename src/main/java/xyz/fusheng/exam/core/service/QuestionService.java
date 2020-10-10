package xyz.fusheng.exam.core.service;

import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.dto.QuestionDto;
import xyz.fusheng.exam.core.vo.QuestionVo;

/**
 * @author: code-fusheng
 * @Date: 2020/10/9 18:30
 */
public interface QuestionService {

    /**
     * 添加试题
     * @param questionDto
     */
    void save(QuestionDto questionDto);

    /**
     * 删除试题
     * @param questionIds
     */
    void deleteByIds(Long[] questionIds);

    /**
     * 修改试题
     * @param questionDto
     */
    void update(QuestionDto questionDto);

    /**
     * 根据id查询试题详情
     * @param questionId
     * @return
     */
    QuestionVo getById(Long questionId);

    /**
     * 自定义分页查询试题
     * @param page
     * @return
     */
    Page<QuestionVo> getByPage(Page<QuestionVo> page);

    /**
     * 根据id获取试题与答案详情
     * @param questionId
     * @return
     */
    QuestionVo getQuestionVoWithAnswers(Long questionId);

    /**
     * 根据id获取试题与答案
     * @param questionId
     * @return
     */
    QuestionVo getQuestionVoWithAnswersForFrontById(Long questionId);
}
