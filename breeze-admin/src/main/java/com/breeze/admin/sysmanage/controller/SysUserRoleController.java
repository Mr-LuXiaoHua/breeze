package com.breeze.admin.sysmanage.controller;

import com.breeze.common.bo.Result;
import com.breeze.dao.sysmanage.entity.SysRole;
import com.breeze.dao.sysmanage.entity.SysUserRole;
import com.breeze.service.sysmanage.SysRoleService;
import com.breeze.service.sysmanage.SysUserRoleService;
import com.breeze.service.sysmanage.dto.SysUserRoleAddReq;
import com.breeze.service.sysmanage.dto.SysUserRoleListReq;
import com.breeze.service.sysmanage.dto.SysUserRoleListResp;
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
@RequestMapping("/user-role")
@RestController
public class SysUserRoleController {


    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysUserRoleService sysUserRoleService;

    @PostMapping("/list")
    public Result<List<SysUserRoleListResp>> list(@RequestBody @Validated SysUserRoleListReq req) {
        List<SysUserRole> sysUserRoleList = sysUserRoleService.list(req);
        List<SysRole> sysRoleList = sysRoleService.list();
        if (CollectionUtils.isEmpty(sysRoleList)) {
            return Result.success(new ArrayList<>());
        }
        Map<Long, SysUserRole> sysUserRoleMap = sysUserRoleList.stream().collect(Collectors.toMap(SysUserRole::getRoleId, sysUserRole-> sysUserRole));
        List<SysUserRoleListResp> respList = new ArrayList<>();

        SysUserRoleListResp parent = new SysUserRoleListResp();
        parent.setId(0L);
        parent.setPId(-1L);
        parent.setName("所有");
        parent.setOpen(true);
        parent.setChecked(true);
        respList.add(parent);
        for (SysRole sysRole : sysRoleList) {
            SysUserRoleListResp resp = new SysUserRoleListResp();
            resp.setId(sysRole.getId());
            resp.setPId(0L);
            resp.setName(sysRole.getName());
            resp.setOpen(true);
            resp.setChecked(sysUserRoleMap.containsKey(sysRole.getId()));
            respList.add(resp);
        }

        return Result.success(respList);

    }


    @PostMapping("/add")
    public Result add(@RequestBody @Validated SysUserRoleAddReq req) {
        sysUserRoleService.add(req);
        return Result.success();
    }
}
