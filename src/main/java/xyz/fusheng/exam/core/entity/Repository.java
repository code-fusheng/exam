package xyz.fusheng.exam.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 /**
  * @author:   code-fusheng
  * @Date:     2020/10/7 23:09
  */
@ApiModel(value="xyz-fusheng-exam-core-entity-Repository")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ex_repository")
public class Repository extends BaseEntity {
    /**
    * 题库编号
    */
    @ApiModelProperty(value="题库编号")
    @TableId(value = "repository_id", type = IdType.INPUT)
    private Long repositoryId;

    /**
    * 题库名称
    */
    @ApiModelProperty(value="题库名称")
    private String repositoryName;

     /**
      * 题目总数
      */
     @ApiModelProperty(value = "题目总数")
     private Integer questionCount;

    /**
    * 题库备注
    */
    @ApiModelProperty(value="题库备注")
    private String remark;

    /**
    * 题库创建者
    */
    @ApiModelProperty(value="题库创建者")
    private Long createdBy;

     /**
      * 题库更新者
      */
     @ApiModelProperty(value="题库更新者")
     private Long updateBy;

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
    private Boolean isDeleted;

    /**
    * 状态位 1：启用 0：弃用
    */
    @ApiModelProperty(value="状态位 1：启用 0：弃用")
    private Boolean isEnabled;
}
