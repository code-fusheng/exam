package xyz.fusheng.exam.core.dto;

/**
 * @author: code-fusheng
 * @Date: 2020/10/7 23:15
 */

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @FileName: RepositoryDto
 * @Author: code-fusheng
 * @Date: 2020/10/7 23:15
 * @version: 1.0
 * Description: 题库传输对象
 */

@ApiModel(value="xyz-fusheng-exam-core-entity-Repository")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepositoryDto extends BaseDto {

    /**
     * 题库编号
     */
    @ApiModelProperty(value="题库编号")
    // @JsonSerialize(using= ToStringSerializer.class)
    private Long repositoryId;

    /**
     * 题库名称
     */
    @ApiModelProperty(value="题库名称")
    private String repositoryName;

    /**
     * 题库备注
     */
    @ApiModelProperty(value="题库备注")
    private String remark;

    /**
     * 题库创建者
     */
    @ApiModelProperty(value="题库创建者")
    private String createdBy;

    /**
     * 题库更新者
     */
    @ApiModelProperty(value="题库更新者")
    private String updateBy;

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
    private Integer isDeleted;

    /**
     * 状态位 1：启用 0：弃用
     */
    @ApiModelProperty(value="状态位 1：启用 0：弃用")
    private Integer isEnabled;

}
