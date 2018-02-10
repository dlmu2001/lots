package com.lucky.lots.dao;

import com.lucky.lots.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Qualifier("mysql")
public class LotsDaoSqlImpl implements LotsDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Lot creatLot(long userId, String title, int totalNum, int checkedNum) {
        final String sql = "INSERT INTO lot(creator_id,title,total_num,required_check_num) VALUES (?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1,userId);
                ps.setString(2,title);
                ps.setInt(3,totalNum);
                ps.setInt(4,checkedNum);
                return ps;
            }
        },keyHolder);
        long lotId = keyHolder.getKey().longValue();

        return getLotById(lotId);
    }

    @Override
    public List<Lot> getLotList(String openId) {
        List<Lot> lots = jdbcTemplate.query("SELECT * FROM lot WHERE creator=?",
                new Object[]{openId},new LotRowMapper());
        return lots;
    }

    @Override
    public List<Lot> getCreatLotList(String openId) {
        List<Lot> lots = jdbcTemplate.query("SELECT * FROM lot WHERE creator=?",
                new Object[]{openId},new LotRowMapper());
        return lots;
    }

    @Override
    public List<LotEasyUserInfoBean> getUserListByLot(long lotId) {
        List<LotEasyUserInfoBean> userList = jdbcTemplate.query("SELECT user.id,user.nick_name, " +
                "user.avatar_url,user_lot.lot_no FROM user,user_lot " +
                "WHERE user_lot.user_id=user.id AND user_lot.lot_id=?",
                new Object[]{lotId},new EasyLotUserInfoRowMapper());
        return userList;
    }

    @Override
    public Lot getLotById(long id) {
        Lot newLot = jdbcTemplate.queryForObject("SELECT * from lot where id=?",
                new LotRowMapper(), id);
        List<LotEasyUserInfoBean> easyUserInfoBeanList = getUserListByLot(id);
        if(easyUserInfoBeanList!=null && !easyUserInfoBeanList.isEmpty()){
            List<LotUserRespBean> userRespBeanList = new ArrayList<>();
            for(LotEasyUserInfoBean easyUserInfoBean:easyUserInfoBeanList){
                boolean checked =  easyUserInfoBean.getLotNo()<=newLot.requiredCheckNum;
                LotUserRespBean respBean = new LotUserRespBean(easyUserInfoBean.getNickName(),
                        easyUserInfoBean.getAvatarUrl(),checked,easyUserInfoBean.getUserId());
                userRespBeanList.add(respBean);
            }
            newLot.lotsUserList = userRespBeanList;
        }
        return newLot;
    }

    @Override
    public Lot drawLot(long id, long userId, UserInfoParam userInfoParam) {
        Lot lot = getLotById(id);
        int rows=0;
        if(lot!=null) {
            synchronized (this) {
                //generated  non-repeated random no
                String sql  = "SELECT * FROM (SELECT 1+FLOOR(RAND() * "
                        + lot.totalNum + ") As random_num FROM lots.user_lot "
                        + " UNION SELECT 1+FLOOR(RAND() * " + lot.totalNum +") As random_num) AS ss "
                        + "WHERE random_num not in (SELECT lot_no from lots.user_lot WHERE lot_id="+id+") LIMIT 1";
                int lotNo = jdbcTemplate.queryForObject(sql, new RowMapper<Integer>() {
                    @Override
                    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                        return resultSet.getInt("random_num");
                    }
                });
                try {
                    rows = jdbcTemplate.update("INSERT INTO user_lot(lot_id,user_id,lot_no) VALUES(?,?,?)",
                            id, userId, lotNo);
                }catch (Exception e){
                }

            }

        }
        return rows>0?getLotById(id):lot;
    }

    @Override
    public boolean deleteLot(long id) {
        return false;
    }
}
