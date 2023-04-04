package com.breeze.service.sysmanage.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.breeze.common.bo.TreeBean;
import com.breeze.common.constants.StringConst;
import com.breeze.common.enums.StatusEnum;
import com.breeze.common.exception.ArgumentException;
import com.breeze.common.exception.DataIsNotExistException;
import com.breeze.common.exception.StatusException;
import com.breeze.common.util.DigestTools;
import com.breeze.common.util.JsonUtils;
import com.breeze.dao.sysmanage.entity.*;
import com.breeze.dao.sysmanage.mapper.*;
import com.breeze.service.sysmanage.LoginService;
import com.breeze.service.sysmanage.dto.LoginReq;
import com.breeze.service.sysmanage.dto.LoginResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 一枕清风
 * @date 2023/3/22
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Resource
    private SysRoleResourceMapper sysRoleResourceMapper;

    @Resource
    private SysResourceMapper sysResourceMapper;

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public LoginResp login(LoginReq req) {

        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername, req.getUsername());
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);

        if (Objects.isNull(sysUser)) {
            throw new DataIsNotExistException("用户名或者密码不正确");
        }

        if (sysUser.getStatus().intValue() == StatusEnum.DISABLED.ordinal()) {
            throw new StatusException("账号已停用");
        }

        String realPassword = req.getPassword() + sysUser.getSalt();
        String md5Password = DigestTools.encryptByMD5(realPassword);

        if (!md5Password.equals(sysUser.getPassword())) {
            throw new ArgumentException("用户名或者密码不正确");
        }

        LoginResp loginResp = new LoginResp();
        loginResp.setNickname(sysUser.getNickname());

        LambdaQueryWrapper<SysUserRole> sysUserRoleQueryWrapper = new LambdaQueryWrapper<>();
        sysUserRoleQueryWrapper.eq(SysUserRole::getUserId, sysUser.getId());

        List<SysUserRole> sysUserRoleList = sysUserRoleMapper.selectList(sysUserRoleQueryWrapper);
        if(CollectionUtils.isEmpty(sysUserRoleList)) {
            loginResp.setResourceCodeList(new ArrayList<>());
            loginResp.setTreeBeanList(new ArrayList<>());
            return loginResp;
        }

        List<Long> roleIdList = sysUserRoleList.stream().map(sysUserRole-> sysUserRole.getRoleId()).collect(Collectors.toList());


        LambdaQueryWrapper<SysRole> sysRoleQueryWrapper = new LambdaQueryWrapper<>();
        sysRoleQueryWrapper.in(SysRole::getId, roleIdList);
        List<SysRole> sysRoleList = sysRoleMapper.selectList(sysRoleQueryWrapper);
        if(CollectionUtils.isEmpty(sysRoleList)) {
            loginResp.setResourceCodeList(new ArrayList<>());
            loginResp.setTreeBeanList(new ArrayList<>());
            return loginResp;
        }


        List<SysResource> sysResourceList;
        // 超级管理员, 查看所有资源
        List<SysRole> superRoleList = sysRoleList.stream().filter(sysRole -> sysRole.getCode().equalsIgnoreCase(StringConst.SUPER_ADMINISTRATOR)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(superRoleList)) {
            LambdaQueryWrapper<SysResource> sysResourceQueryWrapper = new LambdaQueryWrapper<>();
            sysResourceQueryWrapper.orderByAsc(SysResource::getOrderNum);
            sysResourceList = sysResourceMapper.selectList(sysResourceQueryWrapper);
        } else {
            LambdaQueryWrapper<SysRoleResource> sysRoleResourceQueryWrapper = new LambdaQueryWrapper<>();
            sysRoleResourceQueryWrapper.in(SysRoleResource::getRoleId, roleIdList);

            List<SysRoleResource> sysRoleResourceList = sysRoleResourceMapper.selectList(sysRoleResourceQueryWrapper);
            if(CollectionUtils.isEmpty(sysRoleResourceList)) {
                loginResp.setResourceCodeList(new ArrayList<>());
                loginResp.setTreeBeanList(new ArrayList<>());
                return loginResp;
            }

            List<Long> resourceIdList = sysRoleResourceList.stream().map(sysRoleResource-> sysRoleResource.getResourceId()).distinct().collect(Collectors.toList());
            LambdaQueryWrapper<SysResource> sysResourceQueryWrapper = new LambdaQueryWrapper<>();
            sysResourceQueryWrapper.orderByAsc(SysResource::getOrderNum);
            sysResourceQueryWrapper.in(SysResource::getId, resourceIdList);
            sysResourceList = sysResourceMapper.selectList(sysResourceQueryWrapper);
        }



        if(CollectionUtils.isEmpty(sysResourceList)) {
            loginResp.setResourceCodeList(new ArrayList<>());
            loginResp.setTreeBeanList(new ArrayList<>());
            return loginResp;
        }

        List<TreeBean> treeBeanList = new ArrayList<>();
        for (SysResource sysResource : sysResourceList) {
            TreeBean treeBean = new TreeBean();
            treeBean.setId(sysResource.getId());
            treeBean.setName(sysResource.getName());
            treeBean.setParentId(sysResource.getParentId());
            treeBean.setUrl(sysResource.getUrl());
            treeBean.setChildList(new ArrayList<>());

            treeBeanList.add(treeBean);
        }

        loginResp.setUserId(sysUser.getId());

        loginResp.setTreeBeanList(TreeBean.tree(treeBeanList));

        List<String> resourceCodeList = sysResourceList.stream().map(sysResource-> sysResource.getCode()).collect(Collectors.toList());
        loginResp.setResourceCodeList(resourceCodeList);

        List<String> resourceUriList = sysResourceList.stream().map(sysResource-> sysResource.getUrl()).collect(Collectors.toList());
        loginResp.setResourceUrlList(resourceUriList);

        return loginResp;

    }
}
