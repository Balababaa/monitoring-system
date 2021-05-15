package com.xbyrh.service;

import com.xbyrh.repo.entity.Role;
import com.xbyrh.repo.model.bo.RoleBO;
import com.xbyrh.repo.model.bo.RoleSaveBO;

import java.util.List;

/**
 * create at 2021/5/14
 *
 * @author chenxinhui
 */
public interface IRoleService {
    Long countByCode(String code);

    List<RoleBO> listByCode(String code, Integer page, Integer limit);

    void save(RoleSaveBO roleSaveBO);

    Role findByCode(String code);

    List<RoleBO> listAll();

    List<Role> findByIdList(List<Long> roleIdList);
}
