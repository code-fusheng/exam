package xyz.fusheng.exam.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.exam.common.enums.ResultEnums;
import xyz.fusheng.exam.common.utils.*;
import xyz.fusheng.exam.core.dto.RepositoryDto;
import xyz.fusheng.exam.core.entity.Repository;
import xyz.fusheng.exam.core.service.RepositoryService;
import xyz.fusheng.exam.core.vo.QuestionVo;
import xyz.fusheng.exam.core.vo.RepositoryVo;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName: RepositoryController
 * @Author: code-fusheng
 * @Date: 2020/10/7 23:23
 * @version: 1.0
 * Description: 题库控制类
 */

@RestController
@RequestMapping("/repository")
@Api(tags = "题库接口", value = "试题题库管理接口")
public class RepositoryController {

    @Resource
    private RepositoryService repositoryService;

    /**
     * 添加题库
     * @param repositoryDto
     * @return
     */
    @ApiOperation(value = "添加题库", notes = "添加题库")
    @PostMapping("/save")
    public Result<Object> save(@RequestBody RepositoryDto repositoryDto) {
        // 雪花算法生成ID
        repositoryDto.setRepositoryId(new IdWorker().nextId());
        repositoryDto.setOperationUserId(SecurityUtil.getUserId());
        repositoryService.save(repositoryDto);
        return new Result<>("操作提示: 添加成功!");
    }

    /**
     * 删除题库
     * @param repositoryIds
     * @return
     */
    @ApiOperation(value = "删除题库", notes = "删除题库")
    @DeleteMapping("/deleteByIds/{repositoryIds}")
    public Result<Object> deleteByIds(@PathVariable Long[] repositoryIds) {
        repositoryService.deleteByIds(repositoryIds);
        return new Result<>("操作提示：删除成功!");
    }

    /**
     * 修改题库
     * @param repositoryDto
     * @return
     */
    @ApiOperation(value = "修改题库", notes = "修改题库")
    @PutMapping("/update")
    public Result<Object> update(@RequestBody RepositoryDto repositoryDto) {
        // 修改之前会进行一次查询操作，所以会有缓存，此时再查询同样的数据效率会高一些，此处并不影响性能
        repositoryDto.setVersion(repositoryService.getById(repositoryDto.getRepositoryId()).getVersion());
        repositoryDto.setOperationUserId(SecurityUtil.getUserId());
        repositoryService.update(repositoryDto);
        return new Result<>("操作提示: 修改成功!");
    }

    /**
     * 根据id查询题库详情
     * @param repositoryId
     * @return
     */
    @ApiOperation(value = "查询单个题库详情", notes = "根据id查询题库详情")
    @GetMapping("/getById/{repositoryId}")
    public Result<RepositoryVo> getById(@PathVariable Long repositoryId) {
        RepositoryVo repositoryVo = repositoryService.getById(repositoryId);
        return new Result<>("操作提示: 查询成功!", repositoryVo);
    }

    /**
     * 分页查询题库
     * @param page
     * @return
     */
    @ApiOperation(value = "分页查询题库详情", notes = "分页查询题库详情")
    @PostMapping("/getByPage")
    public Result<Page<RepositoryVo>> getByPage(@RequestBody Page<RepositoryVo> page) {
        String sortColumn = page.getSortColumn();
        // 驼峰转下划线
        String newSortColumn = StringUtils.upperCharToUnderLine(sortColumn);
        page.setSortColumn(newSortColumn);
        if (StringUtils.isNotBlank(sortColumn)) {
            // 题库名、题目总数、创建时间、更新时间
            String[] sortColumns = {"repository_name", "question_count", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(newSortColumn.toLowerCase())) {
                return new Result<>(ResultEnums.ERROR.getCode(), "操作提示: 参数错误!");
            }
        }
        page = repositoryService.getByPage(page);
        return new Result<>("操作提示: 分页查询成功!", page);
    }

}
