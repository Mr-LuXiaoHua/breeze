package com.breeze.service.sysmanage;

import com.breeze.common.bo.PageBO;
import com.breeze.dao.sysmanage.entity.SysRole;
import com.breeze.service.sysmanage.dto.*;

import java.util.List;


public interface SysRoleService {


    PageBO<SysRole> pageList(SysRolePageListReq req);


    int add(SysRoleAddReq req);

    SysRole detail(SysRoleDetailReq req);

    int edit(SysRoleEditReq req);

    int delete(SysRoleDeleteReq req);

    List<SysRole> list();




}
