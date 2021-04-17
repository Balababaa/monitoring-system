package com.xbyrh.common.enums;

/**
 * created on 2021/4/14.
 *
 * @author chenxinhui
 */

public enum ResponseEnum implements IEnum<Integer,String>{

    SUCCESS(0, "success"),
    NOT_FOUND(1001, "not_found"),
    BAD_REQUEST(1002, "bad_request");

    private Integer code;

    private String desc;

    ResponseEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
