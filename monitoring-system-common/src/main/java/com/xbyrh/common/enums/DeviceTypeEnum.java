package com.xbyrh.common.enums;

/**
 * create at 2021/4/22
 *
 * @author chenxinhui
 */
public enum DeviceTypeEnum implements IEnum<Integer,String>{
    KITCHEN(1,"厨房"),
    PARLOR(2, "客厅"),
    BEDROOM(3,"卧室")

    ;

    private Integer code;

    private String desc;

    DeviceTypeEnum(Integer code, String desc) {
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

    public static DeviceTypeEnum getByCode(Integer code){
        for (DeviceTypeEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }

        throw new IllegalArgumentException("未知的枚举类型");
    }
}
