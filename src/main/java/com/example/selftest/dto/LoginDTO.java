package com.example.selftest.dto;

import org.springframework.stereotype.Component;
@Component
public class LoginDTO {

    private String loginCode;
    private String loginPwd;
    private String randomUuid;
    private String authCode;

    public LoginDTO() {
    }

    public LoginDTO(String loginCode, String loginPwd, String randomUuid, String authCode) {
        this.loginCode = loginCode;
        this.loginPwd = loginPwd;
        this.randomUuid = randomUuid;
        this.authCode = authCode;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getRandomUuid() {
        return randomUuid;
    }

    public void setRandomUuid(String randomUuid) {
        this.randomUuid = randomUuid;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
}
