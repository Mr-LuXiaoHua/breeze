package com.breeze.service.sysmanage.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.breeze.dao.sysmanage.entity.SysUserRole;
import com.breeze.dao.sysmanage.mapper.SysUserRoleMapper;
import com.breeze.service.sysmanage.SysUserRoleService;
import com.breeze.service.sysmanage.dto.SysUserRoleAddReq;
import com.breeze.service.sysmanage.dto.SysUserRoleListReq;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 一枕清风
 * @date 2023/3/21
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysUserRole> list(SysUserRoleListReq req) {
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRole::getUserId, req.getUserId());
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectList(queryWrapper);
        return sysUserRoles;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void add(SysUserRoleAddReq req) {
        Long userId = req.getUserId();
        List<SysUserRole> sysUserRoleList = new ArrayList<>();
        for (Long roleId : req.getRoleIds()) {
            if (roleId != 0L) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(userId);
                sysUserRole.setRoleId(roleId);
                sysUserRoleList.add(sysUserRole);
            }
        }

        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(SysUserRole::getUserId, userId);
        sysUserRoleMapper.delete(queryWrapper);

        for (SysUserRole sysUserRole : sysUserRoleList) {
            sysUserRoleMapper.insert(sysUserRole);
        }

    }
}
