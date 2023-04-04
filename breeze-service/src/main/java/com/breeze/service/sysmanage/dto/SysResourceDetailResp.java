package com.breeze.service.sysmanage.dto;

import com.breeze.dao.sysmanage.entity.SysResource;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author 一枕清风
 * @date 2023/3/15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysResourceDetailResp extends SysResourcePageListResp {

    private List<SysResource> treeList;
}
