package xyz.fusheng.exam.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fusheng.exam.common.utils.Result;
import xyz.fusheng.exam.common.utils.SecurityUtil;
import xyz.fusheng.exam.core.entity.User;
import xyz.fusheng.exam.core.service.UserService;

import javax.annotation.Resource;

/**
 * @FileName: AdminController
 * @Author: code-fusheng
 * @Date: 2020/10/8 13:30
 * @version: 1.0
 * Description: 当前用户接口
 */

@RestController
@RequestMapping("/admin")
@Api(tags = "登录用户接口", value = "获取登录用户 - 信息、角色、权限")
public class AdminController {

    @Resource
    private UserService userService;

    /**
     * 获取当前用户信息（前端需求接口）
     * @return
     */
    @GetMapping("/info")
    public Result<User> info() {
        Long userId  = SecurityUtil.getUserId();
        User userInfo = userService.selectUserInfoById(userId);
        return new Result<>("操作提示: 成功查询当前用户信息!", userInfo);
    }

}
