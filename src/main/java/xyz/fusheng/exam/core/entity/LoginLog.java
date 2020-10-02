package xyz.fusheng.exam.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 /**
  * @author:   code-fusheng
  * @Date:     2020/10/2 0:13
  */

@ApiModel(value="xyz-fusheng-exam-core-entity-LoginLog")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginLog {
    /**
    * 登录日志编号
    */
    @ApiModelProperty(value="登录日志编号")
    private Long loginLogId;

    /**
    * 登录账号
    */
    @ApiModelProperty(value="登录账号")
    private String userName;

    /**
    * 登录IP地址
    */
    @ApiModelProperty(value="登录IP地址")
    private String ipAddress;

    /**
    * 登录地点
    */
    @ApiModelProperty(value="登录地点")
    private String loginLocation;

    /**
    * 浏览器类型
    */
    @ApiModelProperty(value="浏览器类型")
    private String browserType;

    /**
    * 操作系统类型
    */
    @ApiModelProperty(value="操作系统类型")
    private String osType;

    /**
    * 登录状态 （0 成功；1 失败 默认 0）
    */
    @ApiModelProperty(value="登录状态 （0 成功；1 失败 默认 0）")
    private Boolean loginStatus;

    /**
    * 用户类型 （0 管理员；1 普通用户 默认 1）
    */
    @ApiModelProperty(value="用户类型 （0 管理员；1 普通用户 默认 1）")
    private Boolean loginType;

    /**
    * 消息提示
    */
    @ApiModelProperty(value="消息提示")
    private String msg;

    /**
    * 登录时间
    */
    @ApiModelProperty(value="登录时间")
    private Date loginTime;
}
