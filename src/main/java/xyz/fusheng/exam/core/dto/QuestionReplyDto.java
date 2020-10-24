package xyz.fusheng.exam.core.dto; /**
 * @author: code-fusheng
 * @Date: 2020/10/23 10:37
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @FileName: QuestionReplyDto
 * @Author: code-fusheng
 * @Date: 2020/10/23 10:37
 * @version: 1.0
 * Description: 作答传输对象
 */

@ApiModel(value="xyz-fusheng-exam-core-dto-QuestionReply")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class QuestionReplyDto extends BaseDto {

    /**
     * 作答编号
     */
    @ApiModelProperty(value="作答编号")
    private Long replyId;

    /**
     * 试题编号
     */
    @ApiModelProperty(value="试题编号")
    private Long questionId;

    /**
     * 试卷编号
     */
    @ApiModelProperty(value="试卷编号")
    private Long paperId;

    /**
     * 考试编号
     */
    @ApiModelProperty(value="考试编号")
    private Long examId;

    /**
     * 用户编号
     */
    @ApiModelProperty(value="用户编号")
    private Long userId;

    /**
     * 试题类型
     */
    @ApiModelProperty(value="试题类型")
    private Integer questionTypeId;

    /**
     * 用户答案（Object 集合）
     */
    @ApiModelProperty(value="用户答案（Object 集合）")
    private String userAnswer;

    /**
     * 试题分值
     */
    @ApiModelProperty(value="试题分值")
    private Integer questionScore;

    /**
     * 实际得分
     */
    @ApiModelProperty(value="实际得分")
    private Integer actualScore;

    /**
     * 是否正确（1：正确，0：错误，2：多选，3：少选）
     */
    @ApiModelProperty(value="是否正确（1：正确，0：错误，2：多选，3：少选）")
    private Integer isRight;

    /**
     * 用时
     */
    @ApiModelProperty(value="用时")
    private Integer useTime;

}
