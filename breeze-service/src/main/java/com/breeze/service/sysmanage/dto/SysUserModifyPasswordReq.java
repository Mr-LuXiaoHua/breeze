package com.breeze.service.sysmanage.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author 一枕清风
 * @date 2023/3/14
 */
@Data
public class SysUserModifyPasswordReq {

    private Long userId;


    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;


    @NotBlank(message = "新密码不能为空")
    private String password;



}
