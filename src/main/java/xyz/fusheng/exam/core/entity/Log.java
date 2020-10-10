package xyz.fusheng.exam.core.entity;

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
  * @Date:     2020/10/7 20:36
  * 接口访问日志表
  */

@ApiModel(value="xyz-fusheng-exam-core-entity-Log")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_log")
public class Log extends BaseEntity {
    /**
    * 日志id
    */
    @ApiModelProperty(value="日志id")
    @TableId
    private Integer logId;

    /**
    * 请求路径
    */
    @ApiModelProperty(value="请求路径")
    private String logUrl;

    /**
    * 参数
    */
    @ApiModelProperty(value="参数")
    private String logParams;

    /**
    * 访问状态，1正常0异常
    */
    @ApiModelProperty(value="访问状态，1正常0异常")
    private Integer logStatus;

    /**
    * 异常信息
    */
    @ApiModelProperty(value="异常信息")
    private String logMessage;

    /**
    * 请求方式，get、post等
    */
    @ApiModelProperty(value="请求方式，get、post等")
    private String logMethod;

    /**
    * 响应时间
    */
    @ApiModelProperty(value="响应时间")
    private Long logTime;

    /**
    * 返回值
    */
    @ApiModelProperty(value="返回值")
    private String logResult;

    /**
    * 请求ip
    */
    @ApiModelProperty(value="请求ip")
    private String logIp;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private Date createdTime;

    /**
    * 创建人
    */
    @ApiModelProperty(value="创建人")
    private String createdBy;
}
