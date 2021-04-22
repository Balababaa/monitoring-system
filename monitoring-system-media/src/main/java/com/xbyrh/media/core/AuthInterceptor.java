package com.xbyrh.media.core;

import com.xbyrh.common.annotations.NoAuth;
import com.xbyrh.common.constant.RedisKeyConstant;
import com.xbyrh.repo.entity.User;
import com.xbyrh.service.IRedisService;
import com.xbyrh.service.IUserService;
import com.xbyrh.service.context.AuthContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * create at 2021/4/14
 *
 * @author chenxinhui
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if (handlerMethod.hasMethodAnnotation(NoAuth.class)) {
            return true;
        }

        String loginToken = request.getHeader("login_token");

        if (loginToken == null) {
            return false;
        }

        String value = redisService.get(RedisKeyConstant.ACCESS_TOKEN_CACHE_PREFIX + loginToken);

        if (value == null) {
            return false;
        }

        Long userId = Long.valueOf(value);

        User user = userService.getById(userId);

        AuthContext.setUser(user);

        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AuthContext.clear();
    }
}
