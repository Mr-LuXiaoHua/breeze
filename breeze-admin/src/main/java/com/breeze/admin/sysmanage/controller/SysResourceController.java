package com.breeze.admin.sysmanage.controller;

import com.breeze.common.bo.PageBO;
import com.breeze.common.bo.Result;
import com.breeze.dao.sysmanage.entity.SysResource;
import com.breeze.service.sysmanage.SysResourceService;
import com.breeze.service.sysmanage.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 一枕清风
 * @date 2023/3/10
 */
@Slf4j
@RequestMapping("/resource")
@RestController
public class SysResourceController {

    @Resource
    private SysResourceService sysResourceService;


    @PostMapping("/page-list")
    public Result<PageBO<SysResourcePageListResp>> pageList(@RequestBody SysResourcePageListReq req) {
        PageBO<SysResourcePageListResp> pageBO = sysResourceService.pageList(req);
        return Result.success(pageBO);
    }


    @PostMapping("/tree")
    public Result<List<SysResource>> tree() {
        List<SysResource> sysResourceList = sysResourceService.tree();
        return Result.success(sysResourceList);
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Validated SysResourceAddReq req) {
        sysResourceService.add(req);
        return Result.success();
    }

    @PostMapping("/detail")
    public Result<SysResourceDetailResp> detail(@RequestBody @Validated SysResourceDetailReq req) {
        SysResourceDetailResp detail = sysResourceService.detail(req);
        return Result.success(detail);
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody @Validated SysResourceEditReq req) {
        sysResourceService.edit(req);
        return Result.success();
    }


    @PostMapping("/delete")
    public Result delete(@RequestBody @Validated SysResourceDeleteReq req) {
        sysResourceService.delete(req);
        return Result.success();
    }


}
