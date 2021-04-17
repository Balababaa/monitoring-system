package com.xbyrh.common.exception;

import com.xbyrh.common.enums.ResponseEnum;

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

    @Override
    public ResponseEnum responseEnum() {
        return ResponseEnum.NOT_FOUND;
    }


}
