package com.lucky.lots.service;

import com.lucky.lots.common.TokenManager;
import com.lucky.lots.dao.UserDao;
import com.lucky.lots.entity.TokenModel;
import com.lucky.lots.entity.UserInfoParam;
import com.lucky.lots.entity.WxSessionInfo;
import com.lucky.lots.entity.WxUserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LoginService {
    private final static String JSCODE2SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
    private final static String APPID="wx1236393a2d8f42db";
    private final static String SECRET="60faa5d0f71f52f286b18f1ff9ebdecf";

    @Autowired
    @Qualifier("mysql")
    UserDao userDao;
    class WxHttpMessageConverter extends MappingJackson2HttpMessageConverter{
        public WxHttpMessageConverter() {
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(MediaType.TEXT_PLAIN);
            setSupportedMediaTypes(mediaTypes);
        }
    }


    public String login(String jscode){
        String url= String.format(JSCODE2SESSION_URL,APPID,SECRET,jscode);
        RestTemplate template= new RestTemplate();
        template.getMessageConverters().add(new WxHttpMessageConverter());
        ResponseEntity<WxSessionInfo> responseEntity = template.getForEntity(url,WxSessionInfo.class);
        HttpStatus statusCode = responseEntity.getStatusCode();
        if(statusCode.value()==200){
            WxSessionInfo sessionInfo = responseEntity.getBody();
            if(sessionInfo.errcode==null){
                WxUserInfo userInfo = userDao.addUser(sessionInfo.openid,sessionInfo.session_key);
                if(userInfo!=null){
                    return TokenManager.genToken(userInfo.getId());
                }else {

                }

            }else {

            }
        }else {
            //重试?
        }
        return null;
    }
    public void updateUser(long userId,UserInfoParam userInfoParam){
        userDao.updateUser(userId,userInfoParam);
    }
}
