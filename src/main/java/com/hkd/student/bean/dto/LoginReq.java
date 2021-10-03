package com.hkd.student.bean.dto;

public class LoginReq {

    private String loginName;
    private String secretCode;
    private String loginType;

    public LoginReq() {
    }

    public LoginReq(String loginName, String secretCode, String loginType) {
        this.loginName = loginName;
        this.secretCode = secretCode;
        this.loginType = loginType;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
