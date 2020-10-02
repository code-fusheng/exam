package xyz.fusheng.exam.security.handler; /**
 * @author: code-fusheng
 * @Date: 2020/9/30 16:06
 */

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import xyz.fusheng.exam.common.enums.StateEnums;
import xyz.fusheng.exam.common.utils.Result;
import xyz.fusheng.exam.common.utils.SecurityUtil;
import xyz.fusheng.exam.core.dto.LoginLogDto;
import xyz.fusheng.exam.core.entity.LoginLog;
import xyz.fusheng.exam.core.service.LoginLogService;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @FileName: UserLoginFailureHandler
 * @Author: code-fusheng
 * @Date: 2020/9/30 16:06
 * @version: 1.0
 * Description: 用户登录失败处理类
 */

@Slf4j
@Component
public class UserLoginFailureHandler implements AuthenticationFailureHandler{

    @Resource
    private LoginLogService loginLogService;

    /**
     * 登录失败返回结果
     * @param request
     * @param response
     * @param exception
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        LoginLog loginLog = SecurityUtil.createLoginLog(request);
        loginLog.setUserName(request.getParameter("username"));
        loginLog.setLoginStatus(StateEnums.LOGIN_ERROR.getCode());
        if (exception instanceof UsernameNotFoundException) {
            log.info("【登录失败】" + exception.getMessage());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(new Result<>(500, "登录失败: 用户名不存在!")));
            loginLog.setMsg("登录失败: 用户名不存在！");
        }
        if (exception instanceof LockedException) {
            log.info("【登录失败】" + exception.getMessage());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(new Result<>(500, "登录失败: 用户被解冻!")));
            loginLog.setMsg("登录失败: 用户被冻结！");
        }
        if (exception instanceof BadCredentialsException) {
            log.info("【登录失败】" + exception.getMessage());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(new Result<>(500, "登录失败: 用户名密码不正确!")));
            loginLog.setMsg("登录失败: 用户名密码不正确！");
        }
        // 保存登录日志信息
        loginLogService.save(loginLog);
    }
}
