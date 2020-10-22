package xyz.fusheng.exam.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import xyz.fusheng.exam.common.utils.IdWorker;
import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.dto.PaperDto;
import xyz.fusheng.exam.core.dto.QuestionDto;
import xyz.fusheng.exam.core.entity.*;
import xyz.fusheng.exam.core.mapper.*;
import xyz.fusheng.exam.core.service.PaperService;
import xyz.fusheng.exam.core.vo.PaperRuleVo;
import xyz.fusheng.exam.core.vo.PaperVo;
import xyz.fusheng.exam.core.vo.QuestionVo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: code-fusheng
 * @Date: 2020/10/15 15:02
 */
@Service
public class PaperServiceImpl implements PaperService {

    @Resource
    private PaperMapper paperMapper;

    @Resource
    private PaperRuleMapper paperRuleMapper;

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private AnswerMapper answerMapper;

    @Resource
    private RepositoryMapper repositoryMapper;

    @Resource
    private PaperQuestionMapper paperQuestionMapper;

    /**
     * 添加试卷
     *
     * @param paperDto
     */
    @Override
    public void save(PaperDto paperDto) {
        Paper paper = new Paper();
        BeanUtils.copyProperties(paperDto, paper);
        paper.setCreatedBy(paperDto.getOperationUserId());
        paperMapper.insert(paper);
    }

    /**
     * 添加试卷试题
     *
     * @param questionDto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveQuestionVoForPaper(QuestionDto questionDto) {
        Question question = new Question();
        BeanUtils.copyProperties(questionDto, question);
        // 设置创建者
        question.setCreatedBy(questionDto.getOperationUserId());
        questionMapper.insert(question);
        // 保存试题与题库关系
        repositoryMapper.saveRepositoryAndQuestion(questionDto.getRepositoryId(), questionDto.getQuestionId());
        // 判断视图对象中的答案列表是否为空，不为空时批量更新答案
        if (questionDto.getAnswerList().size() > 0) {
            List<Answer> answerList = questionDto.getAnswerList();
            for (Answer answer : answerList) {
                answer.setAnswerId(new IdWorker().nextId());
                answer.setCreatedBy(questionDto.getOperationUserId());
                answer.setQuestionId(questionDto.getQuestionId());
                answerMapper.insert(answer);
            }
        }
        // 更新题库试题数
        Repository repository = repositoryMapper.selectById(questionDto.getRepositoryId());
        repository.setQuestionCount(repository.getQuestionCount() + 1);
        repositoryMapper.updateById(repository);
        // 保存试题与试卷关系
        PaperQuestion paperQuestion = new PaperQuestion();
        paperQuestion.setPaperId(questionDto.getPaperId());
        paperQuestion.setQuestionId(questionDto.getQuestionId());
        paperQuestion.setQuestionTypeId(questionDto.getQuestionTypeId());
        paperQuestion.setQuestionSort(questionDto.getQuestionSort());
        paperQuestion.setQuestionScore(questionDto.getQuestionScore());
        paperQuestionMapper.insert(paperQuestion);
    }

    /**
     * 从题库选择实体添加到试卷
     * @param questionIds
     * @param paperId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSelectQuestionVoForPaper(List<Long> questionIds, Long paperId) {
        questionIds.forEach(questionId -> {
            Question question = questionMapper.selectById(questionId);
            PaperQuestion paperQuestion = new PaperQuestion();
            paperQuestion.setPaperId(paperId);
            paperQuestion.setQuestionId(questionId);
            paperQuestion.setQuestionTypeId(question.getQuestionTypeId());
            paperQuestionMapper.insert(paperQuestion);
        });
    }

    /**
     * 获取试卷中的试题信息
     * @param questionId
     * @param paperId
     * @return
     */
    @Override
    public QuestionVo getQuestionVoForPaperByQuestionIdAndPaperId(Long questionId, Long paperId) {
        QuestionVo questionVo = questionMapper.getQuestionVoById(questionId);
        // 查询试题答案列表
        QueryWrapper<Answer> aqw = new QueryWrapper<>();
        aqw.lambda().eq(Answer::getQuestionId, questionId);
        List<Answer> answerList = answerMapper.selectList(aqw);
        questionVo.setAnswerList(answerList);
        // 查询试题所属题库
        Long repositoryId = repositoryMapper.getRepositoryIdByQuestionId(questionId);
        questionVo.setRepositoryId(repositoryId);
        // 查询试题与试卷关系
        QueryWrapper<PaperQuestion> pqqw = new QueryWrapper<>();
        pqqw.lambda().eq(PaperQuestion::getQuestionId, questionId);
        pqqw.lambda().eq(PaperQuestion::getPaperId, paperId);
        PaperQuestion paperQuestion = paperQuestionMapper.selectOne(pqqw);
        questionVo.setQuestionSort(paperQuestion.getQuestionSort());
        questionVo.setQuestionScore(paperQuestion.getQuestionScore());
        return questionVo;
    }

    /**
     * 修改试卷试题信息
     * @param questionDto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateQuestionVoForPaper(QuestionDto questionDto) {
        // 修改试题信息
        Question question = new Question();
        BeanUtils.copyProperties(questionDto, question);
        // 设置修改者
        question.setUpdateBy(questionDto.getOperationUserId());
        questionMapper.updateById(question);
        // 更新试题与题库的关系
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
        // 更新试题在试卷中的拓展信息
        PaperQuestion paperQuestion = new PaperQuestion();
        paperQuestion.setQuestionTypeId(questionDto.getQuestionTypeId());
        paperQuestion.setQuestionSort(questionDto.getQuestionSort());
        paperQuestion.setQuestionScore(questionDto.getQuestionScore());
        UpdateWrapper<PaperQuestion> pquw = new UpdateWrapper<>();
        pquw.lambda().eq(PaperQuestion::getPaperId, questionDto.getPaperId());
        pquw.lambda().eq(PaperQuestion::getQuestionId, questionDto.getQuestionId());
        paperQuestionMapper.update(paperQuestion, pquw);
    }

    /**
     * 删除试卷中的试题
     *
     * @param questionId
     * @param paperId
     */
    @Override
    public void deleteQuestionForPaperById(Long questionId, Long paperId) {
        paperMapper.deleteQuestionForPaperById(questionId, paperId);
    }

    /**
     * 删除试卷
     *
     * @param paperIds
     */
    @Override
    public void deleteByIds(Long[] paperIds) {
        List<Long> ids = Arrays.asList(paperIds);
        if (ids != null && ids.size() > 0) {
            paperMapper.deleteBatchIds(ids);
        }
    }

    /**
     * 更新试卷信息
     * @param paperDto
     */
    @Override
    public void update(PaperDto paperDto) {
        Paper paper = new Paper();
        BeanUtils.copyProperties(paperDto, paper);
        paper.setUpdateBy(paperDto.getOperationUserId());
        paperMapper.updateById(paper);
    }

    /**
     * 根据id查询试卷基础对象信息
     *
     * @param paperId
     * @return
     */
    @Override
    public PaperVo getById(Long paperId) {
        PaperVo paperVo = paperMapper.getPaperVoById(paperId);
        return paperVo;
    }

    /**
     * 分页查询试卷详情
     *
     * @param page
     * @return
     */
    @Override
    public Page<PaperVo> getByPage(Page<PaperVo> page) {
        // 查询数据
        List<PaperVo> paperVoList = paperMapper.getByPage(page);
        page.setList(paperVoList);
        // 统计总数
        int totalCount = paperMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    /**
     * 后台查询试卷视图以及规则信息以及试题答案列表
     *
     * @param paperId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public PaperVo getPaperVoWithPaperRuleVoWithQuestionVosById(Long paperId) {
        // 查询基础的试卷详情
        PaperVo paperVo = paperMapper.getPaperVoById(paperId);
        // 查询试卷规则详情
        PaperRuleVo paperRuleVo = paperRuleMapper.getPaperRuleVoById(paperVo.getPaperRuleId());
        // 设置试卷视图对象试卷规则
        paperVo.setPaperRuleVo(paperRuleVo);
        return paperVo;
    }

    /**
     * 获取试卷与考试规则信息
     * @param paperId
     * @return
     */
    @Override
    public PaperVo getPaperVoWithRuleByPaperId(Long paperId) {
        // 获取试卷基础视图对象
        PaperVo paperVo = paperMapper.getPaperVoById(paperId);
        // 获取试卷规则信息
        PaperRuleVo paperRuleVo = paperRuleMapper.getPaperRuleVoById(paperVo.getPaperRuleId());
        paperVo.setPaperRuleVo(paperRuleVo);
        return paperVo;
    }

}
