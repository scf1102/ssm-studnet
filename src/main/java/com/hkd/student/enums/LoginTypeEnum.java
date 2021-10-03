package com.hkd.student.enums;

import org.springframework.util.StringUtils;

public enum LoginTypeEnum {
    ADMIN("admin"),
    NO("studentNo"),
    PHONE("studentPhone"),
    EMAIL("studentEmail");

    private String type;

    LoginTypeEnum(String type) {
        this.type = type;
    }
    public static LoginTypeEnum getByType(String loginType){
        if (!StringUtils.hasText(loginType)){
            return null;
        }
        for(LoginTypeEnum e : values()){
            if (e.equals(loginType)){
                return e;
            }
        }
        return null;
    }

    public String getType() {
        return type;
    }
}
