package com.lucky.lots.dao;

import com.lucky.lots.entity.LotEasyUserInfoBean;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EasyLotUserInfoRowMapper implements RowMapper<LotEasyUserInfoBean> {
    @Override
    public LotEasyUserInfoBean mapRow(ResultSet resultSet, int i) throws SQLException {
        long userId = resultSet.getLong("id");
        String nickName=resultSet.getString("nick_name");
        String avatarUrl = resultSet.getString("avatar_url");
        int lotNo = resultSet.getInt("lot_no");

        return new LotEasyUserInfoBean(nickName,avatarUrl,lotNo,userId);
    }
}
