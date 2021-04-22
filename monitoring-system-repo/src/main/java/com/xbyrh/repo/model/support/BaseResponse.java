package com.xbyrh.repo.model.support;

import com.xbyrh.common.enums.ResponseEnum;
import lombok.Data;

/**
 * Created on 2021/4/14.
 *
 * @author chenxinhui
 */
@Data
public class BaseResponse<T> {

    private Integer code;

    private String msg;

    private T data;

    public BaseResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse(ResponseEnum responseEnum, T data) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getDesc();
        this.data = data;
    }

    public BaseResponse() {

    }

    public static <T> BaseResponse<T> ok(T data) {
        return new BaseResponse<>(ResponseEnum.SUCCESS, data);
    }

    public static BaseResponse<String> ok() {
        return new BaseResponse<>(ResponseEnum.SUCCESS, "");
    }
}
