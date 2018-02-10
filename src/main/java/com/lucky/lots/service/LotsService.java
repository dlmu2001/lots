package com.lucky.lots.service;

import com.lucky.lots.common.TokenManager;
import com.lucky.lots.dao.LotsDao;
import com.lucky.lots.entity.Lot;
import com.lucky.lots.entity.UserInfoParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotsService {
    @Autowired
    @Qualifier("mysql")
    LotsDao lotsDao;
    public Lot creatLot(String token, String title, int totalNum, int checkedNum){
        long userId = TokenManager.parseJWTToken(token);
        if(userId==-1){
            return null;
        }
        return lotsDao.creatLot(userId,title,totalNum,checkedNum);
    }
    public List<Lot> getLotList(String openId){
        return lotsDao.getLotList(openId);
    }
    public List<Lot> getCreatLotList(String openId){
        return lotsDao.getCreatLotList(openId);
    }
    public Lot getLotById(long id){
        return lotsDao.getLotById(id);
    }
    public Lot drawLot(long id, long userId, UserInfoParam userInfoParam){
        return lotsDao.drawLot(id,userId,userInfoParam);
    }
    public boolean deleteLot(long id){
        return lotsDao.deleteLot(id);
    }
}
