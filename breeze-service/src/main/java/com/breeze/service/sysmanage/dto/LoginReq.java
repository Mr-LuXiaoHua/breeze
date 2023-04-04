package com.breeze.service.sysmanage.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author 一枕清风
 * @date 2023/3/22
 */
@Data
public class LoginReq {

    @NotBlank(message = "请输入用户名")
    private String username;

    @NotBlank(message = "请输入密码")
    private String password;
}
