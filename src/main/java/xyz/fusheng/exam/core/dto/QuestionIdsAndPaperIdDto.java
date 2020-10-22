package xyz.fusheng.exam.core.dto; /**
 * @author: code-fusheng
 * @Date: 2020/10/22 12:15
 */

import lombok.Data;

import java.util.List;

/**
 * @FileName: QuestionIdsAndPaperIdDto
 * @Author: code-fusheng
 * @Date: 2020/10/22 12:15
 * @version: 1.0
 * Description:
 */

@Data
public class QuestionIdsAndPaperIdDto extends BaseDto{

    private Long PaperId;

    private List<Long> questionIds;

}
