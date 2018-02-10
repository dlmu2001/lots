package com.lucky.lots.dao;

import com.lucky.lots.entity.Lot;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LotRowMapper implements RowMapper<Lot> {
    @Override
    public Lot mapRow(ResultSet resultSet, int i) throws SQLException {
        Long id = resultSet.getLong("id");
        String openId = resultSet.getString("creator_id");
        String title = resultSet.getString("title");
        int totalNum = resultSet.getInt("total_num");
        int checkedNum =resultSet.getInt("required_check_num");

        Lot lot =new Lot(id,openId,title,totalNum,checkedNum);

        return lot;
    }
}
