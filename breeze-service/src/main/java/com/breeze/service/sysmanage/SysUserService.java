package com.breeze.service.sysmanage;

import com.breeze.common.bo.PageBO;
import com.breeze.service.sysmanage.dto.*;

public interface SysUserService {


    PageBO<SysUserPageListResp> pageList(SysUserPageListReq req);

    int add(SysUserAddReq req);

    SysUserDetailResp detail(SysUserDetailReq req);

    int edit(SysUserEditReq req);

    int delete(SysUserDeleteReq req);

    void modifyPassword(SysUserModifyPasswordReq req);


}
