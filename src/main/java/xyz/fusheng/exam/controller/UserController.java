package xyz.fusheng.exam.controller; /**
 * @author: code-fusheng
 * @Date: 2020/10/2 10:40
 */

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.exam.core.service.UserService;

import javax.annotation.Resource;

/**
 * @FileName: UserController
 * @Author: code-fusheng
 * @Date: 2020/10/2 10:40
 * @version: 1.0
 * Description: 用户控制类
 */

@RestController
@RequestMapping("/user")
@Api(tags = "用户接口", value = "用户操作管理接口")
public class UserController {

    @Resource
    private UserService userService;

}
