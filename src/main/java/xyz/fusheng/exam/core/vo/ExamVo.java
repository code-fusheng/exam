package xyz.fusheng.exam.core.vo;

import lombok.Data;
import xyz.fusheng.exam.core.entity.Exam;
import xyz.fusheng.exam.core.entity.ExamPaper;
import xyz.fusheng.exam.core.entity.Paper;

import java.util.ArrayList;
import java.util.List;

/**
 * @FileName: ExamVo
 * @Author: code-fusheng
 * @Date: 2020/10/19 18:55
 * @version: 1.0
 * Description:
 */

@Data
public class ExamVo extends Exam {

    /**
     * 创建者用户名
     */
    private String createdUserName;

    /**
     * 更新者用户名
     */
    private String updateUserName;

    /**
     * 考试的试卷集合
     */
    private List<PaperVo> paperVoList = new ArrayList<>();

    /**
     * 当前考试的试卷编号
     */
    private Long currentPaperId;

}
