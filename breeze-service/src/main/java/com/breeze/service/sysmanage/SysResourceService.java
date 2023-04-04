package com.breeze.service.sysmanage;

import com.breeze.common.bo.PageBO;
import com.breeze.dao.sysmanage.entity.SysResource;
import com.breeze.service.sysmanage.dto.*;

import java.util.List;

public interface SysResourceService {


    PageBO<SysResourcePageListResp> pageList(SysResourcePageListReq req);


    int add(SysResourceAddReq req);


    List<SysResource> tree();

    SysResourceDetailResp detail(SysResourceDetailReq req);


    int edit(SysResourceEditReq req);

    int delete(SysResourceDeleteReq req);


    List<SysResource> list();





}
