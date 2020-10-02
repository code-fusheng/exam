package xyz.fusheng.exam.core.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author:   code-fusheng
 * @Date:     2020/9/30 8:40
 * 系统用户表
 */
@ApiModel(value="xyz-fusheng-exam-core-entity-User")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class UserDto {
   /**
   * 用户ID
   */
   @ApiModelProperty(value="用户ID")
   private Long userId;

   /**
   * 用户名
   */
   @NotBlank(message = "用户名不能为空!")
   @ApiModelProperty(value="用户名")
   private String username;

   /**
   * 密码
   */
   @ApiModelProperty(value="密码")
   private String password;

   /**
   * 头像
   */
   @ApiModelProperty(value="头像")
   private String header;

   /**
   * 电话号码
   */
   @NotBlank(message = "用户电话不能为空!")
   @ApiModelProperty(value="电话号码")
   private String phone;

   /**
   * 邮箱
   */
   @ApiModelProperty(value="邮箱")
   private String mail;

   /**
   * 签名
   */
   @ApiModelProperty(value="签名")
   private String signature;

   /**
   * 描述
   */
   @ApiModelProperty(value="描述")
   private String description;

   /**
   * 真实姓名
   */
   @ApiModelProperty(value="真实姓名")
   private String realname;

   /**
   * 性别 0:私密 1:男 2:女
   */
   @ApiModelProperty(value="性别 0:私密 1:男 2:女")
   private Boolean sex;

   /**
   * 地址
   */
   @ApiModelProperty(value="地址")
   private String address;

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
