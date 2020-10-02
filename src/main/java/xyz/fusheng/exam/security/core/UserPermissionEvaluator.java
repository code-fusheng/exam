package xyz.fusheng.exam.security.core; /**
 * @author: code-fusheng
 * @Date: 2020/9/30 11:46
 */

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import xyz.fusheng.exam.core.entity.Menu;
import xyz.fusheng.exam.core.service.UserService;
import xyz.fusheng.exam.security.entity.SelfUser;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @FileName: UserPermissionEvaluator
 * @Author: code-fusheng
 * @Date: 2020/9/30 11:46
 * @version: 1.0
 * Description: 自定义权限注解验证
 */

@Component
public class UserPermissionEvaluator implements PermissionEvaluator {

    @Resource
    private UserService userService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission) {
        // 获取用户信息
        SelfUser selfUser = (SelfUser) authentication.getPrincipal();
        // 查询用户权限（这里可以将权限放入缓存提高效率）
        Set<String> permissions = new HashSet<>();
        List<Menu> menuList = userService.selectMenusByUserId(selfUser.getUserId());
        for (Menu menu : menuList) {
            permissions.add(menu.getPermission());
        }
        // 权限对比
        if (permissions.contains(permission.toString())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
