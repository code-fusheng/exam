package xyz.fusheng.exam.core.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.common.utils.SecurityUtil;
import xyz.fusheng.exam.core.dto.RepositoryDto;
import xyz.fusheng.exam.core.entity.Repository;
import xyz.fusheng.exam.core.mapper.RepositoryMapper;
import xyz.fusheng.exam.core.service.RepositoryService;
import xyz.fusheng.exam.core.vo.RepositoryVo;

import java.util.Arrays;
import java.util.List;

/**
  * @author:   code-fusheng
  * @Date:     2020/10/7 23:09
  */
@Service
public class RepositoryServiceImpl implements RepositoryService{

    @Resource
    private RepositoryMapper repositoryMapper;

     /**
      * 添加题库
      * @param repositoryDto
      */
     @Override
     public void save(RepositoryDto repositoryDto) {
         Repository repository = new Repository();
         BeanUtils.copyProperties(repositoryDto, repository);
         // 设置创建人
         repository.setCreatedBy(repositoryDto.getOperationUserId());
         repositoryMapper.insert(repository);
     }

    /**
     * 删除题库
     * @param repositoryIds
     */
     @Override
     public void deleteByIds(Long[] repositoryIds) {
         List<Long> ids = Arrays.asList(repositoryIds);
         if (ids != null && ids.size() > 0) {
             repositoryMapper.deleteBatchIds(ids);
         }
     }

    /**
     * 更新题库
     * @param repositoryDto
     */
    @Override
    public void update(RepositoryDto repositoryDto) {
        Repository repository = new Repository();
        BeanUtils.copyProperties(repositoryDto, repository);
        // 设置修改人
        repository.setUpdateBy(repositoryDto.getOperationUserId());
        repositoryMapper.updateById(repository);
    }

    /**
     * 查询题库视图
     * @param repositoryId
     * @return
     */
    @Override
    public RepositoryVo getById(Long repositoryId) {
         // 关联查询创建者和更新者用户名
         RepositoryVo repositoryVo = repositoryMapper.getRepositoryVoById(repositoryId);
         // 查询题库试题数
         return repositoryVo;
    }

    /**
     * 自定义分页查询题库
     * @param page
     * @return
     */
    @Override
    public Page<RepositoryVo> getByPage(Page<RepositoryVo> page) {
        // 查询数据
        List<RepositoryVo> repositoryVoList = repositoryMapper.getByPage(page);
        page.setList(repositoryVoList);
        // 统计总数
        int totalCount = repositoryMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

}
