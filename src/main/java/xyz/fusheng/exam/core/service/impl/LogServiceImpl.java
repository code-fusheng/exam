package xyz.fusheng.exam.core.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import xyz.fusheng.exam.core.entity.Log;
import xyz.fusheng.exam.core.mapper.LogMapper;
import xyz.fusheng.exam.core.service.LogService;
 /**
  * @author:   code-fusheng
  * @Date:     2020/10/7 20:36
  */
@Service
public class LogServiceImpl implements LogService{

    @Resource
    private LogMapper logMapper;

     /**
      * 添加日志
      * @param logger
      */
     @Override
     public void save(Log logger) {
         logMapper.insert(logger);
     }
 }
