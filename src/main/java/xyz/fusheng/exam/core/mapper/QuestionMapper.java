package xyz.fusheng.exam.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.entity.Question;
import xyz.fusheng.exam.core.vo.QuestionVo;

import java.util.List;

/**
  * @author:   code-fusheng
  * @Date:     2020/10/9 18:30
  */
public interface QuestionMapper extends BaseMapper<Question> {

    /**
     * 根据id查询试题视图
     * @param questionId
     * @return
     */
    QuestionVo getQuestionVoById(Long questionId);

    /**
     * 分页查询试题
     * @param page
     * @return
     */
    List<QuestionVo> getByPage(Page<QuestionVo> page);

    /**
     * 统计试题总数
     * @param page
     * @return
     */
    int getCountByPage(Page<QuestionVo> page);

    /**
     * 获取试题内容给前台通过id
     * @param questionId
     * @return
     */
    Question getQuestionContentForFrontById(Long questionId);

    /**
     * 根据试题id获取中间表题库Ids
     * @param questionId
     * @return
     */
    List<Long> getRepositoryByQuestionId(Long questionId);

    /**
     * 根据试卷编号获取试题编号列表
     * @param paperId
     * @return
     */
    List<Long> getQuestionIdsByPaperId(Long paperId);

    /**
     * 根据试卷编号与试题编号查询试题排序
     * @param paperId
     * @param questionId
     * @return
     */
    Integer getQuestionSortByPaperIdAndQuestionId(Long paperId, Long questionId);

    /**
     * 分页查询考试试卷试题
     * @param page
     * @return
     */
    List<QuestionVo> getQuestionVoListByPageForFront(Page<QuestionVo> page);

    /**
     * 获取考试试卷试题分页总数
     * @param page
     * @return
     */
    int getTotalCountByPageForFront(Page<QuestionVo> page);

    /**
     * 获取题库试题列表分页
     * @param page
     * @return
     */
    List<Question> getQuestionListByPage(Page<Question> page);

    /**
     * 统计分页总数
     * @param page
     * @return
     */
    int getTotalCountByPageForQuestionList(Page<Question> page);
}
