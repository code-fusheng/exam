package xyz.fusheng.exam.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.fusheng.exam.core.entity.PaperQuestion;

/**
 * @author: code-fusheng
 * @Date: 2020/10/19 11:15
 */
public interface PaperQuestionMapper extends BaseMapper<PaperQuestion> {

    /**
     * 根据试卷编号与试题编号查询中间信息
     *
     * @param paperId
     * @param questionId
     * @return
     */
    PaperQuestion getPaperWithQuestionInfoById(Long paperId, Long questionId);

}
