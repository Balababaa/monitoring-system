package com.xbyrh.repo.mapper;

import com.xbyrh.repo.entity.UserRoleRef;
import com.xbyrh.repo.entity.UserRoleRefExample;
import java.util.List;

public interface UserRoleRefMapper {
    int countByExample(UserRoleRefExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserRoleRef record);

    int insertSelective(UserRoleRef record);

    List<UserRoleRef> selectByExample(UserRoleRefExample example);

    UserRoleRef selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRoleRef record);

    int updateByPrimaryKey(UserRoleRef record);
}