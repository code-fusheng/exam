package xyz.fusheng.exam.security.handler; /**
 * @author: code-fusheng
 * @Date: 2020/9/30 19:43
 */

import com.alibaba.fastjson.JSON;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import xyz.fusheng.exam.common.utils.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @FileName: UserAuthAccessDeniedHandler
 * @Author: code-fusheng
 * @Date: 2020/9/30 19:43
 * @version: 1.0
 * Description: 用户暂无权限处理
 */

@Component
public class UserAuthAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * 暂无权限处理
     * @param request
     * @param response
     * @param exception
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(new Result<>(403,"请求失败: 未授权!")));
    }
}
