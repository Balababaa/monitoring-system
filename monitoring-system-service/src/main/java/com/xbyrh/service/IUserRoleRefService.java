package com.xbyrh.service;

import com.xbyrh.repo.entity.UserRoleRef;

import java.util.List;

/**
 * create at 2021/4/14
 *
 * @author chenxinhui
 */
public interface IUserRoleRefService {

    void addUserRoleRef(Long uid, List<String> codeList);

    List<UserRoleRef> findByUid(Long uid);
}
