package com.xbyrh.service;

import com.xbyrh.repo.model.bo.AuthTokenBO;
import com.xbyrh.repo.model.vo.MenuVO;

import java.util.List;

/**
 * create at 2021/4/16
 *
 * @author chenxinhui
 */
public interface IAuthService {

    AuthTokenBO auth(String username, String password);

    List<MenuVO> menu();

}
