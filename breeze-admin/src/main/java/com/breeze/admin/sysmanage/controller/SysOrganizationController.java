package com.breeze.admin.sysmanage.controller;

import com.breeze.common.bo.PageBO;
import com.breeze.common.bo.Result;
import com.breeze.dao.sysmanage.entity.SysOrganization;
import com.breeze.service.sysmanage.SysOrganizationService;
import com.breeze.service.sysmanage.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 一枕清风
 * @date 2023/3/10
 */
@Slf4j
@RequestMapping("/organization")
@RestController
public class SysOrganizationController {

    @Resource
    private SysOrganizationService sysOrganizationService;


    @PostMapping("/page-list")
    public Result<PageBO<SysOrganizationPageListResp>> pageList(@RequestBody SysOrganizationPageListReq req) {
        PageBO<SysOrganizationPageListResp> pageBO = sysOrganizationService.pageList(req);
        return Result.success(pageBO);
    }

    @PostMapping("/tree")
    public Result<List<SysOrganization>> tree() {
        List<SysOrganization> sysOrganizationList = sysOrganizationService.tree();
        return Result.success(sysOrganizationList);
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Validated SysOrganizationAddReq req) {
        sysOrganizationService.add(req);
        return Result.success();
    }


    @PostMapping("/detail")
    public Result<SysOrganizationDetailResp> detail(@RequestBody @Validated SysOrganizationDetailReq req) {
        SysOrganizationDetailResp detail = sysOrganizationService.detail(req);
        return Result.success(detail);
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody @Validated SysOrganizationEditReq req) {
        sysOrganizationService.edit(req);
        return Result.success();
    }



    @PostMapping("/delete")
    public Result delete(@RequestBody @Validated SysOrganizationDeleteReq req) {
        sysOrganizationService.delete(req);
        return Result.success();
    }



}
