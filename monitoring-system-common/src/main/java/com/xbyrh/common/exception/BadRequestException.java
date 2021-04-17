package com.xbyrh.common.exception;

import com.xbyrh.common.enums.ResponseEnum;

/**
 * create at 2021/4/16
 *
 * @author chenxinhui
 */
public class BadRequestException extends BaseException{

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public ResponseEnum responseEnum() {
        return ResponseEnum.BAD_REQUEST;
    }
}
