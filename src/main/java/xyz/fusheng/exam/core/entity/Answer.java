package xyz.fusheng.exam.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author: code-fusheng
 * @Date: 2020/10/9 18:50
 */
@ApiModel(value = "xyz-fusheng-exam-core-entity-Answer")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("ex_answer")
public class Answer extends BaseEntity {
    /**
     * 答案编号
     */
    @ApiModelProperty(value = "答案编号")
    @TableId(value = "answer_id", type = IdType.INPUT)
    private Long answerId;

    /**
     * 答案内容
     */
    @ApiModelProperty(value = "答案内容")
    private String answerContent;

    /**
     * 答案图片
     */
    @ApiModelProperty(value = "答案图片")
    private String answerImage;

    /**
     * 答案视频
     */
    @ApiModelProperty(value = "答案视频")
    private String answerVideo;

    /**
     * 答案代码
     */
    @ApiModelProperty(value = "答案代码")
    private String answerCode;

    /**
     * 答案排序
     */
    @ApiModelProperty(value = "答案排序")
    private Integer answerSort;

    /**
     * 是否正确（0：正确，1：错误）
     */
    @ApiModelProperty(value = "是否正确（1：正确，0：错误）")
    private Integer isRight;

    /**
     * 答案分析
     */
    @ApiModelProperty(value = "答案分析")
    private String analysis;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 试题编号
     */
    @ApiModelProperty(value = "试题编号")
    private Long questionId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 答案创建者
     */
    @ApiModelProperty(value = "答案创建者")
    private Long createdBy;

    /**
     * 答案更新者
     */
    @ApiModelProperty(value = "答案更新者")
    private Long updateBy;

    /**
     * 乐观锁 默认1
     */
    @ApiModelProperty(value = "乐观锁 默认1")
    @Version
    private Integer version;

    /**
     * 逻辑删除位 1：已删除 0：未删除
     */
    @ApiModelProperty(value = "逻辑删除位 1：已删除 0：未删除")
    @TableLogic
    private Integer isDeleted;

    /**
     * 状态位 1：启用 0：弃用
     */
    @ApiModelProperty(value = "状态位 1：启用 0：弃用")
    private Integer isEnabled;
}
