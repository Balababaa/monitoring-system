package com.xbyrh.service;

import com.xbyrh.repo.entity.User;
import com.xbyrh.repo.model.bo.UserBO;
import com.xbyrh.repo.model.bo.UserSaveBO;
import com.xbyrh.repo.model.bo.UserUpdateBO;

import java.util.List;

/**
 * create at 2021/4/14
 *
 * @author chenxinhui
 */
public interface IUserService {

    User getByUsername(String username);

    User getByEmail(String email);

    User getById(Long userId);

    User update(UserUpdateBO userUpdateBO);

    List<UserBO> listByUsername(String username, Integer page, Integer limit);

    void save(UserSaveBO userSaveBO);

    Long countByUsername(String username);

}
