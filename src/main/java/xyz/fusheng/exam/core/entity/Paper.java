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
  * @Date:     2020/10/15 15:02
  */
@ApiModel(value="xyz-fusheng-exam-core-entity-Paper")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("ex_paper")
public class Paper extends BaseEntity {
    /**
    * 试卷编号
    */
    @ApiModelProperty(value="试卷编号")
    @TableId(value = "paper_id", type = IdType.INPUT)
    private Long paperId;

    /**
    * 试卷名称
    */
    @ApiModelProperty(value="试卷名称")
    private String paperName;

    /**
    * 试卷规则编号
    */
    @ApiModelProperty(value="试卷规则编号")
    private Long paperRuleId;

    /**
    * 试卷备注
    */
    @ApiModelProperty(value="试卷备注")
    private String remark;

    /**
    * 试卷分析
    */
    @ApiModelProperty(value="试卷分析")
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
