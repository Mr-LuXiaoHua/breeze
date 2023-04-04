package com.breeze.service.sysmanage;

import com.breeze.service.sysmanage.dto.LoginReq;
import com.breeze.service.sysmanage.dto.LoginResp;

/**
 * @author 一枕清风
 * @date 2023/3/22
 */
public interface LoginService {

    LoginResp login(LoginReq req);
}
