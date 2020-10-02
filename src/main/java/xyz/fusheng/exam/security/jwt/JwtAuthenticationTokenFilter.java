package xyz.fusheng.exam.security.jwt; /**
 * @author: code-fusheng
 * @Date: 2020/9/30 10:33
 */

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import xyz.fusheng.exam.common.config.JwtConfig;
import xyz.fusheng.exam.security.entity.SelfUser;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @FileName: JwtAuthenticationTokenFilter
 * @Author: code-fusheng
 * @Date: 2020/9/30 10:33
 * @version: 1.0
 * Description: JWT 认证请求 Token 校验器
 */

@Slf4j
public class JwtAuthenticationTokenFilter extends BasicAuthenticationFilter {

    public JwtAuthenticationTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中 JWT 中的 Token
        String tokenHeader = request.getHeader(JwtConfig.tokenHeader);
        if (null != tokenHeader && tokenHeader.startsWith(JwtConfig.tokenPrefix)) {
            try {
                // 截取 JWT 前缀
                String token = tokenHeader.replace(JwtConfig.tokenPrefix, "");
                // 解析 JWT
                Claims claims = Jwts.parser()
                        .setSigningKey(JwtConfig.secret)
                        .parseClaimsJws(token)
                        .getBody();
                // 获取用户名
                String username = claims.getSubject();
                // 获取用户id
                String userId = claims.getId();
                // 用户名和用户id不为空
                if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(userId)) {
                    // 获取用户角色
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    String authority = claims.get("authorities").toString();
                    if (!StringUtils.isEmpty(authority)) {
                        List<Map<String, String>> authorityMap = JSONObject.parseObject(authority, List.class);
                        for (Map<String, String> role : authorityMap) {
                            if(!StringUtils.isEmpty(role)) {
                                authorities.add(new SimpleGrantedAuthority(role.get("authority")));
                            }
                        }
                    }
                    // 组装 selfUser 参数
                    SelfUser selfUser = new SelfUser();
                    selfUser.setUsername(claims.getSubject());
                    selfUser.setUserId(Long.parseLong(claims.getId()));
                    selfUser.setAuthorities(authorities);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(selfUser, userId, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (ExpiredJwtException e) {
                log.info("Token 过期");
            } catch (Exception e) {
                log.info("Token 无效");
            }
        }
        filterChain.doFilter(request, response);
        return;
    }
}
