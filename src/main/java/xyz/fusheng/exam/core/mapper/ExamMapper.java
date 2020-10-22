package xyz.fusheng.exam.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.entity.Exam;
import xyz.fusheng.exam.core.vo.ExamVo;

import java.util.List;

/**
  * @author:   code-fusheng
  * @Date:     2020/10/19 18:44
  */
public interface ExamMapper extends BaseMapper<Exam> {
    /**
     * 查询考试视图信息
     * @param examId
     * @return
     */
     ExamVo getExamVoById(Long examId);

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<ExamVo> getExamVoByPage(Page<ExamVo> page);

    /**
     * 获取分页总数
     * @param page
     * @return
     */
    int getTotalCountByPage(Page<ExamVo> page);
}
