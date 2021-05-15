package com.xbyrh.repo.mapper;

import com.xbyrh.repo.entity.RolePermissionRef;
import com.xbyrh.repo.entity.RolePermissionRefExample;
import java.util.List;

public interface RolePermissionRefMapper {
    int countByExample(RolePermissionRefExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RolePermissionRef record);

    int insertSelective(RolePermissionRef record);

    List<RolePermissionRef> selectByExample(RolePermissionRefExample example);

    RolePermissionRef selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RolePermissionRef record);

    int updateByPrimaryKey(RolePermissionRef record);
}