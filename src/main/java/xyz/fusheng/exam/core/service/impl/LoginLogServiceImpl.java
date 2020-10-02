package xyz.fusheng.exam.core.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import xyz.fusheng.exam.core.dto.LoginLogDto;
import xyz.fusheng.exam.core.mapper.LoginLogMapper;
import xyz.fusheng.exam.core.entity.LoginLog;
import xyz.fusheng.exam.core.service.LoginLogService;
 /**
  * @author:   code-fusheng
  * @Date:     2020/10/2 0:13
  */
@Service
public class LoginLogServiceImpl implements LoginLogService{

    @Resource
    private LoginLogMapper loginLogMapper;

     /**
      * 保存用户登录日志
      * @param loginLog
      */
     @Override
     public void save(LoginLog loginLog) {
          loginLogMapper.insert(loginLog);
     }
 }
