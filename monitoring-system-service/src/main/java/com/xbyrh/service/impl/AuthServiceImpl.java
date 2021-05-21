package com.xbyrh.service.impl;

import cn.hutool.core.lang.Validator;
import com.alibaba.fastjson.JSON;
import com.xbyrh.common.constant.Constant;
import com.xbyrh.common.constant.RedisKeyConstant;
import com.xbyrh.common.enums.PermissionTypeEnum;
import com.xbyrh.common.exception.BadRequestException;
import com.xbyrh.common.exception.NotFoundException;
import com.xbyrh.common.utils.HaloUtils;
import com.xbyrh.repo.entity.Permission;
import com.xbyrh.repo.entity.RolePermissionRef;
import com.xbyrh.repo.entity.User;
import com.xbyrh.repo.entity.UserRoleRef;
import com.xbyrh.repo.model.bo.AuthTokenBO;
import com.xbyrh.repo.model.vo.MenuVO;
import com.xbyrh.service.*;
import com.xbyrh.service.context.AuthContext;
import com.xbyrh.service.properties.AccessTokenProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * create at 2021/4/16
 *
 * @author chenxinhui
 */
@Slf4j
@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private IUserService userService;

    @Autowired
    private AccessTokenProperties accessTokenProperties;

    @Autowired
    private IRolePermissionRefService rolePermissionRefService;

    @Autowired
    private IUserRoleRefService userRoleRefService;

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IRedisService redisService;

    @Override
    public AuthTokenBO auth(String username, String password) {
        User user = authPassword(username, password);

        return buildAuthToken(user);
    }

    private List<Permission> getPermission(Long uid){
        List<UserRoleRef> userRoleRefList = userRoleRefService.findByUid(uid);

        if (CollectionUtils.isEmpty(userRoleRefList)) {
            return new ArrayList<>();
        }

        List<Long> roleIdList = userRoleRefList.stream().map(UserRoleRef::getRoleId).collect(Collectors.toList());

        List<RolePermissionRef> rolePermissionRefList = rolePermissionRefService.findByRoleIdList(roleIdList);

        if (CollectionUtils.isEmpty(rolePermissionRefList)) {
            return new ArrayList<>();
        }

        List<Long> permissionIdList = rolePermissionRefList.stream().map(RolePermissionRef::getPermissionId).collect(
                Collectors.toList());

        List<Permission> permissionList = permissionService.findByIdList(permissionIdList);

        if (CollectionUtils.isEmpty(rolePermissionRefList)) {
            return new ArrayList<>();
        }

        return permissionList;
    }


    @Override
    public List<MenuVO> menu() {
        User user = AuthContext.getUser();
        List<Permission> permissionList = getPermission(user.getUid());
        return permissionList.stream().filter(
                x -> x.getType().equals(PermissionTypeEnum.MENU.getCode())).map(
                x -> JSON.parseObject(x.getPath(), MenuVO.class)).collect(
                Collectors.toList());
    }

    @Override
    public List<String> getPermissionCodeList(Long uid) {
        List<Permission> permissionList = getPermission(uid);

        return permissionList.stream().map(Permission::getCode).collect(Collectors.toList());
    }

    private AuthTokenBO buildAuthToken(User user) {

        AuthTokenBO token = new AuthTokenBO();

        token.setAccessToken(HaloUtils.randomUUIDWithoutDash());
        token.setExpiredIn(accessTokenProperties.getExpireTime());
        token.setRefreshToken(HaloUtils.randomUUIDWithoutDash());
        token.setUser(user);

        // Cache those tokens, just for clearing
//        cacheStore.putAny(SecurityUtils.buildAccessTokenKey(user), token.getAccessToken(),
//                ACCESS_TOKEN_EXPIRED_SECONDS, TimeUnit.SECONDS);
//        cacheStore.putAny(SecurityUtils.buildRefreshTokenKey(user), token.getRefreshToken(),
//                REFRESH_TOKEN_EXPIRED_DAYS, TimeUnit.DAYS);

        // Cache those tokens with user id
        redisService.set(RedisKeyConstant.ACCESS_TOKEN_CACHE_PREFIX + token.getAccessToken(), user.getId().toString(),
                         Constant.ACCESS_TOKEN_EXPIRED_SECONDS, TimeUnit.SECONDS);
        redisService.set(RedisKeyConstant.REFRESH_TOKEN_CACHE_PREFIX + token.getRefreshToken(), user.getId().toString(),
                         Constant.REFRESH_TOKEN_EXPIRED_DAYS, TimeUnit.SECONDS);

        return token;
    }

    private User authPassword(String username, String password) {
        String mismatchTip = "用户名或者密码不正确";

        User user;

        try {
            user = Validator.isEmail(username) ? userService.getByEmail(username) : userService.getByUsername(username);
        } catch (NotFoundException notFoundException) {
            log.info("user not exist, username [{}], password [{}]", username, password);
            throw new BadRequestException(mismatchTip);
        }

        if (!passwordMatch(user, password)) {
            throw new BadRequestException(mismatchTip);
        }

        return user;
    }

    private boolean passwordMatch(User user, String password) {
        return user.getPassword() != null && (user.getPassword().equals(password));
    }


}
