package com.xbyrh.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.xbyrh.repo.entity.*;
import com.xbyrh.repo.mapper.PermissionMapper;
import com.xbyrh.repo.model.bo.PermissionSaveBO;
import com.xbyrh.service.IPermissionService;
import com.xbyrh.service.context.AuthContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create at 2021/5/14
 *
 * @author chenxinhui
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> listByCode(String code, Integer page, Integer limit) {
        PermissionExample example = new PermissionExample();
        PermissionExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(code)) {
            criteria.andCodeEqualTo(code);
        }

        example.setLimit(limit);
        example.setOffset((page - 1) * limit);

        return permissionMapper.selectByExample(example);
    }

    @Override
    public Long countByCode(String code) {
        PermissionExample example = new PermissionExample();
        PermissionExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(code)) {
            criteria.andCodeEqualTo(code);
        }

        return (long) permissionMapper.countByExample(example);
    }

    @Override
    public void save(PermissionSaveBO permissionSaveBO) {
        Permission permission = new Permission();
        permission.setName(permissionSaveBO.getName());
        permission.setCode(permissionSaveBO.getCode());
        permission.setType(permissionSaveBO.getType());
        permission.setPath(permissionSaveBO.getPath());
        User user = AuthContext.getUser();
        permission.setCreator(user.getUid());
        permission.setModifier(user.getUid());

        permissionMapper.insertSelective(permission);
    }

    @Override
    public List<Permission> listAll() {
        PermissionExample example = new PermissionExample();
        return permissionMapper.selectByExample(example);
    }

    @Override
    public Permission findByCode(String code) {
        PermissionExample example = new PermissionExample();
        example.createCriteria()
                .andCodeEqualTo(code);

        List<Permission> permissionList = permissionMapper.selectByExample(example);

        if (CollectionUtil.isEmpty(permissionList)) {
            return null;
        } else {
            return permissionList.get(0);
        }
    }

    @Override
    public List<Permission> findByIdList(List<Long> permissionIdList) {
        PermissionExample example = new PermissionExample();
        example.createCriteria().andIdIn(permissionIdList);

        return permissionMapper.selectByExample(example);
    }
}
