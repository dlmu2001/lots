package com.lucky.lots.entity;

public class LotEasyUserInfoBean {
    private String nickName;
    private String avatarUrl;
    private int lotNo;
    private long userId;

    public LotEasyUserInfoBean(String nickName, String avatarUrl, int lotNo, long userId) {
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
        this.lotNo = lotNo;
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public int getLotNo() {
        return lotNo;
    }


    public long getUserId() {
        return userId;
    }
}
