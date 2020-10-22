package xyz.fusheng.exam.core.service;

import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.dto.RepositoryDto;
import xyz.fusheng.exam.core.entity.Repository;
import xyz.fusheng.exam.core.vo.RepositoryVo;

import java.util.List;

/**
 * @author: code-fusheng
 * @Date: 2020/10/7 23:09
 */
public interface RepositoryService {

    /**
     * 添加题库
     * @param repositoryDto
     */
    void save(RepositoryDto repositoryDto);

    /**
     * 删除题库
     * @param repositoryIds
     */
    void deleteByIds(Long[] repositoryIds);

    /**
     * 修改题库
     * @param repositoryDto
     */
    void update(RepositoryDto repositoryDto);

    /**
     * 根据id查询题库详情
     * @param repositoryId
     * @return
     */
    RepositoryVo getById(Long repositoryId);

    /**
     * 自定义分页查询题库
     * @param page
     * @return
     */
    Page<RepositoryVo> getByPage(Page<RepositoryVo> page);

    /**
     * 查询所有可用题库
     * @return
     */
    List<Repository> getList();
}
