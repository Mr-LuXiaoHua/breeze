package com.breeze.service.sysmanage.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.breeze.common.bo.PageBO;
import com.breeze.common.enums.StatusEnum;
import com.breeze.common.exception.DataIsExistException;
import com.breeze.common.exception.DataIsNotExistException;
import com.breeze.common.util.BeanTools;
import com.breeze.dao.sysmanage.entity.SysRole;
import com.breeze.dao.sysmanage.entity.SysRoleResource;
import com.breeze.dao.sysmanage.mapper.SysRoleMapper;
import com.breeze.dao.sysmanage.mapper.SysRoleResourceMapper;
import com.breeze.service.sysmanage.SysRoleService;
import com.breeze.service.sysmanage.dto.*;
import com.breeze.service.util.PageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author 一枕清风
 * @date 2023/3/16
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysRoleResourceMapper sysRoleResourceMapper;

    @Override
    public PageBO<SysRole> pageList(SysRolePageListReq req) {

        Page<SysRole> page = new Page<>(req.getCurrentPage(), req.getPageSize());
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        sysRoleMapper.selectPage(page, queryWrapper);

        List<SysRole> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return PageBO.empty();
        }
        return PageUtils.toPageBO(page);
    }


    @Override
    public int add(SysRoleAddReq req) {
        if(isRoleExist(req.getCode(), null)) {
            throw new DataIsExistException("角色编码已存在");
        }
        SysRole sysRole = BeanTools.copy(req, SysRole.class);
        return sysRoleMapper.insert(sysRole);
    }

    @Override
    public SysRole detail(SysRoleDetailReq req) {
        SysRole sysRole = getById(req.getId());
        if (Objects.isNull(sysRole)) {
            throw new DataIsNotExistException("角色不存在");
        }
        return sysRole;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int delete(SysRoleDeleteReq req) {
        LambdaQueryWrapper<SysRoleResource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRoleResource::getRoleId, req.getId());
        sysRoleResourceMapper.delete(queryWrapper);
        return sysRoleMapper.deleteById(req.getId());
    }

    @Override
    public int edit(SysRoleEditReq req) {
        if(isRoleExist(req.getCode(), req.getId())) {
            throw new DataIsExistException("角色编码已存在");
        }
        SysRole sysRole = BeanTools.copy(req, SysRole.class);
        return sysRoleMapper.updateById(sysRole);
    }

    @Override
    public List<SysRole> list() {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRole::getStatus, StatusEnum.ENABLED.ordinal());
        return sysRoleMapper.selectList(queryWrapper);
    }

    public boolean isRoleExist(String code, Long id) {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRole::getCode, code.trim());
        if (Objects.nonNull(id)) {
            queryWrapper.ne(SysRole::getId, id);
        }
        SysRole sysRole = sysRoleMapper.selectOne(queryWrapper);
        if (Objects.isNull(sysRole)) {
            return false;
        }
        return true;
    }


    public SysRole getById(Long id) {
        return sysRoleMapper.selectById(id);
    }




}
