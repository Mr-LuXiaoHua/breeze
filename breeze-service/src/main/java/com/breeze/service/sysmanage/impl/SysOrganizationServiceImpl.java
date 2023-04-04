package com.breeze.service.sysmanage.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.breeze.common.bo.PageBO;
import com.breeze.common.constants.StringConst;
import com.breeze.common.constants.SymbolConst;
import com.breeze.common.exception.DataIsExistException;
import com.breeze.common.exception.DataIsNotExistException;
import com.breeze.common.util.BeanTools;
import com.breeze.dao.sysmanage.entity.SysOrganization;
import com.breeze.dao.sysmanage.mapper.SysOrganizationMapper;
import com.breeze.service.sysmanage.SysOrganizationService;
import com.breeze.service.sysmanage.dto.*;
import com.breeze.service.util.PageUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 一枕清风
 * @date 2023/3/16
 */
@Service
public class SysOrganizationServiceImpl implements SysOrganizationService {

    @Resource
    private SysOrganizationMapper sysOrganizationMapper;

    @Override
    public PageBO<SysOrganizationPageListResp> pageList(SysOrganizationPageListReq req) {

        Page<SysOrganization> page = new Page<>(req.getCurrentPage(), req.getPageSize());
        LambdaQueryWrapper<SysOrganization> queryWrapper = new LambdaQueryWrapper<>();
        sysOrganizationMapper.selectPage(page, queryWrapper);

        List<SysOrganization> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return PageBO.empty();
        }
        List<Long> idList = records.stream().map(sysOrganization-> sysOrganization.getParentId()).collect(Collectors.toList());
        Map<Long, SysOrganization> resourceMap = getMapByIdList(idList);

        List<SysOrganizationPageListResp> respList = new ArrayList<>();
        for (SysOrganization sysOrganization : records) {
            SysOrganizationPageListResp resp = BeanTools.copy(sysOrganization, SysOrganizationPageListResp.class);

            SysOrganization parent = resourceMap.get(sysOrganization.getParentId());
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
    public List<SysOrganization> tree() {
        LambdaQueryWrapper<SysOrganization> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(SysOrganization::getOrderNum);
        List<SysOrganization> sysOrganizationList = sysOrganizationMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(sysOrganizationList)) {
            return new ArrayList<>();
        }
        for (SysOrganization sysOrganization : sysOrganizationList) {
            indentName(sysOrganization);
        }
        return sysOrganizationList;
    }

    @Override
    public int add(SysOrganizationAddReq req) {
        if(isOrganizationExist(req.getCode(), null)) {
            throw new DataIsExistException("机构编码已存在");
        }
        SysOrganization sysOrganization = BeanTools.copy(req, SysOrganization.class);
        sysOrganization.setLevel(getLevel(sysOrganization.getParentId()));
        return sysOrganizationMapper.insert(sysOrganization);
    }

    @Override
    public SysOrganizationDetailResp detail(SysOrganizationDetailReq req) {
        SysOrganization sysOrganization = getById(req.getId());
        if (Objects.isNull(sysOrganization)) {
            throw new DataIsNotExistException("机构不存在");
        }

        SysOrganizationDetailResp resp = BeanTools.copy(sysOrganization, SysOrganizationDetailResp.class);
        resp.setTreeList(tree());

        return resp;
    }

    @Override
    public int delete(SysOrganizationDeleteReq req) {
        return sysOrganizationMapper.deleteById(req.getId());
    }

    @Override
    public int edit(SysOrganizationEditReq req) {
        if(isOrganizationExist(req.getCode(), req.getId())) {
            throw new DataIsExistException("机构编码已存在");
        }
        SysOrganization sysOrganization = BeanTools.copy(req, SysOrganization.class);
        sysOrganization.setLevel(getLevel(sysOrganization.getParentId()));
        return sysOrganizationMapper.updateById(sysOrganization);
    }

    @Override
    public Map<Long, SysOrganization> getMapByIdList(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return new HashMap<>();
        }
        LambdaQueryWrapper<SysOrganization> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysOrganization::getId, idList);
        List<SysOrganization> sysResources = sysOrganizationMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(sysResources)) {
            return new HashMap<>();
        }
        Map<Long, SysOrganization> resourceMap = sysResources.stream().collect(Collectors.toMap(SysOrganization::getId, sysOrganization-> sysOrganization));
        return resourceMap;
    }


    private void indentName(SysOrganization sysOrganization) {
        StringBuilder sb = new StringBuilder();
        int level = sysOrganization.getLevel();
        for (int i=0; i<level; i++) {
            sb.append(SymbolConst.ELLIPSIS).append(SymbolConst.ELLIPSIS);
        }
        sb.append(sysOrganization.getName());
        sysOrganization.setName(sb.toString());
    }


    public boolean isOrganizationExist(String code, Long id) {
        LambdaQueryWrapper<SysOrganization> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysOrganization::getCode, code.trim());
        if (Objects.nonNull(id)) {
            queryWrapper.ne(SysOrganization::getId, id);
        }
        SysOrganization sysOrganization = sysOrganizationMapper.selectOne(queryWrapper);
        if (Objects.isNull(sysOrganization)) {
            return false;
        }
        return true;
    }


    public SysOrganization getById(Long id) {
        return sysOrganizationMapper.selectById(id);
    }


    public int getLevel(long parentId) {
        SysOrganization sysOrganization = getById(parentId);
        return sysOrganization.getLevel() + 1;
    }

}
