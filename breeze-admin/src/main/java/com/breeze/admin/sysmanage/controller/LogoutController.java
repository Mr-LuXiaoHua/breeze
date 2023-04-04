package com.breeze.admin.sysmanage.controller;

import com.breeze.common.bo.Result;
import com.breeze.common.constants.StringConst;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 一枕清风
 * @date 2023/3/24
 */
@RestController
public class LogoutController {


    @PostMapping("/logout")
    public Result logout(HttpServletRequest request) {
        request.getSession().removeAttribute(StringConst.USER_SESSION);
        return Result.success();
    }
}
