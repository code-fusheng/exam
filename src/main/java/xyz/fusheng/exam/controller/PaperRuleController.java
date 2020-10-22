package xyz.fusheng.exam.controller; /**
 * @author: code-fusheng
 * @Date: 2020/10/14 12:36
 */

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.exam.common.enums.ResultEnums;
import xyz.fusheng.exam.common.utils.*;
import xyz.fusheng.exam.core.dto.PaperRuleDto;
import xyz.fusheng.exam.core.entity.PaperRule;
import xyz.fusheng.exam.core.service.PaperRuleService;
import xyz.fusheng.exam.core.vo.PaperRuleVo;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName: PaperRuleController
 * @Author: code-fusheng
 * @Date: 2020/10/14 12:36
 * @version: 1.0
 * Description: 试卷规则控制类
 */

@RestController
@RequestMapping("/paper/rule")
@Api(tags = "试卷规则管理", value = "试卷规则管理接口")
public class PaperRuleController {

    @Resource
    private PaperRuleService paperRuleService;

    /**
     * 添加试卷规则
     * @param paperRuleDto
     * @return
     */
    @ApiOperation(value = "添加试卷规则", notes = "添加试卷规则")
    @PostMapping("/save")
    public Result<Object> save(@RequestBody PaperRuleDto paperRuleDto) {
        paperRuleDto.setPaperRuleId(new IdWorker().nextId());
        paperRuleDto.setOperationUserId(SecurityUtil.getUserId());
        paperRuleService.save(paperRuleDto);
        return new Result<>("操作提示: 添加成功!");
    }

    /**
     * 删除试卷规则
     * @param paperRuleIds
     * @return
     */
    @ApiOperation(value = "删除试题", notes = "删除试题")
    @DeleteMapping("/deleteByIds")
    public Result<Object> deleteByIds(@RequestBody Long[] paperRuleIds) {
        paperRuleService.deleteByIds(paperRuleIds);
        return new Result<>("操作提示: 删除成功!");
    }

    /**
     * 修改试卷规则
     * @param paperRuleDto
     * @return
     */
    @PutMapping("/update")
    public Result<Object> update(@RequestBody PaperRuleDto paperRuleDto) {
        paperRuleDto.setVersion(paperRuleService.getById(paperRuleDto.getPaperRuleId()).getVersion());
        paperRuleDto.setOperationUserId(SecurityUtil.getUserId());
        paperRuleService.update(paperRuleDto);
        return new Result<>("操作提示: 修改成功!");
    }

    /**
     * 根据id查询试卷规则详情
     * @param paperRuleId
     * @return
     */
    @GetMapping("/getById/{paperRuleId}")
    public Result<PaperRuleVo> getById(@PathVariable Long paperRuleId) {
        PaperRuleVo paperRuleVo = paperRuleService.getById(paperRuleId);
        return new Result<>("操作提示: 查询成功!", paperRuleVo);
    }


    /**
     * 分页查询试卷规则详情
     * @param page
     * @return
     */
    @PostMapping("/getByPage")
    public Result<Page<PaperRuleVo>> getByPage(@RequestBody Page<PaperRuleVo> page) {
        String sortColumn = page.getSortColumn();
        // 驼峰转下划线
        String newSortColumn = StringUtils.upperCharToUnderLine(sortColumn);
        page.setSortColumn(newSortColumn);
        if (StringUtils.isNotBlank(sortColumn)) {
            // 总分、时长、合格分、单选题数/分数、多选题数/分数、填空题数/分数、判断题数/分数、简答题数/分数
            String[] sortColumns = {"total_score", "total_time", "eligibility_score", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(newSortColumn.toLowerCase())) {
                return new Result<>(ResultEnums.ERROR.getCode(), "操作提示: 参数错误!");
            }
        }
        page = paperRuleService.getByPage(page);
        return new Result<>("操作提示: 分页查询成功!", page);
    }

    /**
     * 获取试卷规则列表
     * @return
     */
    @GetMapping("/getList")
    public Result<List<PaperRule>> getList() {
        // 获取试卷规则列表（规则名与编号）
        List<PaperRule> paperRuleList = paperRuleService.getSimpleRuleList();
        return new Result<>("操作提示: 查询试卷规则列表!", paperRuleList);
    }

}
