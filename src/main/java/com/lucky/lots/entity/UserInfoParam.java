package com.lucky.lots.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户信息参数，微信获取的的用户信息，不包含openid这样的敏感信息")
public class UserInfoParam {
    @ApiModelProperty(value = "昵称")
    public String nickName;
    @ApiModelProperty(value = "头像url")
    public String avatarUrl;
    @ApiModelProperty(value = "性别")
    public int gender;
    @ApiModelProperty(value = "省份")
    public String province;
    @ApiModelProperty(value = "城市")
    public String city;
    @ApiModelProperty(value = "国家")
    public String country;

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
