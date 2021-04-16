package com.xbyrh.service;

import com.xbyrh.repo.entity.User;

/**
 * create at 2021/4/14
 *
 * @author chenxinhui
 */
public interface IUserService {

    User getUserByOpenid(String openid);

    User modifyUserInfo(User user);

}
