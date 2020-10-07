package xyz.fusheng.exam.core.service;

import xyz.fusheng.exam.core.entity.LoginLog;

/**
  * @author:   code-fusheng
  * @Date:     2020/10/2 0:13
  */
public interface LoginLogService {

    /**
     * 保存用户登录日志
     * @param loginLog
     */
    void save(LoginLog loginLog);
}
