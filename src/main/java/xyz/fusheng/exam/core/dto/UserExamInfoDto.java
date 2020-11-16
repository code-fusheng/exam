package xyz.fusheng.exam.core.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @FileName: UserExamInfoDto
 * @Author: code-fusheng
 * @Date: 2020/10/29 21:30
 * @version: 1.0
 * Description: 用户考试信息记录表
 */

@ApiModel(value="xyz-fusheng-exam-core-dto-UserExamInfo")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class UserExamInfoDto extends BaseDto {

    /**
     * 用户考试编号
     */
    @ApiModelProperty(value="用户考试编号")
    private Long id;

    /**
     * 用户编号
     */
    @ApiModelProperty(value="用户编号")
    private Long userId;

    /**
     * 考试编号
     */
    @ApiModelProperty(value="考试编号")
    private Long examId;

    /**
     * 试卷编号
     */
    @ApiModelProperty(value="试卷编号")
    private Long paperId;

    /**
     * 用户得分
     */
    @ApiModelProperty(value="用户得分")
    private Integer userScore;

    /**
     * 是否阅卷 0：未阅卷；1：已经阅卷
     */
    @ApiModelProperty(value="是否阅卷 0：未阅卷；1：已经阅卷")
    private Integer isMark;

    /**
     * 是否交卷 0：未交卷；1：已经交卷
     */
    @ApiModelProperty(value="是否交卷 0：未交卷；1：已经交卷")
    private Integer isSubmit;

    /**
     * 阅卷评语
     */
    @ApiModelProperty(value="阅卷评语")
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
     * 创建者
     */
    @ApiModelProperty(value="创建者")
    private Long createdBy;

    /**
     * 更新者
     */
    @ApiModelProperty(value="更新者")
    private Long updateBy;

}
