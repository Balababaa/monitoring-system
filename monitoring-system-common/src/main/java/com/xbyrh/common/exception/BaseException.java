package com.xbyrh.common.exception;

import com.xbyrh.common.enums.ResponseEnum;

/**
 * create at 2021/4/14
 *
 * @author chenxinhui
 */
public abstract class BaseException extends RuntimeException {

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract ResponseEnum responseEnum();

}
