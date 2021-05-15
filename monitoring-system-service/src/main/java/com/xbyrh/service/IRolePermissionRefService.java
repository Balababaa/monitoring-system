package com.xbyrh.service;

import com.xbyrh.repo.entity.Permission;
import com.xbyrh.repo.entity.RolePermissionRef;

import java.util.List;

/**
 * create at 2021/5/14
 *
 * @author chenxinhui
 */
public interface IRolePermissionRefService {

    void addRolePermissionRef(Long roleId, List<String> codeList);

    List<RolePermissionRef> findByRoleId(Long id);

    List<RolePermissionRef> findByRoleIdList(List<Long> roleIdList);
}
