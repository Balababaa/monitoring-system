package com.xbyrh.common.enums;

/**
 * create at 2021/5/14
 *
 * @author chenxinhui
 */
public enum PermissionTypeEnum implements IEnum<Integer,String>{

    FUNCTION(1,"功能权限"),
    MENU(2, "菜单权限"),
            ;

    private Integer code;

    private String desc;

    PermissionTypeEnum(Integer code, String desc) {
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


    public static PermissionTypeEnum getByCode(Integer code){
        for (PermissionTypeEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }

        throw new IllegalArgumentException("未知的枚举类型");
    }
}
