package com.lucky.lots.dao;

import com.lucky.lots.entity.UserInfoParam;
import com.lucky.lots.entity.WxUserInfo;

public interface UserDao {
    WxUserInfo addUser(String openId, String sessionKey);
    WxUserInfo updateUser(long id,UserInfoParam userInfo);
    WxUserInfo getUser(long id);
}
