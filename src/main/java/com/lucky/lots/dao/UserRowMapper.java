package com.lucky.lots.dao;

import com.lucky.lots.entity.WxUserInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<WxUserInfo> {
    @Override
    public WxUserInfo mapRow(ResultSet resultSet, int i) throws SQLException {
        Long id = resultSet.getLong("id");
        String openId = resultSet.getString("wx_open_id");
        String sessionKey = resultSet.getString("wx_session_key");
        WxUserInfo userInfo = new WxUserInfo(id,openId,sessionKey);
        userInfo.setAvatarUrl(resultSet.getString("avatar_url"))
                .setCity(resultSet.getString("city"))
                .setCountry(resultSet.getString("country"))
                .setGender(resultSet.getInt("gender"))
                .setNickName(resultSet.getString("nick_name"))
                .setProvince(resultSet.getString("province"));
        return userInfo;
    }
}
