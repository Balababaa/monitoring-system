package com.xbyrh.repo.mapper;

import com.xbyrh.repo.entity.Permission;
import com.xbyrh.repo.entity.PermissionExample;
import java.util.List;

public interface PermissionMapper {
    int countByExample(PermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}