package xyz.fusheng.exam.core.vo; /**
 * @author: code-fusheng
 * @Date: 2020/10/10 14:27
 */

import lombok.Data;
import xyz.fusheng.exam.core.entity.Answer;

/**
 * @FileName: AnswerVo
 * @Author: code-fusheng
 * @Date: 2020/10/10 14:27
 * @version: 1.0
 * Description: 答案视图对相爱那个
 */

@Data
public class AnswerVo extends Answer {

    /**
     * 创建者用户名
     */
    private String createdUserName;

    /**
     * 更新者用户名
     */
    private String updateUserName;

    /**
     * 是否选中
     */
    private Integer isSelect;

}
