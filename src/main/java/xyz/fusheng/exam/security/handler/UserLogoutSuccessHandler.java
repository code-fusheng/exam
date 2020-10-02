package xyz.fusheng.exam.security.handler; /**
 * @author: code-fusheng
 * @Date: 2020/9/30 15:59
 */

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import xyz.fusheng.exam.common.utils.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @FileName: UserLogoutSuccessHandler
 * @Author: code-fusheng
 * @Date: 2020/9/30 15:59
 * @version: 1.0
 * Description: 登出成功处理类
 */

@Slf4j
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

    /**
     * 用户登出返回结果
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(new Result<>("登出成功!")));
    }
}
