package com.breeze.service.sysmanage.dto;

import lombok.Data;

/**
 * @author 一枕清风
 * @date 2023/3/21
 */
@Data
public class SysUserRoleListResp {

    private Long id;

    private Long pId;

    private String name;

    private boolean open;

    private boolean checked;
}
