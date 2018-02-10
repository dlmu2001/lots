package com.lucky.lots.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "抓阄中每个参与者的信息")
public class LotUserRespBean {
    @ApiModelProperty(value = "昵称")
    public String nickName;
    @ApiModelProperty(value = "头像url")
    public String avatarUrl;
    @ApiModelProperty(value = "是否选中")
    public boolean checked;

    private Long userId;

    public LotUserRespBean(String nickName, String avatarUrl, boolean checked, Long userId) {
        this.nickName = nickName;
        this.avatarUrl = avatarUrl;
        this.checked = checked;
        this.userId = userId;
    }

    public LotUserRespBean setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public LotUserRespBean setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public LotUserRespBean setChecked(boolean checked) {
        this.checked = checked;
        return this;
    }



    public String getNickName() {
        return nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public boolean isChecked() {
        return checked;
    }

    public Long getUserId() {
        return userId;
    }

}
