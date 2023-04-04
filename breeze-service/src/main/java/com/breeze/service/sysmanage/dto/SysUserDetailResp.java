package com.breeze.service.sysmanage.dto;

import com.breeze.dao.sysmanage.entity.SysOrganization;
import lombok.Data;

import java.util.List;

/**
 * @author 一枕清风
 * @date 2023/3/13
 */
@Data
public class SysUserDetailResp {

    private Long id;

    private String username;

    private String nickname;

    private Integer status;

    private Long orgId;

    private String orgName;


    private List<SysOrganization> treeList;



}
