package xyz.fusheng.exam.core.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.dto.AnswerDto;
import xyz.fusheng.exam.core.entity.Answer;
import xyz.fusheng.exam.core.mapper.AnswerMapper;
import xyz.fusheng.exam.core.service.AnswerService;
import xyz.fusheng.exam.core.vo.AnswerVo;

import java.util.Arrays;
import java.util.List;

/**
  * @author:   code-fusheng
  * @Date:     2020/10/9 18:50
  */
@Service
public class AnswerServiceImpl implements AnswerService{

    @Resource
    private AnswerMapper answerMapper;

     /**
      * 添加答案
      * @param answerDto
      */
     @Override
     public void save(AnswerDto answerDto) {
         Answer answer = new Answer();
         BeanUtils.copyProperties(answerDto, answer);
         answer.setCreatedBy(answerDto.getOperationUserId());
         answerMapper.insert(answer);
     }

    /**
     * 删除答案
     * @param answerIds
     */
     @Override
     public void deleteByIds(Long[] answerIds) {
         List<Long> ids = Arrays.asList(answerIds);
         if (ids != null && ids.size() > 0) {
             answerMapper.deleteBatchIds(ids);
         }
     }

    /**
     * 更新答案
     * @param answerDto
     */
    @Override
    public void update(AnswerDto answerDto) {
        Answer answer = new Answer();
        BeanUtils.copyProperties(answerDto, answer);
        // 设置修改者
        answer.setUpdateBy(answerDto.getOperationUserId());
        answerMapper.updateById(answer);
    }

    /**
     * 根据id查询答案详情
     * @param answerId
     * @return
     */
    @Override
    public AnswerVo getById(Long answerId) {
        // 关联查询创建者和更新者
        AnswerVo answerVo = answerMapper.getAnswerVoById(answerId);
        return answerVo;
    }

    /**
     * 分页查询答案详情
     * @param page
     * @return
     */
    @Override
    public Page<AnswerVo> getByPage(Page<AnswerVo> page) {
        // 查询数据
        List<AnswerVo> answerVoList = answerMapper.getByPage(page);
        page.setList(answerVoList);
        // 统计总数
        int totalCount = answerMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }
}
