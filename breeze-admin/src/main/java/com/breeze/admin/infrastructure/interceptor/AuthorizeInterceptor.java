package com.breeze.admin.infrastructure.interceptor;

import com.breeze.admin.sysmanage.manager.SessionManager;
import com.breeze.common.exception.ForbiddenException;
import com.breeze.common.util.UrlMatch;
import com.breeze.service.sysmanage.dto.LoginResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * @author 一枕清风
 * @date 2023/3/24
 */
@Slf4j
@Component
public class AuthorizeInterceptor implements HandlerInterceptor {

    @Resource
    private SessionManager sessionManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        isAuthorized(uri);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }



    private void isAuthorized(String uri) {
        LoginResp loginResp = sessionManager.getLoginInfo();
        if (Objects.isNull(loginResp)) {
            throw new ForbiddenException();
        }
        List<String> patterns = loginResp.getResourceUrlList();
        if (!UrlMatch.match(patterns, uri)) {
            log.warn("用户 {} 未被授权访问 {}", loginResp.getNickname(), uri);
            throw new ForbiddenException();
        }

    }
}
