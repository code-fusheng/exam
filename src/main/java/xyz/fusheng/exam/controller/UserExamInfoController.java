package xyz.fusheng.exam.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.exam.core.service.UserExamInfoService;

import javax.annotation.Resource;

/**
 * @FileName: UserExamInfoController
 * @Author: code-fusheng
 * @Date: 2020/10/29 21:43
 * @version: 1.0
 * Description: 用户考试信息控制类
 */

@RestController
@RequestMapping("/userExamInfo")
@Api(tags = "用户考试信息接口", value = "用户考试信息管理接口")
public class UserExamInfoController {

    @Resource
    private UserExamInfoService userExamInfoService;

}
