package com.breeze.service.sysmanage.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 一枕清风
 * @date 2023/3/15
 */
@Data
public class SysOrganizationDetailReq {

    @NotNull(message = "标识不能为空")
    private Long id;
}
