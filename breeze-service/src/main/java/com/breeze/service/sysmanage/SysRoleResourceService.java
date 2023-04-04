package com.breeze.service.sysmanage;


import com.breeze.dao.sysmanage.entity.SysRoleResource;
import com.breeze.service.sysmanage.dto.SysRoleResourceAddReq;
import com.breeze.service.sysmanage.dto.SysRoleResourceListReq;

import java.util.List;

public interface SysRoleResourceService {


    List<SysRoleResource> list(SysRoleResourceListReq req);

    void add(SysRoleResourceAddReq req);


}
