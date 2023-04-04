package com.breeze.admin.sysmanage.manager;

import com.breeze.common.constants.StringConst;
import com.breeze.service.sysmanage.dto.LoginResp;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author 一枕清风
 * @date 2023/3/27
 */
@Component
public class SessionManager {



    public HttpSession getHttpSession() {
        HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        return session;
    }


    public void storeLoginInfo(LoginResp loginResp) {
        getHttpSession().setAttribute(StringConst.USER_SESSION, loginResp);
    }

    public LoginResp getLoginInfo() {
        LoginResp resp = null;
        Object obj = getHttpSession().getAttribute(StringConst.USER_SESSION);
        if(Objects.isNull(obj)) {
            return resp;
        }
        return (LoginResp) obj;
    }


}
