package com.ubold.admin.constants;

/**
 * Created by ningzuokun on 2018/3/21.
 */
public enum MenuTypeEnum {
    MENU(1, "菜单"),
    FUNCTION(2, "功能按钮");

    private Integer code;
    private String value;

    MenuTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static MenuTypeEnum getInstance(Integer code) {
        for (MenuTypeEnum e : values()) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
