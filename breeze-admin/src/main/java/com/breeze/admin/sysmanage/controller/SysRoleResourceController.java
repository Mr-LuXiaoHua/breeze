package com.breeze.admin.sysmanage.controller;

import com.breeze.common.bo.Result;
import com.breeze.dao.sysmanage.entity.SysResource;
import com.breeze.dao.sysmanage.entity.SysRoleResource;
import com.breeze.service.sysmanage.SysResourceService;
import com.breeze.service.sysmanage.SysRoleResourceService;
import com.breeze.service.sysmanage.dto.*;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 一枕清风
 * @date 2023/3/21
 */
@RequestMapping("/role-resource")
@RestController
public class SysRoleResourceController {


    @Resource
    private SysResourceService sysResourceService;

    @Resource
    private SysRoleResourceService sysRoleResourceService;

    @PostMapping("/list")
    public Result<List<SysRoleResourceListResp>> list(@RequestBody @Validated SysRoleResourceListReq req) {
        List<SysRoleResource> sysRoleResourceList = sysRoleResourceService.list(req);
        List<SysResource> sysResourceList = sysResourceService.list();
        if (CollectionUtils.isEmpty(sysResourceList)) {
            return Result.success(new ArrayList<>());
        }
        Map<Long, SysRoleResource> sysRoleResourceMap = sysRoleResourceList.stream().collect(Collectors.toMap(SysRoleResource::getResourceId, sysRoleResource-> sysRoleResource));
        List<SysRoleResourceListResp> respList = new ArrayList<>();
        for (SysResource sysResource : sysResourceList) {
            SysRoleResourceListResp resp = new SysRoleResourceListResp();
            resp.setId(sysResource.getId());
            resp.setPId(sysResource.getParentId());
            resp.setName(sysResource.getName());
            resp.setOpen(true);
            resp.setChecked(sysRoleResourceMap.containsKey(sysResource.getId()));
            respList.add(resp);
        }

        return Result.success(respList);

    }


    @PostMapping("/add")
    public Result add(@RequestBody @Validated SysRoleResourceAddReq req) {
        sysRoleResourceService.add(req);
        return Result.success();
    }
}
