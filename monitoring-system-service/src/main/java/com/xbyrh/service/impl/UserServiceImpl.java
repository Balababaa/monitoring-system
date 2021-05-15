package com.xbyrh.service.impl;

import com.xbyrh.common.exception.NotFoundException;
import com.xbyrh.repo.entity.Role;
import com.xbyrh.repo.entity.User;
import com.xbyrh.repo.entity.UserExample;
import com.xbyrh.repo.entity.UserRoleRef;
import com.xbyrh.repo.mapper.UserMapper;
import com.xbyrh.repo.model.bo.UserBO;
import com.xbyrh.repo.model.bo.UserSaveBO;
import com.xbyrh.repo.model.bo.UserUpdateBO;
import com.xbyrh.repo.model.mapper.UserConverter;
import com.xbyrh.service.IRoleService;
import com.xbyrh.service.IUserRoleRefService;
import com.xbyrh.service.IUserService;
import com.xbyrh.service.context.AuthContext;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * create at 2021/4/14
 *
 * @author chenxinhui
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private IUserRoleRefService userRoleRefService;

    @Autowired
    private IRoleService roleService;

    @Override
    public User getByUsername(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);

        List<User> userList = userMapper.selectByExample(userExample);

        if (CollectionUtils.isEmpty(userList)) {
            throw new NotFoundException(String.format("user(username = %s) not found!", username));
        }

        return userList.get(0);
    }

    @Override
    public User getByEmail(String email) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andEmailEqualTo(email);

        List<User> userList = userMapper.selectByExample(userExample);

        if (CollectionUtils.isEmpty(userList)) {
            throw new NotFoundException(String.format("user(email = %s) not found!", email));
        }

        return userList.get(0);
    }

    @Override
    public User getById(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User update(UserUpdateBO userUpdateBO) {
        User user = AuthContext.getUser();

        if (StringUtils.isNotBlank(userUpdateBO.getNickname())) {
            user.setNickname(userUpdateBO.getNickname());
        }

        if (StringUtils.isNotBlank(userUpdateBO.getAvatar())) {
            user.setAvatar(userUpdateBO.getAvatar());
        }
        if (StringUtils.isNotBlank(userUpdateBO.getPassword())) {
            user.setPassword(userUpdateBO.getPassword());
        }

        userMapper.updateByPrimaryKey(user);

        AuthContext.setUser(user);

        return user;
    }

    @Override
    public List<UserBO> listByUsername(String username, Integer page, Integer limit) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (StringUtils.isNotBlank(username)) {
            criteria.andUsernameEqualTo(username);
        }

        userExample.setLimit(limit);
        userExample.setOffset((page - 1) * limit);

        List<User> userList = userMapper.selectByExample(userExample);

        List<UserBO> userBOList = userConverter.toBOList(userList);

        for (UserBO userBO : userBOList) {
            List<UserRoleRef> userRoleRefList = userRoleRefService.findByUid(userBO.getUid());

            if (!CollectionUtils.isEmpty(userRoleRefList)) {
                List<Long> roleIdList = userRoleRefList.stream().map(UserRoleRef::getRoleId).collect(
                        Collectors.toList());

                List<Role> roleList = roleService.findByIdList(roleIdList);

                userBO.setRoleList(roleList);
            } else {
                userBO.setRoleList(new ArrayList<>());
            }
        }

        return userBOList;
    }

    @Override
    public void save(UserSaveBO userSaveBO) {
        User user = new User();
        user.setUid(userSaveBO.getUid());
        user.setUsername(userSaveBO.getUsername());
        user.setNickname(userSaveBO.getNickname());
        user.setEmail(userSaveBO.getEmail());
        user.setPassword("123456");
        user.setAvatar("https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png");

        userMapper.insertSelective(user);
    }

    @Override
    public Long countByUsername(String username) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (StringUtils.isNotBlank(username)) {
            criteria.andUsernameEqualTo(username);
        }

        return (long) userMapper.countByExample(userExample);
    }

}
