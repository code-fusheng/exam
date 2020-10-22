package xyz.fusheng.exam.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

 /**
  * @author:   code-fusheng
  * @Date:     2020/10/19 18:44
  */
@ApiModel(value="xyz-fusheng-exam-core-entity-Exam")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("ex_exam")
public class Exam extends BaseEntity {
    /**
    * 考试编号
    */
    @ApiModelProperty(value="考试编号")
    @TableId(value = "exam_id", type = IdType.INPUT)
    private Long examId;

    /**
    * 考试名称
    */
    @ApiModelProperty(value="考试名称")
    private String examName;

    /**
    * 考试描述
    */
    @ApiModelProperty(value="考试描述")
    private String examDescription;

    /**
    * 是否公开
    */
    @ApiModelProperty(value="是否公开")
    private Integer isPublic;

    /**
    * 口令密码
    */
    @ApiModelProperty(value="口令密码")
    private String password;

    /**
    * 是否限时
    */
    @ApiModelProperty(value="是否限时")
    private Integer isLimitTime;

    /**
    * 开始时间
    */
    @ApiModelProperty(value="开始时间")
    private Date startTime;

    /**
    * 结束时间
    */
    @ApiModelProperty(value="结束时间")
    private Date endTime;

    /**
    * 考试备注
    */
    @ApiModelProperty(value="考试备注")
    private String remark;

    /**
    * 考试分析
    */
    @ApiModelProperty(value="考试分析")
    private String analysis;

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

    /**
    * 创建者
    */
    @ApiModelProperty(value="创建者")
    private Long createdBy;

    /**
    * 更新者
    */
    @ApiModelProperty(value="更新者")
    private Long updateBy;

    /**
    * 乐观锁 默认1
    */
    @ApiModelProperty(value="乐观锁 默认1")
    @Version
    private Integer version;

    /**
    * 逻辑删除位 1：已删除 0：未删除
    */
    @ApiModelProperty(value="逻辑删除位 1：已删除 0：未删除")
    @TableLogic
    private Integer isDeleted;

    /**
    * 状态位 1：启用 0：弃用
    */
    @ApiModelProperty(value="状态位 1：启用 0：弃用")
    private Integer isEnabled;
}
