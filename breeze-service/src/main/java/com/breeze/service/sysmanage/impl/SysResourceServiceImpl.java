package com.breeze.service.sysmanage.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.breeze.common.bo.PageBO;
import com.breeze.common.constants.StringConst;
import com.breeze.common.constants.SymbolConst;
import com.breeze.common.exception.DataIsExistException;
import com.breeze.common.exception.DataIsNotExistException;
import com.breeze.common.util.BeanTools;
import com.breeze.dao.sysmanage.entity.SysResource;
import com.breeze.dao.sysmanage.mapper.SysResourceMapper;
import com.breeze.service.sysmanage.SysResourceService;
import com.breeze.service.sysmanage.dto.*;
import com.breeze.service.util.PageUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 一枕清风
 * @date 2023/3/9
 */
@Service
public class SysResourceServiceImpl implements SysResourceService {

    @Resource
    private SysResourceMapper sysResourceMapper;


    @Override
    public PageBO<SysResourcePageListResp> pageList(SysResourcePageListReq req) {
        Page<SysResource> page = new Page<>(req.getCurrentPage(), req.getPageSize());
        LambdaQueryWrapper<SysResource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(SysResource::getOrderNum);
        sysResourceMapper.selectPage(page, queryWrapper);

        List<SysResource> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return PageBO.empty();
        }
        List<Long> idList = records.stream().map(sysResource-> sysResource.getParentId()).collect(Collectors.toList());
        Map<Long, SysResource> resourceMap = getMapByIdList(idList);

        List<SysResourcePageListResp> respList = new ArrayList<>();
        for (SysResource sysResource : records) {
            SysResourcePageListResp resp = BeanTools.copy(sysResource, SysResourcePageListResp.class);

            resp.setTypeName(SysResource.TypeEnum.getTypeName(sysResource.getType()));
            SysResource parent = resourceMap.get(sysResource.getParentId());
            if (Objects.nonNull(parent)) {
                resp.setParentName(parent.getName());
            } else {
                resp.setParentName(StringConst.EMPTY);
            }

            respList.add(resp);
        }

        return PageUtils.toPageBO(page, respList);

    }


    @Override
    public int add(SysResourceAddReq req) {
        if(isResourceExist(req.getCode(), null)) {
            throw new DataIsExistException("资源编码已存在");
        }
        SysResource sysResource = BeanTools.copy(req, SysResource.class);
        sysResource.setLevel(getLevel(sysResource.getParentId()));
        return sysResourceMapper.insert(sysResource);
    }


    @Override
    public SysResourceDetailResp detail(SysResourceDetailReq req) {
        SysResource sysResource = getById(req.getId());
        if (Objects.isNull(sysResource)) {
            throw new DataIsNotExistException("资源不存在");
        }

        SysResourceDetailResp resp = BeanTools.copy(sysResource, SysResourceDetailResp.class);
        resp.setTreeList(tree());

        return resp;
    }


    @Override
    public int edit(SysResourceEditReq req) {
        if(isResourceExist(req.getCode(), req.getId())) {
            throw new DataIsExistException("资源编码已存在");
        }
        SysResource sysResource = BeanTools.copy(req, SysResource.class);
        sysResource.setLevel(getLevel(sysResource.getParentId()));
        return sysResourceMapper.updateById(sysResource);
    }




    @Override
    public int delete(SysResourceDeleteReq req) {
        return sysResourceMapper.deleteById(req.getId());
    }

    @Override
    public List<SysResource> list() {
        LambdaQueryWrapper<SysResource> queryWrapper = new LambdaQueryWrapper<>();
        return sysResourceMapper.selectList(queryWrapper);
    }

    @Override
    public List<SysResource> tree() {
        LambdaQueryWrapper<SysResource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysResource::getType, SysResource.TypeEnum.DIR.ordinal(), SysResource.TypeEnum.MENU.ordinal());
        queryWrapper.orderByAsc(SysResource::getOrderNum);
        List<SysResource> sysResourceList = sysResourceMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(sysResourceList)) {
            return new ArrayList<>();
        }
        for (SysResource sysResource : sysResourceList) {
            indentName(sysResource);
        }
        return sysResourceList;
    }


    /**
     * 判断资源编码是否已存在
     * @param code
     * @return
     */
    public boolean isResourceExist(String code, Long id) {
        LambdaQueryWrapper<SysResource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysResource::getCode, code.trim());
        if (Objects.nonNull(id)) {
            queryWrapper.ne(SysResource::getId, id);
        }
        SysResource sysResource = sysResourceMapper.selectOne(queryWrapper);
        if (Objects.isNull(sysResource)) {
            return false;
        }
        return true;
    }


    public Map<Long, SysResource> getMapByIdList(List<Long> idList) {
        LambdaQueryWrapper<SysResource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysResource::getId, idList);
        List<SysResource> sysResources = sysResourceMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(sysResources)) {
            return new HashMap<>();
        }
        Map<Long, SysResource> resourceMap = sysResources.stream().collect(Collectors.toMap(SysResource::getId, sysResource-> sysResource));
        return resourceMap;
    }

    public SysResource getById(Long id) {
        return sysResourceMapper.selectById(id);
    }


    /**
     * 缩进名字
     * @param sysResource
     */
    private void indentName(SysResource sysResource) {
        StringBuilder sb = new StringBuilder();
        int level = sysResource.getLevel();
        for (int i=0; i<level; i++) {
            sb.append(SymbolConst.ELLIPSIS).append(SymbolConst.ELLIPSIS);
        }
        sb.append(sysResource.getName());
        sysResource.setName(sb.toString());
    }


    public int getLevel(long parentId) {
        SysResource sysResource = getById(parentId);
        return sysResource.getLevel() + 1;
    }
}
