package xyz.fusheng.exam.controller; /**
 * @author: code-fusheng
 * @Date: 2020/10/10 10:13
 */

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.exam.common.enums.ResultEnums;
import xyz.fusheng.exam.common.utils.*;
import xyz.fusheng.exam.core.dto.AnswerDto;
import xyz.fusheng.exam.core.entity.Answer;
import xyz.fusheng.exam.core.service.AnswerService;
import xyz.fusheng.exam.core.vo.AnswerVo;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName: AnswerController
 * @Author: code-fusheng
 * @Date: 2020/10/10 10:13
 * @version: 1.0
 * Description: 答案控制类
 */

@RestController
@RequestMapping("/answer")
@Api(tags = "答案接口", value = "答案管理接口")
public class AnswerController {

    @Resource
    private AnswerService answerService;

    /**
     * 添加答案
     * @param answerDto
     * @return
     */
    @ApiOperation(value = "添加答案", notes = "添加答案")
    @PostMapping("/save")
    public Result<Object> save(@RequestBody AnswerDto answerDto) {
        answerDto.setAnswerId(new IdWorker().nextId());
        answerDto.setOperationUserId(SecurityUtil.getUserId());
        answerService.save(answerDto);
        return new Result<>("操作提示: 添加成功!");
    }

    /**
     * 删除答案
     * @param answerIds
     * @return
     */
    @ApiOperation(value = "删除答案", notes = "删除答案")
    @DeleteMapping("/deleteByIds/{answerIds}")
    public Result<Object> deleteByIds(@PathVariable Long[] answerIds) {
        answerService.deleteByIds(answerIds);
        return new Result<>("操作提示: 删除成功!");
    }

    /**
     * 修改答案
     * @param answerDto
     * @return
     */
    @ApiOperation(value = "修改答案", notes = "修改答案")
    @PutMapping("/update")
    public Result<Object> update(@RequestBody AnswerDto answerDto) {
        answerDto.setVersion(answerService.getById(answerDto.getAnswerId()).getVersion());
        answerDto.setOperationUserId(SecurityUtil.getUserId());
        answerService.update(answerDto);
        return new Result<>("操作提示: 修改成功!");
    }

    /**
     * 根据id查询答案详情
     * @param answerId
     * @return
     */
    @ApiOperation(value = "查询单个答案详情", notes = "根据id查询答案详情")
    @GetMapping("/getById/{answerId}")
    public Result<AnswerVo> getById(@PathVariable Long answerId) {
        AnswerVo answerVo = answerService.getById(answerId);
        return new Result<>("操作提示: 查询成功!", answerVo);
    }

    /**
     * 分页查询答案详情
     * @param page
     * @return
     */
    @ApiOperation(value = "分页查询答案详情", notes = "分页查询答案详情")
    @PostMapping("/getByPage")
    public Result<Page<AnswerVo>> getByPage(@RequestBody Page<AnswerVo> page) {
        String sortColumn = page.getSortColumn();
        // 驼峰转下划线
        String newSortColumn = StringUtils.upperCharToUnderLine(sortColumn);
        page.setSortColumn(newSortColumn);
        if (StringUtils.isNotBlank(sortColumn)) {
            // 答案排序、创建时间、更新时间
            String[] sortColumns = {"answer_sort", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(newSortColumn.toLowerCase())) {
                return new Result<>(ResultEnums.ERROR.getCode(), "操作提示: 参数错误!");
            }
        }
        page = answerService.getByPage(page);
        return new Result<>("操作提示: 分页查询成功!", page);
    }



}
