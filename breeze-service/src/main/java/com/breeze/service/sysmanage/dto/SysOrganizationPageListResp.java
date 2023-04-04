package com.breeze.service.sysmanage.dto;

import lombok.Data;

/**
 * @author 一枕清风
 * @date 2023/3/13
 */
@Data
public class SysOrganizationPageListResp {

    private Long id;


    private String name;


    private String code;


    private Integer orderNum;

    /**
     * 父级id, 0则表示根目录
     */
    private Long parentId;

    private String parentName;

}
