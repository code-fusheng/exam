package xyz.fusheng.exam.core.dto; /**
 * @author: code-fusheng
 * @Date: 2020/10/14 12:33
 */

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
 * @FileName: PaperRuleDto
 * @Author: code-fusheng
 * @Date: 2020/10/14 12:33
 * @version: 1.0
 * Description: 试卷规则传输对象
 */

@ApiModel(value="xyz-fusheng-exam-core-dto-PaperRule")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class PaperRuleDto extends BaseDto {

    /**
     * 试卷规则编码
     */
    @ApiModelProperty(value="试卷规则编码")
    private Long paperRuleId;

    /**
     * 试卷规则名称
     */
    @ApiModelProperty(value="试卷规则名称")
    private String paperRuleName;

    /**
     * 总分 (默认：100)
     */
    @ApiModelProperty(value="总分 (默认：100)")
    private Integer totalScore;

    /**
     * 时长（默认：120min）
     */
    @ApiModelProperty(value="时长（默认：120min）")
    private Integer totalTime;

    /**
     * 合格分（默认：60%）
     */
    @ApiModelProperty(value="合格分（默认：60%）")
    private Integer eligibilityScore;

    /**
     * 题库编号
     */
    @ApiModelProperty(value = "题库编号")
    private Long repositoryId;

    /**
     * 单选题数
     */
    @ApiModelProperty(value="单选题数")
    private Integer singleCount;

    /**
     * 单选题分数
     */
    @ApiModelProperty(value="单选题分数")
    private Integer singleScore;

    /**
     * 多选题数
     */
    @ApiModelProperty(value="多选题数")
    private Integer multipleCount;

    /**
     * 多选题分数
     */
    @ApiModelProperty(value="多选题分数")
    private Integer multipleScore;

    /**
     * 填空题数
     */
    @ApiModelProperty(value="填空题数")
    private Integer fillCount;

    /**
     * 填空题分数
     */
    @ApiModelProperty(value="填空题分数")
    private Integer fillScore;

    /**
     * 判断题数
     */
    @ApiModelProperty(value="判断题数")
    private Integer judgeCount;

    /**
     * 判断题分数
     */
    @ApiModelProperty(value="判断题分数")
    private Integer judgeScore;

    /**
     * 简答题数
     */
    @ApiModelProperty(value="简答题数")
    private Integer shortCount;

    /**
     * 简答题分数
     */
    @ApiModelProperty(value="简答题分数")
    private Integer shortScore;

    /**
     * 规则备注
     */
    @ApiModelProperty(value="规则备注")
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
