package com.lucky.lots.dao;

public interface LoginDao {
    String saveLoginSession(String openId,String sessionKey,String jwtToken);
}
