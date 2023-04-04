package com.breeze.service.sysmanage.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 一枕清风
 * @date 2023/3/21
 */
@Data
public class SysRoleResourceListReq {

    @NotNull(message = "角色标识不能为空")
    private Long roleId;
}
