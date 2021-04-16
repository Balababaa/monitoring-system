package com.xbyrh.common.exception;

/**
 * create at 2021/4/14
 *
 * @author chenxinhui
 */
public class NotFoundException extends BaseException{

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
