package com.lucky.lots.entity;

public class TokenModel {
    private String openId;
    private String sessionKey;

    public TokenModel(String openId, String sessionKey) {
        this.openId = openId;
        this.sessionKey = sessionKey;
    }

    public String getOpenId() {
        return openId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
