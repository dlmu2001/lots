package com.lucky.lots.dao;

import com.lucky.lots.entity.Lot;
import com.lucky.lots.entity.LotEasyUserInfoBean;
import com.lucky.lots.entity.UserInfoParam;
import com.lucky.lots.entity.WxUserInfo;

import java.util.List;

public interface LotsDao {
    Lot creatLot(long userId,String title,int totalNum,int checkedNum);
    List<Lot> getLotList(String openId);
    List<Lot> getCreatLotList(String openId);
    List<LotEasyUserInfoBean> getUserListByLot(long lotId);
    Lot getLotById(long id);
    Lot drawLot(long id, long userId, UserInfoParam userInfoParam);
    boolean deleteLot(long id);
}
