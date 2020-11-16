package xyz.fusheng.exam.core.vo;

import lombok.Data;
import xyz.fusheng.exam.core.entity.QuestionReply;

import java.util.ArrayList;
import java.util.List;

/**
 * @FileName: QuestionReplyVo
 * @Author: code-fusheng
 * @Date: 2020/10/23 10:39
 * @version: 1.0
 * Description: 试题作答视图
 */

@Data
public class QuestionReplyVo extends QuestionReply {

    /**
     * 前端数据
     */
    private List<Object> userAnswerForFront = new ArrayList<>();

}
