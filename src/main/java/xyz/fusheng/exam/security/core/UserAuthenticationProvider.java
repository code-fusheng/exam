package xyz.fusheng.exam.security.core; /**
 * @author: code-fusheng
 * @Date: 2020/9/30 11:17
 */

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import xyz.fusheng.exam.core.entity.Role;
import xyz.fusheng.exam.core.service.UserService;
import xyz.fusheng.exam.security.entity.SelfUser;
import xyz.fusheng.exam.security.service.SelfUserDetailsService;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @FileName: UserAuthenticationProvider
 * @Author: code-fusheng
 * @Date: 2020/9/30 11:17
 * @version: 1.0
 * Description:自定义登录验证
 */

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private SelfUserDetailsService selfUserDetailsService;
    @Resource
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 取出表单中输入的用户名
        String username = (String) authentication.getPrincipal();
        // 获取表单中输入的密码
        String password = (String) authentication.getCredentials();
        // 查询用户是否存在
        SelfUser userInfo = selfUserDetailsService.loadUserByUsername(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
        // 判断密码是否正确，此处密码使用BCryptPasswordEncoder进行加密
        if (!new BCryptPasswordEncoder().matches(password, userInfo.getPassword())) {
            throw new BadCredentialsException("密码不正确!");
        }
        // 角色集合
        Set<GrantedAuthority> authorities = new HashSet<>();
        // 查询用户角色
        List<Role> roleList = userService.selectRoleByUserId(userInfo.getUserId());
        for (Role role : roleList) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        userInfo.setAuthorities(authorities);
        // 进行登录
        return new UsernamePasswordAuthenticationToken(userInfo, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
