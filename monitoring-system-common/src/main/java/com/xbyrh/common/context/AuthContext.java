package com.xbyrh.common.context;


import com.xbyrh.repo.entity.User;

/**
 * create at 2021/4/14
 *
 * @author chenxinhui
 */
public class AuthContext {

    private static final ThreadLocal<User> context = new ThreadLocal<User>();


    public static User getUser(){
        return context.get();
    }

    public static void setUser(User user){
        if (user == null) {
            throw new IllegalArgumentException("user must not be null");
        }

        context.set(user);
    }

    public static void clear(){
        context.remove();
    }

}
