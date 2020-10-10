package xyz.fusheng.exam.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.dto.QuestionDto;
import xyz.fusheng.exam.core.entity.Answer;
import xyz.fusheng.exam.core.entity.Question;
import xyz.fusheng.exam.core.mapper.AnswerMapper;
import xyz.fusheng.exam.core.mapper.QuestionMapper;
import xyz.fusheng.exam.core.service.QuestionService;
import xyz.fusheng.exam.core.vo.AnswerVo;
import xyz.fusheng.exam.core.vo.QuestionVo;

import java.util.Arrays;
import java.util.List;

/**
 * @author: code-fusheng
 * @Date: 2020/10/9 18:30
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private AnswerMapper answerMapper;

    /**
     * 添加试题
     * @param questionDto
     */
    @Override
    public void save(QuestionDto questionDto) {
        Question question = new Question();
        BeanUtils.copyProperties(questionDto, question);
        // 设置创建者
        question.setCreatedBy(questionDto.getOperationUserId());
        questionMapper.insert(question);
    }

    /**
     * 删除试题
     * @param questionIds
     */
    @Override
    public void deleteByIds(Long[] questionIds) {
        List<Long> ids = Arrays.asList(questionIds);
        if (ids != null && ids.size() > 0) {
            questionMapper.deleteBatchIds(ids);
        }
    }

    /**
     * 更新试题
     * @param questionDto
     */
    @Override
    public void update(QuestionDto questionDto) {
        Question question = new Question();
        BeanUtils.copyProperties(questionDto, question);
        // 设置修改者
        question.setUpdateBy(questionDto.getOperationUserId());
        questionMapper.updateById(question);
    }

    /**
     * 根据id查询试题视图
     * @param questionId
     * @return
     */
    @Override
    public QuestionVo getById(Long questionId) {
        // 关联查询创建者和更新者
        QuestionVo questionVo = questionMapper.getQuestionVoById(questionId);
        // 查询试题答案?
        return questionVo;
    }

    /**
     * 自定义分页查询试题
     * @param page
     * @return
     */
    @Override
    public Page<QuestionVo> getByPage(Page<QuestionVo> page) {
        // 查询数据
        List<QuestionVo> questionVoList = questionMapper.getByPage(page);
        page.setList(questionVoList);
        // 统计总数
        int totalCount = questionMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    /**
     * 根据id获取试题与答案信息
     * @param questionId
     * @return
     */
    @Override
    public QuestionVo getQuestionVoWithAnswers(Long questionId) {
        QuestionVo questionVo = questionMapper.getQuestionVoById(questionId);
        QueryWrapper<Answer> aqw = new QueryWrapper<>();
        aqw.lambda().eq(Answer::getQuestionId, questionId);
        List<Answer> answerList = answerMapper.selectList(aqw);
        questionVo.setAnswerList(answerList);
        return questionVo;
    }

    /**
     * 根据id获取试题与答案
     * @param questionId
     * @return
     */
    @Override
    public QuestionVo getQuestionVoWithAnswersForFrontById(Long questionId) {
        QuestionVo questionVo = new QuestionVo();
        Question question = questionMapper.getQuestionContentForFrontById(questionId);
        BeanUtils.copyProperties(question, questionVo);
        // 获取题目的答案
        List<Answer> answerList = answerMapper.getAnswerContentForFrontByQuestionId(questionId);
        questionVo.setAnswerList(answerList);
        return questionVo;
    }

}
