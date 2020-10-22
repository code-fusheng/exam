package xyz.fusheng.exam.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.fusheng.exam.common.utils.Page;
import xyz.fusheng.exam.core.entity.Repository;
import xyz.fusheng.exam.core.vo.RepositoryVo;

import java.util.List;

/**
  * @author:   code-fusheng
  * @Date:     2020/10/7 23:09
  */
public interface RepositoryMapper extends BaseMapper<Repository> {

    /**
     * 查询题库视图
     * @param repositoryId
     * @return
     */
    RepositoryVo getRepositoryVoById(Long repositoryId);

    /**
     * 分页查询题库视图
     * @param page
     * @return
     */
    List<RepositoryVo> getByPage(Page<RepositoryVo> page);

    /**
     * 统计题库总数
     * @param page
     * @return
     */
    int getCountByPage(Page<RepositoryVo> page);

    /**
     * 获取可用题库列表
     * @return
     */
    List<Repository> getSimpleRepositoryList();

    /**
     * 存储题库与试题关系
     * @param repositoryId
     * @param questionId
     */
    void saveRepositoryAndQuestion(Long repositoryId, Long questionId);

    /**
     * 根据试题id查询题库编号
     * @param questionId
     * @return
     */
    Long getRepositoryIdByQuestionId(Long questionId);

    /**
     * 根据试题id删除关联信息
     * @param questionId
     */
    void deleteRepositoryAndQuestionByQuestionId(Long questionId);

}
