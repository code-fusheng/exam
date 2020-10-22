package xyz.fusheng.exam.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import xyz.fusheng.exam.common.utils.IdWorker;
import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.common.utils.StringUtils;
import xyz.fusheng.exam.core.dto.AnswerDto;
import xyz.fusheng.exam.core.dto.QuestionDto;
import xyz.fusheng.exam.core.entity.Answer;
import xyz.fusheng.exam.core.entity.PaperQuestion;
import xyz.fusheng.exam.core.entity.Question;
import xyz.fusheng.exam.core.entity.Repository;
import xyz.fusheng.exam.core.mapper.*;
import xyz.fusheng.exam.core.service.QuestionService;
import xyz.fusheng.exam.core.vo.AnswerVo;
import xyz.fusheng.exam.core.vo.QuestionVo;

import java.util.ArrayList;
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

    @Resource
    private RepositoryMapper repositoryMapper;

    @Resource
    private PaperMapper paperMapper;

    @Resource
    private PaperQuestionMapper paperQuestionMapper;

    /**
     * 添加试题
     * @param questionDto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(QuestionDto questionDto) {
        Question question = new Question();
        BeanUtils.copyProperties(questionDto, question);
        // 设置创建者
        question.setCreatedBy(questionDto.getOperationUserId());
        questionMapper.insert(question);
        // 保存试题与题库关联关系
        repositoryMapper.saveRepositoryAndQuestion(questionDto.getRepositoryId(), questionDto.getQuestionId());
        // 更新题库试题数
        Repository repository = repositoryMapper.selectById(questionDto.getRepositoryId());
        repository.setQuestionCount(repository.getQuestionCount() + 1);
        repositoryMapper.updateById(repository);
        // 判断视图对象中的答案列表是否为空，不为空时批量添加答案
        if (questionDto.getAnswerList().size() > 0) {
            List<Answer> answerList = questionDto.getAnswerList();
            for (Answer answer : answerList) {
                answer.setAnswerId(new IdWorker().nextId());
                answer.setCreatedBy(questionDto.getOperationUserId());
                answer.setQuestionId(questionDto.getQuestionId());
                answerMapper.insert(answer);
            }
        }
    }

    /**
     * 删除试题 - 试题页删除试题
     * @param questionIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(Long[] questionIds) {
        List<Long> ids = Arrays.asList(questionIds);
        for (Long questionId : ids) {
            List<Long> repositoryIds = questionMapper.getRepositoryByQuestionId(questionId);
            for (Long repositoryId : repositoryIds) {
                // 删除题库试题中间表
                repositoryMapper.deleteRepositoryAndQuestionByQuestionId(questionId);
                // 更新试题数根据题库编号
                Repository repository = repositoryMapper.selectById(repositoryId);
                repository.setQuestionCount(repository.getQuestionCount() - 1);
                repositoryMapper.updateById(repository);
            }
        }
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
        // 删除试题与题库关联关系
        repositoryMapper.deleteRepositoryAndQuestionByQuestionId(questionDto.getQuestionId());
        // 保存试题与题库关联关系
        repositoryMapper.saveRepositoryAndQuestion(questionDto.getRepositoryId(), questionDto.getQuestionId());
        // 判断视图对象中的答案列表是否为空，不为空时批量更新答案
        if (questionDto.getAnswerList().size() > 0) {
            List<Answer> answerList = questionDto.getAnswerList();
            for (Answer answer : answerList) {
                if ((answer.getAnswerId() == null)) {
                    answer.setAnswerId(new IdWorker().nextId());
                    answer.setCreatedBy(questionDto.getOperationUserId());
                    answer.setQuestionId(questionDto.getQuestionId());
                    answerMapper.insert(answer);
                } else {
                    answer.setUpdateBy(questionDto.getOperationUserId());
                    answerMapper.updateById(answer);
                }
            }
        }
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
        // 查询试题所属题库
        Long repositoryId = repositoryMapper.getRepositoryIdByQuestionId(questionId);
        questionVo.setRepositoryId(repositoryId);
        // 查询试题答案
        QueryWrapper<Answer> aqw = new QueryWrapper<>();
        aqw.lambda().eq(Answer::getQuestionId, questionId);
        List<Answer> answerList = answerMapper.selectList(aqw);
        questionVo.setAnswerList(answerList);
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
        // 查询试题答案列表
        QueryWrapper<Answer> aqw = new QueryWrapper<>();
        aqw.lambda().eq(Answer::getQuestionId, questionId);
        List<Answer> answerList = answerMapper.selectList(aqw);
        questionVo.setAnswerList(answerList);
        // 查询试题所属题库
        Long repositoryId = repositoryMapper.getRepositoryIdByQuestionId(questionId);
        questionVo.setRepositoryId(repositoryId);
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

    /**
     * 根据试题id判断当前问题是否有试卷使用
     * @param questionIds
     * @return
     */
    @Override
    public boolean judgeExistQuestionUsedByPaper(Long[] questionIds) {
        List<Long> questionList = Arrays.asList(questionIds);
        for (Long questionId : questionList) {
            int resultCount = paperMapper.judgeExistQuestionUsedByPaperId(questionId);
            if (resultCount > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 分页获取考试试卷问题列表
     * @param page
     * @return
     */
    @Override
    public Page<QuestionVo> getQuestionVoListByPageForFront(Page<QuestionVo> page) {
        // 查询考试试题题库列表
        List<QuestionVo> questionVoList =  questionMapper.getQuestionVoListByPageForFront(page);
        // 遍历试题列表
        questionVoList.forEach(questionVo -> {
              // 根据试题编号查询选项列表（不带答案标志）
              List<Answer> answerList = answerMapper.getAnswerListByQuestionIdForFront(questionVo.getQuestionId());
              questionVo.setAnswerList(answerList);
        });
        page.setList(questionVoList);
        // 统计分页总数
        int totalCount = questionMapper.getTotalCountByPageForFront(page);
        page.setTotalCount(totalCount);
        return page;
    }

    /**
     * 根据题库编号分页获取试题列表
     * @param page
     * @return
     */
    @Override
    public Page<Question> getQuestionListByPage(Page<Question> page) {
        // 查询题库试题列表
        List<Question> questionList = questionMapper.getQuestionListByPage(page);
        page.setList(questionList);
        // 统计分页总数
        int totalCount = questionMapper.getTotalCountByPageForQuestionList(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public List<QuestionVo> getQuestionVoListByPaperIdForPaperInfo(Long paperId) {
        // 查询试卷试题列表（试卷&试题中间表）
        List<Long> questionIds = questionMapper.getQuestionIdsByPaperId(paperId);
        List<QuestionVo> questionVoList = new ArrayList<>();
        // 遍历 questionIds
        questionIds.forEach(questionId -> {
            // 查询试题视图信息
            QuestionVo questionVo = questionMapper.getQuestionVoById(questionId);
            // 通过试卷编号与试题编号查询关系信息
            PaperQuestion paperQuestion = paperQuestionMapper.getPaperWithQuestionInfoById(paperId, questionId);
            // 将信息整理到试题视图对象
            questionVo.setQuestionSort(paperQuestion.getQuestionSort());
            questionVo.setQuestionScore(paperQuestion.getQuestionScore());
            // 查询试题答案列表
            QueryWrapper<Answer> aqw = new QueryWrapper<>();
            aqw.lambda().eq(Answer::getQuestionId, questionId);
            aqw.lambda().orderByAsc(Answer::getAnswerSort);
            List<Answer> answerList = answerMapper.selectList(aqw);
            questionVo.setAnswerList(answerList);
            // 查询试题所属题库
            Long repositoryId = repositoryMapper.getRepositoryIdByQuestionId(questionId);
            questionVo.setRepositoryId(repositoryId);
            questionVoList.add(questionVo);
        });
        return questionVoList;
    }
}
