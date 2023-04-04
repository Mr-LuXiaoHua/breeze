package com.breeze.service.sysmanage;

import com.breeze.common.bo.PageBO;
import com.breeze.dao.sysmanage.entity.SysOrganization;
import com.breeze.service.sysmanage.dto.*;

import java.util.List;
import java.util.Map;


public interface SysOrganizationService {


    PageBO<SysOrganizationPageListResp> pageList(SysOrganizationPageListReq req);

    List<SysOrganization> tree();

    int add(SysOrganizationAddReq req);

     SysOrganizationDetailResp detail(SysOrganizationDetailReq req);

    int edit(SysOrganizationEditReq req);

    int delete(SysOrganizationDeleteReq req);

    Map<Long, SysOrganization> getMapByIdList(List<Long> idList);




}
