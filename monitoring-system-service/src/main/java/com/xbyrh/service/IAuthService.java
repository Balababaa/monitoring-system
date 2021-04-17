package com.xbyrh.service;

import com.xbyrh.repo.model.bo.AuthTokenBO;

/**
 * create at 2021/4/16
 *
 * @author chenxinhui
 */
public interface IAuthService {

    AuthTokenBO auth(String username, String password);

}
