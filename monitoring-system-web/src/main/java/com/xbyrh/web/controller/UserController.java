package com.xbyrh.web.controller;

import com.alibaba.fastjson.JSON;
import com.xbyrh.common.annotations.NoAuth;
import com.xbyrh.service.context.AuthContext;
import com.xbyrh.common.utils.HttpUtil;
import com.xbyrh.repo.entity.User;
import com.xbyrh.service.IUserService;
import com.xbyrh.web.model.dto.OpenIdDTO;
import com.xbyrh.web.model.dto.UserDTO;
import com.xbyrh.web.model.mapper.UserMapper;
import com.xbyrh.web.model.params.OpenIdParam;
import com.xbyrh.web.model.params.UserInfoParam;
import com.xbyrh.repo.model.support.BaseResponse;
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
    private UserMapper userMapper;

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
    public UserDTO detail(){
        return userMapper.toDTO(AuthContext.getUser());
    }

    @NoAuth
    @PostMapping("modify")
    public BaseResponse<String> modifyUserInfo(@RequestBody UserInfoParam userInfoParam){
        User user = userMapper.fromParam(userInfoParam);

        return BaseResponse.ok("");
    }

}
