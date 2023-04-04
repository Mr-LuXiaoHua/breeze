package com.breeze.admin.sysmanage.controller;

import com.breeze.admin.sysmanage.manager.SessionManager;
import com.breeze.common.bo.PageBO;
import com.breeze.common.bo.Result;
import com.breeze.dao.sysmanage.entity.SysOrganization;
import com.breeze.dao.sysmanage.entity.SysUser;
import com.breeze.service.sysmanage.SysOrganizationService;
import com.breeze.service.sysmanage.SysUserService;
import com.breeze.service.sysmanage.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 一枕清风
 * @date 2023/3/10
 */
@Slf4j
@RequestMapping("/user")
@RestController
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysOrganizationService sysOrganizationService;


    @Resource
    private SessionManager sessionManager;


    @PostMapping("/page-list")
    public Result<PageBO<SysUserPageListResp>> pageList(@RequestBody SysUserPageListReq req) {
        PageBO<SysUserPageListResp> pageBO = sysUserService.pageList(req);

        // 填充机构名称
        List<Long> idList =  pageBO.getRecordList().stream().map(sysUser-> sysUser.getOrgId()).collect(Collectors.toList());
        Map<Long, SysOrganization> organizationMap = sysOrganizationService.getMapByIdList(idList);
        for (SysUserPageListResp resp : pageBO.getRecordList()) {
            SysOrganization org = organizationMap.get(resp.getOrgId());
            if (Objects.nonNull(org)) {
                resp.setOrgName(org.getName());
            }

        }

        return Result.success(pageBO);
    }



    @PostMapping("/add")
    public Result add(@RequestBody @Validated SysUserAddReq req) {
        sysUserService.add(req);
        return Result.success();
    }


    @PostMapping("/detail")
    public Result<SysUserDetailResp> detail(@RequestBody @Validated SysUserDetailReq req) {
        SysUserDetailResp detail = sysUserService.detail(req);
        List<SysOrganization> treeList = sysOrganizationService.tree();
        detail.setTreeList(treeList);

        return Result.success(detail);
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody @Validated SysUserEditReq req) {
        sysUserService.edit(req);
        return Result.success();
    }



    @PostMapping("/delete")
    public Result delete(@RequestBody @Validated SysUserDeleteReq req) {
        sysUserService.delete(req);
        return Result.success();
    }


    @PostMapping("/modify-password")
    public Result modifyPassword(@RequestBody @Validated SysUserModifyPasswordReq req) {

        Long userId = sessionManager.getLoginInfo().getUserId();
        req.setUserId(userId);

        sysUserService.modifyPassword(req);
        return Result.success();
    }




}
