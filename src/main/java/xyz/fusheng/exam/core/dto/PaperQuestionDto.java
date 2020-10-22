package xyz.fusheng.exam.core.dto;

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
 * @FileName: PaperQuestionDto
 * @Author: code-fusheng
 * @Date: 2020/10/19 11:18
 * @version: 1.0
 * Description: 试卷试题中间表传输对象
 */

@ApiModel(value="xyz-fusheng-exam-core-dto-PaperQuestion")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class PaperQuestionDto extends BaseDto{

    /**
     * 试卷编号
     */
    @ApiModelProperty(value="试卷编号")
    @TableId(value = "paper_id", type = IdType.INPUT)
    private Long paperId;

    /**
     * 试题编号
     */
    @ApiModelProperty(value="试题编号")
    @TableId(value = "paper_id", type = IdType.INPUT)
    private Long questionId;

    /**
     * 试题类型
     */
    @ApiModelProperty(value="试题类型")
    private Byte questionTypeId;

    /**
     * 试题排序
     */
    @ApiModelProperty(value="试题排序")
    private Integer questionSort;

    /**
     * 单题分值
     */
    @ApiModelProperty(value="单题分值")
    private Integer questionScore;

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
