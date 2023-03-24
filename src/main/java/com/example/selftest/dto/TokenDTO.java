package com.example.selftest.dto;

public class TokenDTO {

    private Integer loginId;
    private String loginCode;
    private String loginName;

    public TokenDTO() {
    }

    public TokenDTO(Integer loginId, String loginCode, String loginName) {
        this.loginId = loginId;
        this.loginCode = loginCode;
        this.loginName = loginName;
    }

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Override
    public String toString() {
        return "{'loginId':'" + loginId +
                "', 'loginCode':'" + loginCode +
                "', 'loginName':'" + loginName +
                "'}";
    }
}
