package xyz.fusheng.exam.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
  * @Date:     2020/9/30 8:47
  * * 权限表
  */

@ApiModel(value="xyz-fusheng-exam-core-entity-Menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_menu")
public class Menu extends BaseEntity {
    /**
    * ID
    */
    @ApiModelProperty(value="ID")
    @TableId(value = "menu_id", type = IdType.INPUT)
    private Long menuId;

    /**
    * 权限名称
    */
    @ApiModelProperty(value="权限名称")
    private String name;

    /**
    * 权限标识
    */
    @ApiModelProperty(value="权限标识")
    private String permission;

    /**
    * 权限路由
    */
    @ApiModelProperty(value="权限路由")
    private String path;

    /**
    * 父级id
    */
    @ApiModelProperty(value="父级id")
    private Integer pid;

    /**
    * 权限级别 1 2 3
    */
    @ApiModelProperty(value="权限级别 1 2 3")
    private Byte level;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private Date createTime;

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
