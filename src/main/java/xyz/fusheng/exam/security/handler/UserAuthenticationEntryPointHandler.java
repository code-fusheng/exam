package xyz.fusheng.exam.security.handler; /**
 * @author: code-fusheng
 * @Date: 2020/9/30 17:43
 */

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import xyz.fusheng.exam.common.utils.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @FileName: UserAuthenticationEntryPointHandler
 * @Author: code-fusheng
 * @Date: 2020/9/30 17:43
 * @version: 1.0
 * Description: 用户未登录处理
 */

@Component
public class UserAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    /**
     * 用户未登录返回结果
     * @param request
     * @param response
     * @param exception
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(new Result<>(401, "请求失败: 未登录!")));
    }
}
