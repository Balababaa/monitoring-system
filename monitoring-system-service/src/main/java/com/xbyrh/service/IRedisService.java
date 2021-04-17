package com.xbyrh.service;

import java.util.concurrent.TimeUnit;

/**
 * create at 2021/4/16
 *
 * @author chenxinhui
 */
public interface IRedisService {

    void set(String key, String value);

    void set(String key, String value, Long time, TimeUnit timeUnit);

    String get(String key);

    void del(String key);

    void expire(String key, Long time, TimeUnit timeUnit);
}
