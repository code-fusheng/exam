package xyz.fusheng.exam.core.service;

import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.dto.AnswerDto;
import xyz.fusheng.exam.core.vo.AnswerVo;

/**
 * @author: code-fusheng
 * @Date: 2020/10/9 18:50
 */
public interface AnswerService {

    /**
     * 添加答案
     *
     * @param answerDto
     */
    void save(AnswerDto answerDto);

    /**
     * 删除答案
     *
     * @param answerIds
     */
    void deleteByIds(Long[] answerIds);

    /**
     * 修改答案
     * @param answerDto
     */
    void update(AnswerDto answerDto);

    /**
     * 根据id查询答案详情
     *
     * @param answerId
     * @return
     */
    AnswerVo getById(Long answerId);

    /**
     * 分页查询答案视图详情
     *
     * @param page
     * @return
     */
    Page<AnswerVo> getByPage(Page<AnswerVo> page);

}
