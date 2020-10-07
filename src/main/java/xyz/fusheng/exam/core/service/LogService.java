package xyz.fusheng.exam.core.service;

import xyz.fusheng.exam.core.entity.Log;

/**
 * @author: code-fusheng
 * @Date: 2020/10/7 20:36
 */
public interface LogService {

    /**
     * 添加日志
     * @param logger
     */
    void save(Log logger);

}
