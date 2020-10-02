package xyz.fusheng.exam.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 /**
  * @author:   code-fusheng
  * @Date:     2020/9/30 8:44
  * 角色表
  */

@ApiModel(value="xyz-fusheng-exam-core-entity-Role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_role")
public class Role {
    /**
    * 角色ID
    */
    @ApiModelProperty(value="角色ID")
    @TableId
    private Long roleId;

    /**
    * 角色名称
    */
    @ApiModelProperty(value="角色名称")
    private String roleName;

    /**
    * 角色备注
    */
    @ApiModelProperty(value="角色备注")
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

    /**
    * 乐观锁 默认1
    */
    @ApiModelProperty(value="乐观锁 默认1")
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
