package com.lucky.lots.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenManager {
    private final static int VALIDITY_TIME_MS = 60*60*24*7;
    private final static String SIGNINGKEY="wx56asdf";
    private Map<String,Long> tokenMap=new ConcurrentHashMap<String,Long>();

    private static TokenManager tokenManager = new TokenManager();
    private TokenManager(){

    }
    public static TokenManager getTokenManager(){
        return tokenManager;
    }
    public static String createJWTToken(long userId){
        String token= Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + VALIDITY_TIME_MS))
                .claim("userId",Long.toString(userId))
                .signWith(SignatureAlgorithm.HS256,SIGNINGKEY)
                .compact();
        return token;
    }
    public static long parseJWTToken(String tokenStr){
        long userId = -1;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SIGNINGKEY)
                    .parseClaimsJws(tokenStr)
                    .getBody();
            String strUserId = claims.get("userId",String.class);
            userId=Long.parseLong(strUserId);
        }catch (Exception e){
            userId = -1;
        }
        return userId;
    }
    private boolean isValidTokenInner(String token){
        if(token==null || token.isEmpty()){
            return false;
        }
        long userId = parseJWTToken(token);
        Long obj = tokenMap.get(token);
        long mapUserId=obj!=null?tokenMap.get(token):-1;
        return (mapUserId!=-1) && (userId == mapUserId);
    }
    private String genTokenInner(long userId){
        String token = createJWTToken(userId);
        tokenMap.put(token,userId);
        return token;
    }
    public static String genToken(long userId){
        return getTokenManager().genTokenInner(userId);
    }
    public static boolean isValidToken(String token){
        return getTokenManager().isValidTokenInner(token);
    }
}
