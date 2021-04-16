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
    public User getUserByOpenid(String openid) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andOpenidEqualTo(openid);
        List<User> userList = userMapper.selectByExample(userExample);

        if (CollectionUtils.isEmpty(userList)) {
            throw new NotFoundException(String.format("openid: %s is not exist", openid));
        }

        return userList.get(0);
    }

    @Override
    public User modifyUserInfo(User user) {
        try {
            User userInDB = getUserByOpenid(user.getOpenid());

            userInDB.setNickName(user.getNickName());
            userInDB.setAvatarUrl(user.getAvatarUrl());

            userMapper.updateByPrimaryKey(userInDB);

            return user;
        } catch (NotFoundException e) {
            //todo
            user.setUid(System.currentTimeMillis() / 100000000);
            userMapper.insertSelective(user);
            return user;
        }

    }
}
