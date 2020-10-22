package xyz.fusheng.exam.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

 /**
  * @author:   code-fusheng
  * @Date:     2020/10/20 14:19
  */
@ApiModel(value="xyz-fusheng-exam-core-entity-ExamPaper")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("ex_exam_paper")
public class ExamPaper extends BaseEntity {
    /**
    * 考试编号
    */
    @ApiModelProperty(value="考试编号")
    @TableId(value = "exam_id", type = IdType.INPUT)
    private Long examId;

    /**
    * 试卷编号
    */
    @ApiModelProperty(value="试卷编号")
    @TableId(value = "paper_id", type = IdType.INPUT)
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
