package xyz.fusheng.exam.core.dto; /**
 * @author: code-fusheng
 * @Date: 2020/10/9 8:39
 */

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.fusheng.exam.core.dto.BaseDto;

import java.util.Date;

/**
 * @FileName: DictTypeDto
 * @Author: code-fusheng
 * @Date: 2020/10/9 8:39
 * @version: 1.0
 * Description:
 */

@ApiModel(value="xyz-fusheng-exam-core-dto-DictType")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class DictTypeDto extends BaseDto {

    /**
     * 字典编号
     */
    @ApiModelProperty(value="字典编号")
    private Long dictId;

    /**
     * 字典名称
     */
    @ApiModelProperty(value="字典名称")
    private String dictName;

    /**
     * 字典类型
     */
    @ApiModelProperty(value="字典类型")
    private String dictType;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
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
