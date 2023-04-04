package com.breeze.admin.sysmanage.controller;

import com.breeze.admin.sysmanage.manager.SessionManager;
import com.breeze.common.bo.Result;
import com.breeze.common.constants.StringConst;
import com.breeze.service.sysmanage.LoginService;
import com.breeze.service.sysmanage.dto.LoginReq;
import com.breeze.service.sysmanage.dto.LoginResp;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 一枕清风
 * @date 2023/3/22
 */
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @Resource
    private SessionManager sessionManager;

    @PostMapping("/login")
    public Result<LoginResp> login(@RequestBody @Validated LoginReq req, HttpServletRequest request) {
        LoginResp loginResp = loginService.login(req);
        sessionManager.storeLoginInfo(loginResp);
        return Result.success(loginResp);
    }
}
