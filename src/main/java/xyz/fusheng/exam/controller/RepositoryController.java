package xyz.fusheng.exam.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.exam.common.utils.IdWorker;
import xyz.fusheng.exam.common.utils.Result;
import xyz.fusheng.exam.common.utils.SecurityUtil;
import xyz.fusheng.exam.core.dto.RepositoryDto;
import xyz.fusheng.exam.core.entity.Repository;
import xyz.fusheng.exam.core.service.RepositoryService;
import xyz.fusheng.exam.core.vo.RepositoryVo;

import javax.annotation.Resource;

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
    @GetMapping("/getById/{repositoryId}")
    public Result<RepositoryVo> getById(@PathVariable Long repositoryId) {
        RepositoryVo repositoryVo = repositoryService.getById(repositoryId);
        return new Result<>("操作提示: 查询成功!", repositoryVo);
    }

}
