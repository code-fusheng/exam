package xyz.fusheng.exam.core.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.dto.PaperRuleDto;
import xyz.fusheng.exam.core.entity.PaperRule;
import xyz.fusheng.exam.core.mapper.PaperRuleMapper;
import xyz.fusheng.exam.core.service.PaperRuleService;
import xyz.fusheng.exam.core.vo.PaperRuleVo;

import java.util.Arrays;
import java.util.List;

/**
  * @author:   code-fusheng
  * @Date:     2020/10/14 12:29
  */
@Service
public class PaperRuleServiceImpl implements PaperRuleService{

    @Resource
    private PaperRuleMapper paperRuleMapper;

     /**
      * 添加试卷规则
      * @param paperRuleDto
      */
     @Override
     public void save(PaperRuleDto paperRuleDto) {
         PaperRule paperRule = new PaperRule();
         BeanUtils.copyProperties(paperRuleDto, paperRule);
         // 设置创建者
         paperRule.setCreatedBy(paperRuleDto.getOperationUserId());
         paperRuleMapper.insert(paperRule);
     }

    /**
     * 删除试卷规则
     * @param paperRuleIds
     */
     @Override
     public void deleteByIds(Long[] paperRuleIds) {
         List<Long> ids = Arrays.asList(paperRuleIds);
         if (ids !=null && ids.size() > 0) {
             paperRuleMapper.deleteBatchIds(ids);
         }
     }

    /**
     * 修改试题
     * @param paperRuleDto
     */
    @Override
    public void update(PaperRuleDto paperRuleDto) {
        PaperRule paperRule = new PaperRule();
        BeanUtils.copyProperties(paperRuleDto, paperRule);
        paperRule.setUpdateBy(paperRuleDto.getOperationUserId());
        paperRuleMapper.updateById(paperRule);
    }

    /**
     * 查询试卷规则详情
     * @param paperRuleId
     * @return
     */
    @Override
    public PaperRuleVo getById(Long paperRuleId) {
        // 关联查询创建者和更新者
        PaperRuleVo paperRuleVo = paperRuleMapper.getPaperRuleVoById(paperRuleId);
        return paperRuleVo;
    }

    /**
     * 分页查询试卷规则详情
     * @param page
     * @return
     */
    @Override
    public Page<PaperRuleVo> getByPage(Page<PaperRuleVo> page) {
        // 查询数据
        List<PaperRuleVo> paperRuleVoList = paperRuleMapper.getByPage(page);
        page.setList(paperRuleVoList);
        // 统计总数
        int totalCount = paperRuleMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public List<PaperRule> getSimpleRuleList() {
        List<PaperRule> paperRuleList = paperRuleMapper.getSimpleRuleList();
        return paperRuleList;
    }


}
