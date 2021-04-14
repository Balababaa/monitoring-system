package com.xbyrh.mapper;

import com.xbyrh.entity.User;
import com.xbyrh.entity.UserExample;
import java.util.List;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);
}