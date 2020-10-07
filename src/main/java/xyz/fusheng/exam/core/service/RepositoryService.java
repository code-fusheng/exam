package xyz.fusheng.exam.core.service;

import xyz.fusheng.exam.core.dto.RepositoryDto;
import xyz.fusheng.exam.core.vo.RepositoryVo;

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
}
