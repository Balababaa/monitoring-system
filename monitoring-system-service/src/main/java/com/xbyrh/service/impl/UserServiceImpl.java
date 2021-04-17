package com.xbyrh.service.impl;

import com.xbyrh.common.exception.NotFoundException;
import com.xbyrh.repo.entity.User;
import com.xbyrh.repo.entity.UserExample;
import com.xbyrh.repo.mapper.UserMapper;
import com.xbyrh.service.IUserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create at 2021/4/14
 *
 * @author chenxinhui
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByUsername(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);

        List<User> userList = userMapper.selectByExample(userExample);

        if(CollectionUtils.isEmpty(userList)){
            throw new NotFoundException(String.format("user(username = %s) not found!", username));
        }

        return userList.get(0);
    }

    @Override
    public User getByEmail(String email) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andEmailEqualTo(email);

        List<User> userList = userMapper.selectByExample(userExample);

        if(CollectionUtils.isEmpty(userList)){
            throw new NotFoundException(String.format("user(email = %s) not found!", email));
        }

        return userList.get(0);
    }

    @Override
    public User getById(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
