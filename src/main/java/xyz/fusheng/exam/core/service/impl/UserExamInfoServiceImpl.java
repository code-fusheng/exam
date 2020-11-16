package xyz.fusheng.exam.core.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import xyz.fusheng.exam.core.mapper.UserExamInfoMapper;
import xyz.fusheng.exam.core.service.UserExamInfoService;
 /**
  * @author:   code-fusheng
  * @Date:     2020/10/29 20:23
  */
@Service
public class UserExamInfoServiceImpl implements UserExamInfoService{

    @Resource
    private UserExamInfoMapper userExamInfoMapper;

}
