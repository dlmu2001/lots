package com.lucky.lots.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

public class WxUserInfo {
    private long id;
    private String wxOpenId;
    private String wxSessionKey;
    private String nickName;
    private String avatarUrl;
    private int gender;
    private String province;
    private String city;
    private String country;

    public WxUserInfo(long id, String wxOpenId, String wxSessionKey) {
        this.id = id;
        this.wxOpenId = wxOpenId;
        this.wxSessionKey = wxSessionKey;
    }

    public WxUserInfo setWxSessionKey(String wxSessionKey) {
        this.wxSessionKey = wxSessionKey;
        return this;
    }

    public WxUserInfo setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public WxUserInfo setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public WxUserInfo setGender(int gender) {
        this.gender = gender;
        return this;
    }

    public WxUserInfo setProvince(String province) {
        this.province = province;
        return this;
    }

    public WxUserInfo setCity(String city) {
        this.city = city;
        return this;
    }

    public WxUserInfo setCountry(String country) {
        this.country = country;
        return this;
    }

    public long getId() {
        return id;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public String getWxSessionKey() {
        return wxSessionKey;
    }

    public String getNickName() {
        return nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public int getGender() {
        return gender;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
