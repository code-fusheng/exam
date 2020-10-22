package xyz.fusheng.exam.core.service;

import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.dto.QuestionDto;
import xyz.fusheng.exam.core.entity.Question;
import xyz.fusheng.exam.core.vo.QuestionVo;

import java.util.List;

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

    /**
     * 根据试题id判断当前问题是否有试卷使用
     * @param questionIds
     * @return
     */
    boolean judgeExistQuestionUsedByPaper(Long[] questionIds);

    /**
     * 分页查询考试试卷答案列表
     * @param page
     * @return
     */
    Page<QuestionVo> getQuestionVoListByPageForFront(Page<QuestionVo> page);

    /**
     * 分页查询试题题库列表
     * @param page
     * @return
     */
    Page<Question> getQuestionListByPage(Page<Question> page);

    /**
     * 后台获取试卷试题列表以及答案详情
     * @param paperId
     * @return
     */
    List<QuestionVo> getQuestionVoListByPaperIdForPaperInfo(Long paperId);
}
