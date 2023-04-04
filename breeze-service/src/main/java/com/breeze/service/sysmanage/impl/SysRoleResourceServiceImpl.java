package com.breeze.service.sysmanage.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.breeze.dao.sysmanage.entity.SysRoleResource;
import com.breeze.dao.sysmanage.mapper.SysRoleResourceMapper;
import com.breeze.service.sysmanage.SysRoleResourceService;
import com.breeze.service.sysmanage.dto.SysRoleResourceAddReq;
import com.breeze.service.sysmanage.dto.SysRoleResourceListReq;
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
public class SysRoleResourceServiceImpl implements SysRoleResourceService {

    @Resource
    private SysRoleResourceMapper sysRoleResourceMapper;

    @Override
    public List<SysRoleResource> list(SysRoleResourceListReq req) {
        LambdaQueryWrapper<SysRoleResource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRoleResource::getRoleId, req.getRoleId());
        List<SysRoleResource> sysRoleResources = sysRoleResourceMapper.selectList(queryWrapper);
        return sysRoleResources;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void add(SysRoleResourceAddReq req) {
        Long roleId = req.getRoleId();
        List<SysRoleResource> sysRoleResourceList = new ArrayList<>();
        for (Long resId : req.getResIds()) {
            SysRoleResource sysRoleResource = new SysRoleResource();
            sysRoleResource.setRoleId(roleId);
            sysRoleResource.setResourceId(resId);
            sysRoleResourceList.add(sysRoleResource);
        }

        LambdaQueryWrapper<SysRoleResource> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(SysRoleResource::getRoleId, roleId);
        sysRoleResourceMapper.delete(queryWrapper);

        for (SysRoleResource sysRoleResource : sysRoleResourceList) {
            sysRoleResourceMapper.insert(sysRoleResource);
        }

    }
}
