package xyz.fusheng.exam.core.service;

import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.dto.ExamDto;
import xyz.fusheng.exam.core.vo.ExamVo;
import xyz.fusheng.exam.core.vo.PaperVo;

import java.util.List;

/**
 * @author: code-fusheng
 * @Date: 2020/10/19 18:44
 */
public interface ExamService {

    /**
     * 添加考试
     * @param examDto
     */
    void saveExam(ExamDto examDto);

    /**
     * 删除考试
     * @param examIds
     */
    void deleteExamByIds(Long[] examIds);

    /**
     * 获取考试视图信息
     * @param examId
     * @return
     */
    ExamVo getExamVoById(Long examId);

    /**
     * 更新考试
     * @param examDto
     */
    void updateExam(ExamDto examDto);

    /**
     * 分页查询考试
     * @param page
     * @return
     */
    Page<ExamVo> getExamVoByPage(Page<ExamVo> page);

    /**
     * 根据考试编号获取试卷信息集合
     * @param examId
     * @return
     */
    List<PaperVo> getPaperVoListByExamId(Long examId);
}
