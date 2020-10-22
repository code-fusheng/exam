package xyz.fusheng.exam.core.vo; /**
 * @author: code-fusheng
 * @Date: 2020/10/14 14:15
 */

import lombok.Data;
import xyz.fusheng.exam.core.entity.PaperRule;

/**
 * @FileName: PaperRuleVo
 * @Author: code-fusheng
 * @Date: 2020/10/14 14:15
 * @version: 1.0
 * Description:
 */

@Data
public class PaperRuleVo extends PaperRule {

    /**
     * 创建者用户名
     */
    private String createdUserName;

    /**
     * 更新者用户名
     */
    private String updateUserName;

}
