package xyz.fusheng.exam.core.dto; /**
 * @author: code-fusheng
 * @Date: 2020/10/7 23:18
 */

import lombok.Data;

import java.io.Serializable;

/**
 * @FileName: BaseDto
 * @Author: code-fusheng
 * @Date: 2020/10/7 23:18
 * @version: 1.0
 * Description:
 */

@Data
public class BaseDto implements Serializable {

    /**
     * 操作者编号
     */
    private Long operationUserId;

}
