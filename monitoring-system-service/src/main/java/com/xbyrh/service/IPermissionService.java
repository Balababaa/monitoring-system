package com.xbyrh.service;

import com.xbyrh.repo.entity.Permission;
import com.xbyrh.repo.entity.Role;
import com.xbyrh.repo.model.bo.PermissionSaveBO;
import com.xbyrh.repo.model.params.PermissionSaveParam;

import java.util.List;

/**
 * create at 2021/5/14
 *
 * @author chenxinhui
 */
public interface IPermissionService {

    List<Permission> listByCode(String code, Integer page, Integer limit);

    Long countByCode(String code);

    void save(PermissionSaveBO permissionSaveBO);

    List<Permission> listAll();

    Permission findByCode(String code);

    List<Permission> findByIdList(List<Long> permissionIdList);
}
