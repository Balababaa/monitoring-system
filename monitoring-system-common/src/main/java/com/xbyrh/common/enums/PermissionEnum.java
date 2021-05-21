package com.xbyrh.common.enums;


/**
 * create at 2021/5/21
 *
 * @author chenxinhui
 */
public enum PermissionEnum implements IEnum<String, String> {
    ADD_DEVICE("ADD_DEVICE", "增加设备"),
    DELETE_DEVICE("DELETE_DEVICE", "删除设备"),
    MODIFY_DEVICE("MODIFY_DEVICE", "修改设备");

    private String code;

    private String desc;

    PermissionEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
