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
}
