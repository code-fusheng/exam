package xyz.fusheng.exam.core.vo; /**
 * @author: code-fusheng
 * @Date: 2020/10/8 0:29
 */

import lombok.Data;
import xyz.fusheng.exam.core.entity.Repository;

/**
 * @FileName: RepositoryVo
 * @Author: code-fusheng
 * @Date: 2020/10/8 0:29
 * @version: 1.0
 * Description:
 */

@Data
public class RepositoryVo extends Repository {

    /**
     * 创建者用户名
     */
    private String createdUserName;

    /**
     * 更新者用户名
     */
    private String updateUserName;

}
