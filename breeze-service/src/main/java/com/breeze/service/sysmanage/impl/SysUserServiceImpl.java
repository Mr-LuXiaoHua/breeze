package com.breeze.service.sysmanage.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.breeze.common.bo.PageBO;
import com.breeze.common.exception.ArgumentException;
import com.breeze.common.exception.DataIsExistException;
import com.breeze.common.exception.DataIsNotExistException;
import com.breeze.common.util.BeanTools;
import com.breeze.common.util.DigestTools;
import com.breeze.common.util.RandomTools;
import com.breeze.dao.sysmanage.entity.SysUser;
import com.breeze.dao.sysmanage.entity.SysUserRole;
import com.breeze.dao.sysmanage.mapper.SysUserMapper;
import com.breeze.dao.sysmanage.mapper.SysUserRoleMapper;
import com.breeze.service.sysmanage.SysUserService;
import com.breeze.service.sysmanage.dto.*;
import com.breeze.service.util.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class SysUserServiceImpl implements SysUserService {


    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;


    @Override
    public PageBO<SysUserPageListResp> pageList(SysUserPageListReq req) {

        Page<SysUser> page = new Page<>(req.getCurrentPage(), req.getPageSize());
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        sysUserMapper.selectPage(page, queryWrapper);

        List<SysUser> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return PageBO.empty();
        }

        List<SysUserPageListResp> recordList = new ArrayList<>();
        for (SysUser sysUser : records) {
            SysUserPageListResp resp = BeanTools.copy(sysUser, SysUserPageListResp.class);
            recordList.add(resp);
        }




        return PageUtils.toPageBO(page, recordList);
    }


    @Override
    public int add(SysUserAddReq req) {
        if(isUserExist(req.getUsername(), null)) {
            throw new DataIsExistException("用户名编码已存在");
        }

        SysUser sysUser = BeanTools.copy(req, SysUser.class);

        // 密码加密
        String salt = RandomTools.randomAlphabetic(6);
        String password = req.getPassword().trim();
        String realPassword = password + salt;
        String md5Password = DigestTools.encryptByMD5(realPassword);

        sysUser.setPassword(md5Password);
        sysUser.setSalt(salt);


        return sysUserMapper.insert(sysUser);
    }

    @Override
    public SysUserDetailResp detail(SysUserDetailReq req) {
        SysUser sysUser = getById(req.getId());
        if (Objects.isNull(sysUser)) {
            throw new DataIsNotExistException("用户不存在");
        }
        SysUserDetailResp resp = BeanTools.copy(sysUser, SysUserDetailResp.class);
        return resp;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int delete(SysUserDeleteReq req) {

        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRole::getUserId, req.getId());
        sysUserRoleMapper.delete(queryWrapper);

        return sysUserMapper.deleteById(req.getId());
    }

    @Override
    public int edit(SysUserEditReq req) {
        if(isUserExist(req.getUsername(), req.getId())) {
            throw new DataIsExistException("用户名已存在");
        }
        SysUser sysUser = BeanTools.copy(req, SysUser.class);
        return sysUserMapper.updateById(sysUser);
    }

    @Override
    public void modifyPassword(SysUserModifyPasswordReq req) {
        SysUser sysUser = getById(req.getUserId());
        // 比较旧密码是否正确
        String oldPassword = req.getOldPassword().trim();
        String realPassword = oldPassword + sysUser.getSalt();
        String md5Password = DigestTools.encryptByMD5(realPassword);

        if (!md5Password.equals(sysUser.getPassword())) {
            throw new ArgumentException("原密码输入错误");
        }

        // 设置新密码
        String newPassword = req.getPassword().trim();
        realPassword = newPassword + sysUser.getSalt();
        md5Password = DigestTools.encryptByMD5(realPassword);

        sysUser.setPassword(md5Password);

        sysUserMapper.updateById(sysUser);
    }

    public boolean isUserExist(String username, Long id) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername, username.trim());
        if (Objects.nonNull(id)) {
            queryWrapper.ne(SysUser::getId, id);
        }
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        if (Objects.isNull(sysUser)) {
            return false;
        }
        return true;
    }


    public SysUser getById(Long id) {
        return sysUserMapper.selectById(id);
    }




}
