package com.example.selftest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class RubbishDTO {
    private Integer rubbishId;
    private String loginCode;
    private String rubbishName;
    private String rubbishImage;
    private String rubbishType;
    private String telePhone;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;
    public RubbishDTO() {
    }
    public RubbishDTO(Integer rubbishId, String loginCode, String rubbishName, String rubbishImage, String rubbishType, String telePhone, Date gmtCreate) {
        this.rubbishId = rubbishId;
        this.loginCode = loginCode;
        this.rubbishName = rubbishName;
        this.rubbishImage = rubbishImage;
        this.rubbishType = rubbishType;
        this.telePhone = telePhone;
        this.gmtCreate = gmtCreate;
    }
    public Integer getRubbishId() {
        return rubbishId;
    }
    public void setRubbishId(Integer rubbishId) {
        this.rubbishId = rubbishId;
    }
    public String getLoginCode() {
        return loginCode;
    }
    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }
    public String getRubbishName() {
        return rubbishName;
    }
    public void setRubbishName(String rubbishName) {
        this.rubbishName = rubbishName;
    }
    public String getRubbishImage() {
        return rubbishImage;
    }
    public void setRubbishImage(String rubbishImage) {
        this.rubbishImage = rubbishImage;
    }
    public String getRubbishType() {
        return rubbishType;
    }
    public void setRubbishType(String rubbishType) {
        this.rubbishType = rubbishType;
    }
    public String getTelePhone() {
        return telePhone;
    }
    public void setTelePhone(String telephone) {
        this.telePhone = telephone;
    }
    public Date getGmtCreate() {
        return gmtCreate;
    }
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

}
