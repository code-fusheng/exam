package xyz.fusheng.exam.core.vo; /**
 * @author: code-fusheng
 * @Date: 2020/10/15 15:18
 */

import lombok.Data;
import xyz.fusheng.exam.core.entity.Paper;

import java.util.ArrayList;
import java.util.List;

/**
 * @FileName: PaperVo
 * @Author: code-fusheng
 * @Date: 2020/10/15 15:18
 * @version: 1.0
 * Description: 试卷视图对象
 */

@Data
public class PaperVo extends Paper {

    /**
     * 创建者用户名
     */
    private String createdUserName;

    /**
     * 更新者用户名
     */
    private String updateUserName;

    /**
     * 试题规则名
     */
    private String paperRuleName;

    /**
     * 试题规则详情
     */
    private PaperRuleVo paperRuleVo;

    /**
     * 优先级
     */
    private Integer priorityLevel;

    /**
     * 试题列表
     */
    private List<QuestionVo> questionVoList = new ArrayList<>();

}
