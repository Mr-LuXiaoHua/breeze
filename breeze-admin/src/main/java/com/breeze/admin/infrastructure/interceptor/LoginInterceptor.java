package com.breeze.admin.infrastructure.interceptor;

import com.breeze.admin.sysmanage.manager.SessionManager;
import com.breeze.common.exception.UnauthorizedException;
import com.breeze.service.sysmanage.dto.LoginResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author 一枕清风
 * @date 2023/3/24
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private SessionManager sessionManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        isLogin();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    private void isLogin() {
        LoginResp loginUser = sessionManager.getLoginInfo();
        if (Objects.isNull(loginUser)) {
            throw new UnauthorizedException();
        }
    }


}
