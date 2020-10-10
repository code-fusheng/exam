package xyz.fusheng.exam.core.vo; /**
 * @author: code-fusheng
 * @Date: 2020/10/9 13:39
 */

import lombok.Data;
import xyz.fusheng.exam.core.entity.DictData;

/**
 * @FileName: DictDataVo
 * @Author: code-fusheng
 * @Date: 2020/10/9 13:39
 * @version: 1.0
 * Description: 字典数据视图对象
 */

@Data
public class DictDataVo extends DictData {

    /**
     * 创建者用户名
     */
    private String createdUserName;

    /**
     * 更新者用户名
     */
    private String updateUserName;

}
