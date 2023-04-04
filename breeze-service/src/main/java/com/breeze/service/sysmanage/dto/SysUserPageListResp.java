package com.breeze.service.sysmanage.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 一枕清风
 * @date 2023/3/14
 */
@Data
public class SysUserPageListResp {

    private Long id;

    private String username;

    private String nickname;

    private String salt;

    private Integer status;

    private Long orgId;

    private String orgName;


}
