package xyz.fusheng.exam.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.entity.Answer;
import xyz.fusheng.exam.core.vo.AnswerVo;

import java.util.List;

/**
 * @author: code-fusheng
 * @Date: 2020/10/9 18:50
 */
public interface AnswerMapper extends BaseMapper<Answer> {

    /**
     * 根据id查询答案详情
     * @param answerId
     * @return
     */
    AnswerVo getAnswerVoById(Long answerId);

    /**
     * 分页查询数据详情
     * @param page
     * @return
     */
    List<AnswerVo> getByPage(Page<AnswerVo> page);

    /**
     * 统计分页总数
     * @param page
     * @return
     */
    int getCountByPage(Page<AnswerVo> page);

    /**
     * 根据题目id查询试题选项
     * @param questionId
     * @return
     */
    List<Answer> getAnswerContentForFrontByQuestionId(Long questionId);

    /**
     * 根据试题编号查询选项列表（不带答案标志）
     * @param questionId
     * @return
     */
    List<Answer> getAnswerListByQuestionIdForFront(Long questionId);
}

