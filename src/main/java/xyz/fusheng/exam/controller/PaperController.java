package xyz.fusheng.exam.controller; /**
 * @author: code-fusheng
 * @Date: 2020/10/15 15:28
 */

import org.springframework.web.bind.annotation.*;
import xyz.fusheng.exam.common.enums.ResultEnums;
import xyz.fusheng.exam.common.utils.*;
import xyz.fusheng.exam.core.dto.PaperDto;
import xyz.fusheng.exam.core.dto.QuestionDto;
import xyz.fusheng.exam.core.dto.QuestionIdsAndPaperIdDto;
import xyz.fusheng.exam.core.entity.Paper;
import xyz.fusheng.exam.core.service.PaperService;
import xyz.fusheng.exam.core.service.QuestionService;
import xyz.fusheng.exam.core.vo.PaperVo;
import xyz.fusheng.exam.core.vo.QuestionVo;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName: PaperController
 * @Author: code-fusheng
 * @Date: 2020/10/15 15:28
 * @version: 1.0
 * Description:
 */

@RestController
@RequestMapping("/paper")
public class PaperController {

    @Resource
    private PaperService paperService;

    @Resource
    private QuestionService questionService;

    /**
     * 添加试卷
     * @param paperDto
     * @return
     */
    @PostMapping("/save")
    public Result<Object> save(@RequestBody PaperDto paperDto) {
        paperDto.setPaperId(new IdWorker().nextId());
        paperDto.setOperationUserId(SecurityUtil.getUserId());
        paperService.save(paperDto);
        return new Result<>("操作提示: 添加试卷!");
    }

    /**
     * 添加试卷试题
     * @param questionDto
     * @return
     */
    @PostMapping("/saveQuestionVoForPaper")
    public Result<Object> saveQuestionVoForPaper(@RequestBody QuestionDto questionDto) {
        // 雪花算法生成ID编号
        questionDto.setQuestionId(new IdWorker().nextId());
        questionDto.setOperationUserId(SecurityUtil.getUserId());
        paperService.saveQuestionVoForPaper(questionDto);
        return new Result<>("操作提示: 添加试卷试题!");
    }

    /**
     * 修改试卷试题信息（包括试题在试卷中的排序与分值等信息）
     * @param questionDto
     * @return
     */
    @PutMapping("/updateQuestionVoForPaper")
    public Result<Object> updateQuestionVoForPaper(@RequestBody QuestionDto questionDto) {
        questionDto.setVersion(questionService.getById(questionDto.getQuestionId()).getVersion());
        questionDto.setOperationUserId(SecurityUtil.getUserId());
        paperService.updateQuestionVoForPaper(questionDto);
        return new Result<>("操作提示: 修改试卷试题!");
    }

    /**
     * 选择题库试题添加到试卷
     * @return
     */
    @PostMapping("/saveSelectQuestionVoForPaper")
    public Result<Object> saveSelectQuestionVoForPaper(@RequestBody QuestionIdsAndPaperIdDto questionIdsAndPaperIdDto) {
        Long paperId = questionIdsAndPaperIdDto.getPaperId();
        List<Long> questionIds = questionIdsAndPaperIdDto.getQuestionIds();
        paperService.saveSelectQuestionVoForPaper(questionIds, paperId);
        return new Result<>("操作提示: 添加试卷试题!");
    }

    /**
     * 删除试卷
     * @param paperIds
     * @return
     */
    @DeleteMapping("/deleteByIds")
    public Result<Object> deleteByIds(@RequestBody Long[] paperIds) {
        paperService.deleteByIds(paperIds);
        return new Result<>("操作提示: 删除成功!");
    }

    /**
     * 删除试卷中的试题（删除试卷、试题关系）
     * @param questionId
     * @param paperId
     * @return
     */
    @DeleteMapping("/deleteQuestionForPaperById/{questionId}/{paperId}")
    public Result<Object> deleteQuestionForPaperById(@PathVariable Long questionId, @PathVariable Long paperId) {
        paperService.deleteQuestionForPaperById(questionId, paperId);
        return new Result<>("操作提示: 删除试卷试题成功!");
    }

    /**
     * 修改试卷
     * @param paperDto
     * @return
     */
    @PutMapping("/update")
    public Result<Object> update(@RequestBody PaperDto paperDto) {
        paperDto.setVersion(paperService.getById(paperDto.getPaperId()).getVersion());
        paperDto.setOperationUserId(SecurityUtil.getUserId());
        paperService.update(paperDto);
        return new Result<>("操作提示: 修改成功!");
    }

    /**
     * 查询试卷中试题详情
     * @param questionId
     * @param paperId
     * @return
     */
    @GetMapping("/getQuestionVoForPaperByQuestionIdAndPaperId/{questionId}/{paperId}")
    public Result<QuestionVo> getQuestionVoForPaperByQuestionIdAndPaperId(@PathVariable Long questionId, @PathVariable Long paperId) {
        QuestionVo questionVo = paperService.getQuestionVoForPaperByQuestionIdAndPaperId(questionId, paperId);
        return new Result<>("操作提示: 查询试卷中试题详情", questionVo);
    }

    /**
     * 根据id查询试卷详情
     * @param paperId
     * @return
     */
    @GetMapping("/getById/{paperId}")
    public Result<PaperVo> getById(@PathVariable Long paperId) {
        PaperVo paperVo = paperService.getById(paperId);
        return new Result<>("操作提示: 查询成功!", paperVo);
    }

    /**
     * 自定义分页查询试卷
     * @param page
     * @return
     */
    @PostMapping("/getByPage")
    public Result<Page<PaperVo>> getByPage(@RequestBody Page<PaperVo> page) {
        String sortColumn = page.getSortColumn();
        // 驼峰转下划线
        String newSortColumn = StringUtils.upperCharToUnderLine(sortColumn);
        page.setSortColumn(newSortColumn);
        if (StringUtils.isNotBlank(sortColumn)) {
            // 试卷名、创建时间、更新时间
            String[] sortColumns = {"paper_name", "paper_rule_id", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(newSortColumn.toLowerCase())) {
                return new Result<>(ResultEnums.ERROR.getCode(), "操作提示: 参数错误!");
            }
        }
        page = paperService.getByPage(page);
        return new Result<>("操作提示：分页查询成功!", page);
    }

    /**
     * 后台查询试卷视图以及规则信息以及试题答案列表 (调整为不封装试题信息)
     * @return
     */
    @GetMapping("/getPaperVoWithPaperRuleVoWithQuestionVosById/{paperId}")
    public Result<PaperVo> getPaperVoWithPaperRuleVoWithQuestionVosById(@PathVariable Long paperId) {
        PaperVo paperVo = paperService.getPaperVoWithPaperRuleVoWithQuestionVosById(paperId);
        return new Result<>("操作提示: 查询成功!", paperVo);
    }

    /**
     * 获取试卷与规则信息 - 在线考试接口
     * @param paperId
     * @return
     */
    @GetMapping("/getPaperWithRuleByPaperId/{paperId}")
    public Result<PaperVo> getPaperWithRuleByPaperId(@PathVariable Long paperId) {
        PaperVo paperVo = paperService.getPaperVoWithRuleByPaperId(paperId);
        return new Result<>("操作提示: 获取试卷与规则信息!", paperVo);
    }

    /**
     * 获取试卷摘要列表
     * @return
     */
    // @GetMapping("/getPaperListForSelect")
    // public Result<List<Paper>> getPaperListForSelect() {
    //     List<Paper> paperList = paperService.getPaperListForSelect();
    //     return new Result<>("操作提示: 获取试卷摘要列表!", paperList);
    // }



}
