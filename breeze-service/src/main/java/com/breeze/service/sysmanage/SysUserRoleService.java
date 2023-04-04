package com.breeze.service.sysmanage;


import com.breeze.dao.sysmanage.entity.SysUserRole;
import com.breeze.service.sysmanage.dto.SysUserRoleAddReq;
import com.breeze.service.sysmanage.dto.SysUserRoleListReq;

import java.util.List;

public interface SysUserRoleService {


    List<SysUserRole> list(SysUserRoleListReq req);

    void add(SysUserRoleAddReq req);


}
