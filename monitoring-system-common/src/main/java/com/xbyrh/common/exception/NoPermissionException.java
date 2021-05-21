package com.xbyrh.common.exception;

import com.xbyrh.common.enums.ResponseEnum;

/**
 * create at 2021/4/14
 *
 * @author chenxinhui
 */
public class NoPermissionException extends BaseException{

    public NoPermissionException(String message) {
        super(message);
    }

    public NoPermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public ResponseEnum responseEnum() {
        return ResponseEnum.NOT_FOUND;
    }


}
