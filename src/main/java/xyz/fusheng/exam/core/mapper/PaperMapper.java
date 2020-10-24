package xyz.fusheng.exam.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.dto.QuestionDto;
import xyz.fusheng.exam.core.entity.Paper;
import xyz.fusheng.exam.core.vo.PaperVo;
import xyz.fusheng.exam.core.vo.QuestionVo;

import java.util.List;

/**
  * @author:   code-fusheng
  * @Date:     2020/10/15 15:02
  */
public interface PaperMapper extends BaseMapper<Paper> {

    /**
     * 根据id查询试卷视图信息
     * @param paperId
     * @return
     */
    PaperVo getPaperVoById(Long paperId);

    /**
     * 分页查询试卷
     * @param page
     * @return
     */
    List<PaperVo> getByPage(Page<PaperVo> page);

    /**
     * 统计分页总数
     * @param page
     * @return
     */
    int getCountByPage(Page<PaperVo> page);

    /**
     * 保存试题与试卷关系与分值
     * @param
     */
    void savePaperAndQuestionAndScore(QuestionDto questionDto);

    /**
     * 删除试卷中的试题
     * @param questionId
     * @param paperId
     */
    void deleteQuestionForPaperById(Long questionId, Long paperId);

    /**
     * 根据试题id判断当前问题是否有试卷使用
     * @param questionId
     * @return
     */
    int judgeExistQuestionUsedByPaperId(Long questionId);

    /**
     * 获取考试试卷视图列表通过考试编号
     * @param examId
     * @return
     */
    List<PaperVo> getPaperVoListByExamId(Long examId);

    /**
     * 获取当前考试试卷信息
     * @param examId
     * @return
     */
    Long getCurrentPaperByExamId(Long examId);
}
