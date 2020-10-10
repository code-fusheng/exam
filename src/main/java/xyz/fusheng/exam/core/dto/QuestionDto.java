package xyz.fusheng.exam.core.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @FileName: QuestionDto
 * @Author: code-fusheng
 * @Date: 2020/10/9 18:33
 * @version: 1.0
 * Description: 试题传输对象
 */

@ApiModel(value="xyz-fusheng-exam-core-dto-Question")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto extends BaseDto{

    /**
     * 试题编号
     */
    @ApiModelProperty(value="试题编号")
    private Long questionId;

    /**
     * 试题内容
     */
    @ApiModelProperty(value="试题内容")
    private String questionContent;

    /**
     * 试题图片
     */
    @ApiModelProperty(value="试题图片")
    private String questionImage;

    /**
     * 试题视频
     */
    @ApiModelProperty(value="试题视频")
    private String questionVideo;

    /**
     * 试题代码
     */
    @ApiModelProperty(value="试题代码")
    private String questionCode;

    /**
     * 试题类型 0：其它，1：单选题，2：多选题，3：填空题，4：简答题
     */
    @ApiModelProperty(value="试题类型 0：其它，1：单选题，2：多选题，3：填空题，4：简答题")
    private Integer questionTypeId;

    /**
     * 试题标签编号 0：其它，1：基础题，2：提升题，3：开发题
     */
    @ApiModelProperty(value="试题标签编号 0：其它，1：基础题，2：提升题，3：开发题")
    private Integer questionTagId;

    /**
     * 试题备注
     */
    @ApiModelProperty(value="试题备注")
    private String remark;

    /**
     * 试题分析
     */
    @ApiModelProperty(value="试题分析")
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
     * 试题创建者
     */
    @ApiModelProperty(value="试题创建者")
    private Long createdBy;

    /**
     * 试题更新者
     */
    @ApiModelProperty(value="试题更新者")
    private Long updateBy;

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
