package xyz.fusheng.exam.core.vo; /**
 * @author: code-fusheng
 * @Date: 2020/10/9 19:38
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.fusheng.exam.core.entity.Answer;
import xyz.fusheng.exam.core.entity.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * @FileName: QuestionVo
 * @Author: code-fusheng
 * @Date: 2020/10/9 19:38
 * @version: 1.0
 * Description: 试题视图对象
 */

@Data
public class QuestionVo extends Question {

    /**
     * 创建者用户名
     */
    private String createdUserName;

    /**
     * 更新者用户名
     */
    private String updateUserName;

    /**
     * 答案列表
     */
    private List<Answer> answerList = new ArrayList<>();

    /**
     * 试题在试卷中的排序
     */
    private Integer questionSort;

    /**
     * 题库编号
     */
    private Long repositoryId;

    /**
     * 题库名称
     */
    private String repositoryName;

    /**
     * 试卷编号
     */
    private Long paperId;

    /**
     * 试题分数
     */
    private Integer questionScore;

    /**
     * 用户答案
     */
    private List<Object> userAnswer = new ArrayList<>();

}
