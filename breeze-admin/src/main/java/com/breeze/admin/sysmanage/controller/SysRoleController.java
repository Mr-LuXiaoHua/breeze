package com.breeze.admin.sysmanage.controller;

import com.breeze.common.bo.PageBO;
import com.breeze.common.bo.Result;
import com.breeze.dao.sysmanage.entity.SysRole;
import com.breeze.service.sysmanage.SysRoleService;
import com.breeze.service.sysmanage.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 一枕清风
 * @date 2023/3/10
 */
@Slf4j
@RequestMapping("/role")
@RestController
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;


    @PostMapping("/page-list")
    public Result<PageBO<SysRole>> pageList(@RequestBody SysRolePageListReq req) {
        PageBO<SysRole> pageBO = sysRoleService.pageList(req);
        return Result.success(pageBO);
    }


    @PostMapping("/add")
    public Result add(@RequestBody @Validated SysRoleAddReq req) {
        sysRoleService.add(req);
        return Result.success();
    }


    @PostMapping("/detail")
    public Result<SysRole> detail(@RequestBody @Validated SysRoleDetailReq req) {
        SysRole detail = sysRoleService.detail(req);
        return Result.success(detail);
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody @Validated SysRoleEditReq req) {
        sysRoleService.edit(req);
        return Result.success();
    }



    @PostMapping("/delete")
    public Result delete(@RequestBody @Validated SysRoleDeleteReq req) {
        sysRoleService.delete(req);
        return Result.success();
    }



}
