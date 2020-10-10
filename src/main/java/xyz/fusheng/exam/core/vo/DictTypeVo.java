package xyz.fusheng.exam.core.vo; /**
 * @author: code-fusheng
 * @Date: 2020/10/9 9:28
 */

import lombok.Data;
import xyz.fusheng.exam.core.entity.DictType;

/**
 * @FileName: DictTypeVo
 * @Author: code-fusheng
 * @Date: 2020/10/9 9:28
 * @version: 1.0
 * Description: 字典类型视图对象
 */

@Data
public class DictTypeVo extends DictType {

    /**
     * 创建者用户名
     */
    private String createdUserName;

    /**
     * 更新者用户名
     */
    private String updateUserName;

}
