package xyz.fusheng.exam.core.vo; /**
 * @author: code-fusheng
 * @Date: 2020/10/9 19:38
 */

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

}
