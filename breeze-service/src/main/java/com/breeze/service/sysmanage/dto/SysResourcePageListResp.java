package com.breeze.service.sysmanage.dto;

import lombok.Data;

/**
 * @author 一枕清风
 * @date 2023/3/13
 */
@Data
public class SysResourcePageListResp {

    private Long id;


    private String name;


    private String code;


    private String url;


    private Integer type;

    private String typeName;

    private Integer orderNum;

    /**
     * 父级id, 0则表示根目录
     */
    private Long parentId;

    private String parentName;

}
