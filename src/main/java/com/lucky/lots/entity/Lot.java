package com.lucky.lots.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "抓阄信息")
public class Lot {
    @ApiModelProperty(value = "ID",required = true)
    public Long id;
    @ApiModelProperty(value = "创建者openId",required = true)
    public String creatorOpenId;
    @ApiModelProperty(value = "抓阄的主题",required = true)
    public String title;
    @ApiModelProperty(value = "参与的总人数",required = true)
    public int totalNum;
    @ApiModelProperty(value = "选中的人数",required = true)
    public int requiredCheckNum;
    @ApiModelProperty(value = "最后更新时间")
    public long updateTime;
    @ApiModelProperty(value = "已抓阄的用户列表")
    public List<LotUserRespBean> lotsUserList;

    public Lot(Long id, String creatorOpenId, String title, int totalNum, int requiredCheckNum) {
        this.id = id;
        this.creatorOpenId = creatorOpenId;
        this.title = title;
        this.totalNum = totalNum;
        this.requiredCheckNum = requiredCheckNum;
    }

    public void setLotsUserList(List<LotUserRespBean> lotsUserList) {
        this.lotsUserList = lotsUserList;
    }
}
