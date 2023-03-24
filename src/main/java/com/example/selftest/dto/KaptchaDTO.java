package com.example.selftest.dto;

public class KaptchaDTO {
    private String uuid;
    private String image;
    private int expire;

    public KaptchaDTO() {
    }

    public KaptchaDTO(String uuid, String image, int expire) {
        this.uuid = uuid;
        this.image = image;
        this.expire = expire;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }
}
