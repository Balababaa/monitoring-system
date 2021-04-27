package com.xbyrh.web.controller;

import com.alibaba.fastjson.JSON;
import com.xbyrh.common.annotations.NoAuth;
import com.xbyrh.repo.model.bo.UserUpdateBO;
import com.xbyrh.repo.model.mapper.UserConverter;
import com.xbyrh.repo.model.params.UserUpdateParam;
import com.xbyrh.repo.model.vo.UserVO;
import com.xbyrh.service.context.AuthContext;
import com.xbyrh.common.utils.HttpUtil;
import com.xbyrh.repo.entity.User;
import com.xbyrh.service.IUserService;
import com.xbyrh.web.model.dto.OpenIdDTO;
import com.xbyrh.web.model.params.OpenIdParam;
import com.xbyrh.web.properties.OpenIdProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 2021/4/12.
 *
 * @author chenxinhui
 */

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private OpenIdProperties openIdProperties;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private IUserService userService;

    @NoAuth
    @PostMapping("authorize")
    public OpenIdDTO getOpenId(@RequestBody OpenIdParam openIdParam) {
        String url = String.format(openIdProperties.getUrl(), openIdProperties.getAppId(), openIdProperties.getSecret(), openIdParam.getCode());
        String response = HttpUtil.get(url);

        return JSON.parseObject(response, OpenIdDTO.class);
    }

    @GetMapping("detail")
    public UserVO detail(){
        return userConverter.toVO(AuthContext.getUser());
    }

    @PostMapping("modify")
    public UserVO modifyUserInfo(@RequestBody UserUpdateParam userUpdateParam){
        UserUpdateBO userUpdateBO = userConverter.toBO(userUpdateParam);
        User user = userService.update(userUpdateBO);
        return userConverter.toVO(AuthContext.getUser());
    }

}
