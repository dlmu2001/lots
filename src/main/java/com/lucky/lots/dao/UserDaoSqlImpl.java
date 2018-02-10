package com.lucky.lots.dao;

import com.lucky.lots.entity.UserInfoParam;
import com.lucky.lots.entity.WxUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
@Qualifier("mysql")
public class UserDaoSqlImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public WxUserInfo addUser(String openId, String sessionKey) {
        final String sql = "INSERT INTO user(wx_open_id,wx_session_key) VALUES (?,?) " +
                "ON DUPLICATE KEY UPDATE wx_session_key=VALUES(wx_session_key),wx_open_id=VALUES(wx_open_id)" ;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, openId);
                    ps.setString(2, sessionKey);
                    return ps;
                }
            }, keyHolder);
        }catch (Exception e){
            int i=0;
        }

        return getUserByOpenId(openId);
    }

    @Override
    public WxUserInfo updateUser(long id, UserInfoParam userInfo) {
        int rows=0;
        try{
            rows=jdbcTemplate.update("update user set nick_name=?,avatar_url=?," +
                            "gender=?,province=?,city=?,country=? where id=?",
                    new PreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps) throws SQLException {
                            ps.setString(1,userInfo.getNickName());
                            ps.setString(2,userInfo.getAvatarUrl());
                            ps.setInt(3,userInfo.getGender());
                            ps.setString(4,userInfo.getProvince());
                            ps.setString(5,userInfo.getCity());
                            ps.setString(6,userInfo.getCountry());
                            ps.setLong(7,id);
                        }
                    });
        }catch (Exception e){
        }
        return rows==0?null:getUser(id);
    }

    @Override
    public WxUserInfo getUser(long id) {
        WxUserInfo userInfo = jdbcTemplate.queryForObject("SELECT * from user where id=?",
                new UserRowMapper(),id);
        return userInfo;
    }
    public WxUserInfo getUserByOpenId(String openId){
        WxUserInfo userInfo = jdbcTemplate.queryForObject("SELECT * from user where wx_open_id=?",
                new UserRowMapper(),openId);
        return userInfo;
    }
}
