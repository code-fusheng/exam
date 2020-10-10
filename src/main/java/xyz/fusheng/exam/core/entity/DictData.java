package xyz.fusheng.exam.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

 /**
  * @author:   code-fusheng
  * @Date:     2020/10/9 13:08
  */
@ApiModel(value="xyz-fusheng-exam-core-entity-DictData")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_dict_data")
public class DictData extends BaseEntity {
    /**
    * 字典编码
    */
    @ApiModelProperty(value="字典编码")
    @TableId
    private Long dictCode;

    /**
    * 字典排序
    */
    @ApiModelProperty(value="字典排序")
    private Integer dictSort;

    /**
    * 字典标签
    */
    @ApiModelProperty(value="字典标签")
    private String dictLabel;

    /**
    * 字典键值
    */
    @ApiModelProperty(value="字典键值")
    private String dictValue;

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
    @Version
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
