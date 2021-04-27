package com.xbyrh.service.impl;

import cn.hutool.core.lang.Validator;
import com.xbyrh.common.constant.Constant;
import com.xbyrh.common.constant.RedisKeyConstant;
import com.xbyrh.common.exception.BadRequestException;
import com.xbyrh.common.exception.NotFoundException;
import com.xbyrh.common.utils.HaloUtils;
import com.xbyrh.repo.entity.User;
import com.xbyrh.repo.model.bo.AuthTokenBO;
import com.xbyrh.service.IAuthService;
import com.xbyrh.service.IRedisService;
import com.xbyrh.service.IUserService;
import com.xbyrh.service.properties.AccessTokenProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

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
    private IRedisService redisService;

    @Override
    public AuthTokenBO auth(String username, String password) {
        User user = authPassword(username, password);

        return buildAuthToken(user);
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
