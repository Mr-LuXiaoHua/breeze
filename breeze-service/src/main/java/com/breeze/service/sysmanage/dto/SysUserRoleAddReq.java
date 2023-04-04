package com.breeze.service.sysmanage.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 一枕清风
 * @date 2023/3/21
 */
@Data
public class SysUserRoleAddReq {

    @NotNull(message = "用户标识不能为空")
    private Long userId;

    @NotEmpty(message = "角色不能为空")
    private List<Long> roleIds;
}
