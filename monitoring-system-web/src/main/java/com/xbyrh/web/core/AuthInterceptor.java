package com.xbyrh.web.core;

import com.xbyrh.common.annotations.NoOpenid;
import com.xbyrh.common.context.AuthContext;
import com.xbyrh.repo.entity.User;
import com.xbyrh.service.IUserService;
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

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if (handlerMethod.hasMethodAnnotation(NoOpenid.class)) {
            return true;
        }


        String openid = request.getHeader("openid");

        if (openid == null) {
            return false;
        }

        User user = userService.getUserByOpenid(openid);

        AuthContext.setUser(user);

        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AuthContext.clear();
    }
}
