package com.xbyrh.service.impl;

import com.xbyrh.repo.entity.*;
import com.xbyrh.repo.mapper.RolePermissionRefMapper;
import com.xbyrh.service.IPermissionService;
import com.xbyrh.service.IRolePermissionRefService;
import com.xbyrh.service.context.AuthContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * create at 2021/5/14
 *
 * @author chenxinhui
 */
@Service
public class RolePermissionRefServiceImpl implements IRolePermissionRefService {

    @Autowired
    private RolePermissionRefMapper rolePermissionRefMapper;

    @Autowired
    private IPermissionService permissionService;

    @Override
    @Transactional
    public void addRolePermissionRef(Long roleId, List<String> codeList) {
        User user = AuthContext.getUser();
        deleteByUid(roleId);

        for (String code : codeList) {
            RolePermissionRef rolePermissionRef = new RolePermissionRef();
            rolePermissionRef.setRoleId(roleId);

            Permission permission = permissionService.findByCode(code);

            rolePermissionRef.setPermissionId(permission.getId());

            rolePermissionRef.setModifier(user.getUid());
            rolePermissionRef.setCreator(user.getUid());

            rolePermissionRefMapper.insertSelective(rolePermissionRef);
        }

    }

    @Override
    public List<RolePermissionRef> findByRoleId(Long id) {
        RolePermissionRefExample example = new RolePermissionRefExample();
        example.createCriteria().andRoleIdEqualTo(id).andIsDeleteEqualTo(0);

        return rolePermissionRefMapper.selectByExample(example);
    }

    @Override
    public List<RolePermissionRef> findByRoleIdList(List<Long> roleIdList) {
        RolePermissionRefExample example = new RolePermissionRefExample();
        example.createCriteria().andRoleIdIn(roleIdList).andIsDeleteEqualTo(0);

        return rolePermissionRefMapper.selectByExample(example);
    }

    private void deleteByUid(Long roleId) {
        RolePermissionRefExample example = new RolePermissionRefExample();
        example.createCriteria().andRoleIdEqualTo(roleId).andIsDeleteEqualTo(0);

        List<RolePermissionRef> rolePermissionRefList = rolePermissionRefMapper.selectByExample(example);

        rolePermissionRefList.forEach(x -> x.setIsDelete(1));

        rolePermissionRefList.forEach(x -> rolePermissionRefMapper.updateByPrimaryKeySelective(x));
    }

}
