package xyz.fusheng.exam.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.exam.common.enums.ResultEnums;
import xyz.fusheng.exam.common.utils.*;
import xyz.fusheng.exam.core.dto.QuestionDto;
import xyz.fusheng.exam.core.entity.Question;
import xyz.fusheng.exam.core.service.QuestionService;
import xyz.fusheng.exam.core.vo.QuestionVo;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName: QuestionController
 * @Author: code-fusheng
 * @Date: 2020/10/9 19:40
 * @version: 1.0
 * Description: 试题控制类
 */

@RestController
@RequestMapping("/question")
@Api(tags = "试题接口", value = "试题管理接口")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    /**
     * 添加试题
     * @param questionDto
     * @return
     */
    @ApiOperation(value = "添加试题", notes = "添加试题")
    @PostMapping("/save")
    public Result<Object> save(@RequestBody QuestionDto questionDto) {
        // 雪花算法生成ID编号
        questionDto.setQuestionId(new IdWorker().nextId());
        questionDto.setOperationUserId(SecurityUtil.getUserId());
        questionService.save(questionDto);
        return new Result<>("操作提示：添加成功!");
    }

    /**
     * 删除试题
     * @param questionIds
     * @return
     */
    @ApiOperation(value = "删除试题", notes = "删除试题")
    @DeleteMapping("/deleteByIds")
    public Result<Object> deleteByIds(@RequestBody Long[] questionIds) {
        // 查询需要删除的试卷是否有被其他试卷采用
        boolean judgeResult =  questionService.judgeExistQuestionUsedByPaper(questionIds);
        if (judgeResult == true) {
            return new Result<>(ResultEnums.ERROR.getCode(), "操作提示: 批量删除试题目标存在引用!");
        }
        questionService.deleteByIds(questionIds);
        return new Result<>("操作提示: 删除成功!");
    }

    /**
     * 修改试题
     * @param questionDto
     * @return
     */
    @ApiOperation(value = "修改试题", notes = "修改试题")
    @PutMapping("/update")
    public Result<Object> update(@RequestBody QuestionDto questionDto) {
        questionDto.setVersion(questionService.getById(questionDto.getQuestionId()).getVersion());
        questionDto.setOperationUserId(SecurityUtil.getUserId());
        questionService.update(questionDto);
        return new Result<>("操作提示: 修改成功!");
    }

    /**
     * 根据id查询试题详情
     * @param questionId
     * @return
     */
    @ApiOperation(value = "查询单个试题详情", notes = "根据id查询试题详情")
    @GetMapping("/getById/{questionId}")
    public Result<QuestionVo> getById(@PathVariable Long questionId) {
        QuestionVo questionVo = questionService.getById(questionId);
        return new Result<>("操作提示: 查询成功!", questionVo);
    }

    /**
     * 自定义分页查询试题视图
     * @param page
     * @return
     */
    @ApiOperation(value = "分页查询试题详情", notes = "分页查询试题详情")
    @PostMapping("/getByPage")
    public Result<Page<QuestionVo>> getByPage(@RequestBody Page<QuestionVo> page) {
        String sortColumn = page.getSortColumn();
        // 驼峰转下划线
        String newSortColumn = StringUtils.upperCharToUnderLine(sortColumn);
        page.setSortColumn(newSortColumn);
        if (StringUtils.isNotBlank(sortColumn)) {
            // 题目名、题目总数、创建时间、更新时间
            String[] sortColumns = {"question_type_id", "question_tag_id", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(newSortColumn.toLowerCase())) {
                return new Result<>(ResultEnums.ERROR.getCode(), "操作提示: 参数错误!");
            }
        }
        page = questionService.getByPage(page);
        return new Result<>("操作提示: 分页查询成功!", page);
    }

    /**
     * 根据id获取试题以及答案详细信息(answerContent&isRight) - 后台
     * @param questionId
     * @return
     */
    @ApiOperation(value = "查询试题与答案详情")
    @GetMapping("/getQuestionVoWithAnswers/{questionId}")
    public Result<QuestionVo> getQuestionVoWithAnswersById(@PathVariable Long questionId) {
        QuestionVo questionVo = questionService.getQuestionVoWithAnswers(questionId);
        return new Result<>("操作提示: 查询成功!", questionVo);
    }

    /**
     * 根据id获取试题以及选项信息（answerContent） - 前台
     * @param questionId
     * @return
     */
    @ApiOperation(value = "查询试题与选项信息")
    @GetMapping("/getQuestionVoWithAnswersForFront/{questionId}")
    public Result<QuestionVo> getQuestionVoWithAnswersForFrontById(@PathVariable Long questionId) {
        QuestionVo questionVo = questionService.getQuestionVoWithAnswersForFrontById(questionId);
        return new Result<>("操作提示: 查询成功!", questionVo);
    }

    /**
     * 分页查询考试试卷试题与选项列表（不含答案）
     * @param page
     * @return
     */
    @PostMapping("/getQuestionVoListByPageForFront")
    public Result<Page<QuestionVo>> getQuestionVoListByPageForFront(@RequestBody Page<QuestionVo> page) {
        page = questionService.getQuestionVoListByPageForFront(page);
        return new Result<>("操作提示: 查询成功!", page);
    }

    /**
     * 分页查询题库试题列表
     * @param page
     * @return
     */
    @PostMapping("/getQuestionListByPage")
    public Result<Page<Question>> getQuestionListByPage(@RequestBody Page<Question> page) {
        String sortColumn = page.getSortColumn();
        // 驼峰转下划线
        String newSortColumn = StringUtils.upperCharToUnderLine(sortColumn);
        page.setSortColumn(newSortColumn);
        if (StringUtils.isNotBlank(sortColumn)) {
            // 题目名、题目总数、创建时间、更新时间
            String[] sortColumns = {"question_type_id", "question_tag_id", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(newSortColumn.toLowerCase())) {
                return new Result<>(ResultEnums.ERROR.getCode(), "操作提示: 参数错误!");
            }
        }
        page = questionService.getQuestionListByPage(page);
        return new Result<>("操作提示: 分页查询题库试题列表!", page);
    }

    /**
     * 后台查询试卷试题以及答案列表
     * @param paperId
     * @return
     */
    @GetMapping("/getQuestionVoListByPaperIdForPaperInfo/{paperId}")
    public Result<List<QuestionVo>> getQuestionVoListByPaperIdForPaperInfo(@PathVariable Long paperId) {
        List<QuestionVo> questionVoList = questionService.getQuestionVoListByPaperIdForPaperInfo(paperId);
        return new Result<>("操作提示: 查询试卷试题列表!", questionVoList);
    }

}
