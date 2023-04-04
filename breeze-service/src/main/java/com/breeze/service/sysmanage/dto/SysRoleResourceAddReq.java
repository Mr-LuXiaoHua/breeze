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
public class SysRoleResourceAddReq {

    @NotNull(message = "角色标识不能为空")
    private Long roleId;

    @NotEmpty(message = "资源不能为空")
    private List<Long> resIds;
}
