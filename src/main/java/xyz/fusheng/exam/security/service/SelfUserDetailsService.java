package xyz.fusheng.exam.security.service; /**
 * @author: code-fusheng
 * @Date: 2020/9/30 10:17
 */

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import xyz.fusheng.exam.core.entity.User;
import xyz.fusheng.exam.core.service.UserService;
import xyz.fusheng.exam.security.entity.SelfUser;

import javax.annotation.Resource;

/**
 * @FileName: SelfUserDetailsService
 * @Author: code-fusheng
 * @Date: 2020/9/30 10:17
 * @version: 1.0
 * Description: 用户信息 - 详情
 */

@Component
public class SelfUserDetailsService implements UserDetailsService {

    @Resource
    private UserService userService;

    /**
     * 查询用户信息
     * @param username 用户名
     * @return UserDetails SpringSecurity用户信息
     * @throws UsernameNotFoundException
     */
    @Override
    public SelfUser loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        User user = userService.selectUserByName(username);
        if (user!=null) {
            // 组装参数
            SelfUser selfUser = new SelfUser();
            BeanUtils.copyProperties(user, selfUser);
            return selfUser;
        }
        return null;
    }
}
