package com.xbyrh.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.xbyrh.repo.entity.*;
import com.xbyrh.repo.mapper.RoleMapper;
import com.xbyrh.repo.model.bo.RoleBO;
import com.xbyrh.repo.model.bo.RoleSaveBO;
import com.xbyrh.repo.model.bo.UserSaveBO;
import com.xbyrh.repo.model.mapper.RoleConverter;
import com.xbyrh.service.IPermissionService;
import com.xbyrh.service.IRolePermissionRefService;
import com.xbyrh.service.IRoleService;
import com.xbyrh.service.context.AuthContext;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * create at 2021/5/14
 *
 * @author chenxinhui
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleConverter roleConverter;

    @Autowired
    private IRolePermissionRefService rolePermissionRefService;

    @Autowired
    private IPermissionService permissionService;

    @Override
    public Long countByCode(String code) {
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(code)) {
            criteria.andCodeEqualTo(code);
        }

        return (long) roleMapper.countByExample(example);
    }

    @Override
    public List<RoleBO> listByCode(String code, Integer page, Integer limit) {
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(code)) {
            criteria.andCodeEqualTo(code);
        }

        example.setLimit(limit);
        example.setOffset((page - 1) * limit);

        List<Role> roleList = roleMapper.selectByExample(example);

        List<RoleBO> roleBOList = roleConverter.toBOList(roleList);

        for (RoleBO roleBO : roleBOList) {
            List<RolePermissionRef> rolePermissionRefList = rolePermissionRefService.findByRoleId(roleBO.getId());

            if (!CollectionUtils.isEmpty(rolePermissionRefList)) {
                List<Long> permissionIdList = rolePermissionRefList.stream().map(RolePermissionRef::getPermissionId).collect(
                        Collectors.toList());

                List<Permission> permissionList = permissionService.findByIdList(permissionIdList);

                roleBO.setPermissionList(permissionList);
            } else {
                roleBO.setPermissionList(new ArrayList<>());
            }
        }

        return roleBOList;
    }

    @Override
    public void save(RoleSaveBO roleSaveBO) {
        Role role = new Role();
        role.setName(roleSaveBO.getName());
        role.setCode(roleSaveBO.getCode());

        User user = AuthContext.getUser();
        role.setCreator(user.getUid());
        role.setModifier(user.getUid());

        roleMapper.insertSelective(role);
    }

    @Override
    public Role findByCode(String code) {
        RoleExample example = new RoleExample();
        example.createCriteria()
                .andCodeEqualTo(code);

        List<Role> roleList = roleMapper.selectByExample(example);

        if (CollectionUtil.isEmpty(roleList)) {
            return null;
        }else{
            return roleList.get(0);
        }
    }

    @Override
    public List<RoleBO> listAll() {
        RoleExample example = new RoleExample();
        return roleConverter.toBOList(roleMapper.selectByExample(example));
    }

    @Override
    public List<Role> findByIdList(List<Long> roleIdList) {
        RoleExample example = new RoleExample();
        example.createCriteria().andIdIn(roleIdList);
        return roleMapper.selectByExample(example);
    }
}
