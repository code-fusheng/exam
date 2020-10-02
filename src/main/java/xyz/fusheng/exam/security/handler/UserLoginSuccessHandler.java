package xyz.fusheng.exam.security.handler; /**
 * @author: code-fusheng
 * @Date: 2020/9/30 15:19
 */

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import xyz.fusheng.exam.common.config.JwtConfig;
import xyz.fusheng.exam.common.enums.StateEnums;
import xyz.fusheng.exam.common.utils.JwtTokenUtil;
import xyz.fusheng.exam.common.utils.Result;
import xyz.fusheng.exam.common.utils.SecurityUtil;
import xyz.fusheng.exam.core.dto.LoginLogDto;
import xyz.fusheng.exam.core.entity.LoginLog;
import xyz.fusheng.exam.core.service.LoginLogService;
import xyz.fusheng.exam.security.entity.SelfUser;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @FileName: UserLoginSuccessHandler
 * @Author: code-fusheng
 * @Date: 2020/9/30 15:19
 * @version: 1.0
 * Description: 登录成功处理类
 */

@Slf4j
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private LoginLogService loginLogService;

    /**
     * 登录成功处理类
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // 组装 JWT
        SelfUser selfUser = (SelfUser) authentication.getPrincipal();
        String token = JwtTokenUtil.createAccessToken(selfUser);
        token = JwtConfig.tokenPrefix + token;

        // 封装返回参数
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(new Result<>("登录成功!", token)));

        LoginLog loginLog = SecurityUtil.createLoginLog(request);
        loginLog.setUserName(selfUser.getUsername());
        loginLog.setMsg("登录成功!");
        loginLog.setLoginStatus(StateEnums.LOGIN_SUCCESS.getCode());
        loginLogService.save(loginLog);

    }
}
