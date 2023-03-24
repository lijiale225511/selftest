package com.example.selftest.dto;

public class OpResultDTO {

    private String msgResult;
    private Object objResult;

    private Integer code;

    public OpResultDTO() {
    }

    public OpResultDTO(String msgResult, Object objResult) {
        this.msgResult = msgResult;
        this.objResult = objResult;
    }

    public String getMsgResult() {
        return msgResult;
    }

    public void setMsgResult(String msgResult) {
        this.msgResult = msgResult;
    }

    public Object getObjResult() {
        return objResult;
    }

    public void setObjResult(Object objResult) {
        this.objResult = objResult;
    }
}
