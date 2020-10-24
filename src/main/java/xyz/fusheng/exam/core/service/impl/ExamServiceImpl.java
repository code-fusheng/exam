package xyz.fusheng.exam.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import xyz.fusheng.exam.common.utils.IdWorker;
import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.dto.ExamDto;
import xyz.fusheng.exam.core.entity.Exam;
import xyz.fusheng.exam.core.entity.ExamPaper;
import xyz.fusheng.exam.core.entity.PaperRule;
import xyz.fusheng.exam.core.entity.QuestionReply;
import xyz.fusheng.exam.core.mapper.*;
import xyz.fusheng.exam.core.service.ExamService;
import xyz.fusheng.exam.core.vo.ExamVo;
import xyz.fusheng.exam.core.vo.PaperRuleVo;
import xyz.fusheng.exam.core.vo.PaperVo;
import xyz.fusheng.exam.core.vo.QuestionReplyVo;

import java.util.Arrays;
import java.util.List;

/**
 * @author: code-fusheng
 * @Date: 2020/10/19 18:44
 */
@Service
public class ExamServiceImpl implements ExamService {

    @Resource
    private ExamMapper examMapper;

    @Resource
    private PaperMapper paperMapper;

    @Resource
    private ExamPaperMapper examPaperMapper;

    @Resource
    private PaperRuleMapper paperRuleMapper;

    @Resource
    private QuestionReplyMapper questionReplyMapper;

    /**
     * 添加考试
     *
     * @param examDto
     */
    @Override
    public void saveExam(ExamDto examDto) {
        Exam exam = new Exam();
        BeanUtils.copyProperties(examDto, exam);
        // 设置创建者
        exam.setCreatedBy(examDto.getOperationUserId());
        examMapper.insert(exam);
    }

    /**
     * 删除考试
     *
     * @param examIds
     */
    @Override
    public void deleteExamByIds(Long[] examIds) {
        List<Long> ids = Arrays.asList(examIds);
        if (ids != null && ids.size() > 0) {
            examMapper.deleteBatchIds(ids);
        }
    }

    /**
     * 查询考试信息视图
     * @param examId
     * @return
     */
    @Override
    public ExamVo getExamVoById(Long examId) {
        // 查询视图对象
        ExamVo examVo = examMapper.getExamVoById(examId);
        Long currentPaper = paperMapper.getCurrentPaperByExamId(examId);
        examVo.setCurrentPaperId(currentPaper);
        return examVo;
    }

    /**
     * 更新考试
     * @param examDto
     */
    @Override
    public void updateExam(ExamDto examDto) {
        Exam exam = new Exam();
        BeanUtils.copyProperties(examDto, exam);
        exam.setUpdateBy(examDto.getOperationUserId());
        examMapper.updateById(exam);
        // 更新考试试卷
        examPaperMapper.deleteById(examDto.getExamId());
        ExamPaper examPaper = new ExamPaper();
        examPaper.setExamId(examDto.getExamId());
        examPaper.setPaperId(examDto.getCurrentPaperId());
        examPaperMapper.insert(examPaper);
    }

    /**
     * 自定义分页查询考试
     * @param page
     * @return
     */
    @Override
    public Page<ExamVo> getExamVoByPage(Page<ExamVo> page) {
        // 查询数据
        List<ExamVo> examVoList = examMapper.getExamVoByPage(page);
        page.setList(examVoList);
        // 统计分页总数
        int totalCount = examMapper.getTotalCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    /**
     * 获取试卷集合信息
     * @param examId
     * @return
     */
    @Override
    public List<PaperVo> getPaperVoListByExamId(Long examId) {
        List<PaperVo> paperVoList = paperMapper.getPaperVoListByExamId(examId);
        return paperVoList;
    }

    /**
     * 交卷
     * @param questionReplyVoList
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveQuestionReplyList(List<QuestionReplyVo> questionReplyVoList) {
        questionReplyVoList.forEach(questionReplyVo -> {
            questionReplyVo.setUserAnswer(questionReplyVo.getUserAnswerForFront().toString());
            QuestionReply questionReply = new QuestionReply();
            BeanUtils.copyProperties(questionReplyVo, questionReply);
            questionReply.setReplyId(new IdWorker().nextId());
            questionReplyMapper.insert(questionReply);
        });
    }

}
