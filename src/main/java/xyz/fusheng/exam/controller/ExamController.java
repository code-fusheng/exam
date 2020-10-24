package xyz.fusheng.exam.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.exam.common.enums.ResultEnums;
import xyz.fusheng.exam.common.utils.*;
import xyz.fusheng.exam.core.dto.ExamDto;
import xyz.fusheng.exam.core.entity.Exam;
import xyz.fusheng.exam.core.service.ExamService;
import xyz.fusheng.exam.core.service.QuestionReplyService;
import xyz.fusheng.exam.core.vo.ExamVo;
import xyz.fusheng.exam.core.vo.PaperVo;
import xyz.fusheng.exam.core.vo.QuestionReplyVo;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName: ExamController
 * @Author: code-fusheng
 * @Date: 2020/10/19 18:54
 * @version: 1.0
 * Description: 考试控制类
 */

@RestController
@RequestMapping("/exam")
@Api(tags = "考试管理", value = "考试管理接口")
public class ExamController {

    @Resource
    private ExamService examService;

    /**
     * 添加考试
     * @param examDto
     * @return
     */
    @ApiOperation(value = "添加考试", notes = "添加考试")
    @PostMapping("/saveExam")
    public Result<Object> saveExam(@RequestBody ExamDto examDto) {
        examDto.setExamId(new IdWorker().nextId());
        examDto.setOperationUserId(SecurityUtil.getUserId());
        examService.saveExam(examDto);
        return new Result<>("操作提示: 添加考试成功!");
    }

    /**
     * 删除考试
     * @param examIds
     * @return
     */
    @DeleteMapping("/deleteExamByIds")
    public Result<Object> deleteExamByIds(@RequestBody Long[] examIds) {
        examService.deleteExamByIds(examIds);
        return new Result<>("操作提示: 删除成功!");
    }

    /**
     * 修改考试
     * @param examDto
     * @return
     */
    @PutMapping("/updateExam")
    public Result<Object> updateExam(@RequestBody ExamDto examDto) {
        examDto.setVersion(examService.getExamVoById(examDto.getExamId()).getVersion());
        examDto.setOperationUserId(SecurityUtil.getUserId());
        examService.updateExam(examDto);
        return new Result<>("操作成功: 修改考试信息!");
    }

    /**
     * 查询考试视图信息
     * @param examId
     * @return
     */
    @GetMapping("/getExamVoById/{examId}")
    public Result<ExamVo> getExamVoById(@PathVariable Long examId) {
        ExamVo examVo = examService.getExamVoById(examId);
        return new Result<>("操作成功: 查询考试视图详情!", examVo);
    }

    /**
     * 自定义考试视图分页
     * @param page
     * @return
     */
    @PostMapping("/getExamVoByPage")
    public Result<Page<ExamVo>> getExamVoByPage(@RequestBody Page<ExamVo> page) {
        String sortColumn = page.getSortColumn();
        // 驼峰转下划线
        String newSortColumn = StringUtils.upperCharToUnderLine(sortColumn);
        page.setSortColumn(newSortColumn);
        if (StringUtils.isNotBlank(sortColumn)) {
            // 考试名、创建时间、更新时间
            String[] sortColumns = {"exam_name", "start_time", "end_time", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(newSortColumn.toLowerCase())) {
                return new Result<>(ResultEnums.ERROR.getCode(), "操作提示: 参数错误!");
            }
        }
        page = examService.getExamVoByPage(page);
        return new Result<>("操作成功: 分页查询考试视图详情!", page);
    }

    /**
     * 获取考试试卷集合根据考试编号
     * @param examId
     * @return
     */
    @GetMapping("/getPaperVoListByExamId/{examId}")
    public Result<List<PaperVo>> getPaperVoListByExamId(@PathVariable Long examId) {
        List<PaperVo> paperVoList = examService.getPaperVoListByExamId(examId);
        return new Result<>("操作提示: 获取考试试卷集合!", paperVoList);
    }

    /**
     * 添加作答信息（交卷）
     * @param questionReplyVoList
     * @return
     */
    @PostMapping("/saveQuestionReply")
    public Result<Object> saveQuestionReply(@RequestBody List<QuestionReplyVo> questionReplyVoList) {
        examService.saveQuestionReplyList(questionReplyVoList);
        return new Result<>("操作提示: 添加成功!");
    }
}
