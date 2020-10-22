package xyz.fusheng.exam.core.dto; /**
 * @author: code-fusheng
 * @Date: 2020/10/20 14:22
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @FileName: ExamPaperDto
 * @Author: code-fusheng
 * @Date: 2020/10/20 14:22
 * @version: 1.0
 * Description: 考试试卷关系传输对象
 */

@ApiModel(value="xyz-fusheng-exam-core-dto-ExamPaper")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class ExamPaperDto extends BaseDto {

    /**
     * 考试编号
     */
    @ApiModelProperty(value="考试编号")
    private Long examId;

    /**
     * 试卷编号
     */
    @ApiModelProperty(value="试卷编号")
    private Long paperId;

    /**
     * 优先级 1：最低，10：最高
     */
    @ApiModelProperty(value="优先级 1：最低，10：最高")
    private Boolean priorityLevel;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remark;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createdTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间")
    private Date updateTime;

}
