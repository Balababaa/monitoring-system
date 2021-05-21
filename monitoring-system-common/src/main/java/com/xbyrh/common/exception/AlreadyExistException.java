package com.xbyrh.common.exception;

import com.xbyrh.common.enums.ResponseEnum;

/**
 * create at 2021/4/14
 *
 * @author chenxinhui
 */
public class AlreadyExistException extends BaseException{

    public AlreadyExistException(String message) {
        super(message);
    }

    public AlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public ResponseEnum responseEnum() {
        return ResponseEnum.NOT_FOUND;
    }


}
