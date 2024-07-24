package com.swpu.hotelserver.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class JwtComponet {
    public String sign(String username, String password) {
        // 生成签名算法
        Algorithm alg=Algorithm.HMAC256(username+password);
        //生成过期时间
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.SECOND,3*60*60);
        Date date=cal.getTime();

        return JWT.create().withClaim("username",username).withExpiresAt(date).sign(alg);
    }
    //获取过期时间
    public Date getExpireAt(String token){
        return JWT.decode(token).getExpiresAt();
    }
    public String getUsername(String token){
        return JWT.decode(token).getClaim("username").asString();
    }
    public String getPassword(String token){
        return JWT.decode(token).getClaim("password").asString();
    }
    //验证token
    public boolean verify(String token,String username,String password){
        Algorithm alg=Algorithm.HMAC256(username+password);
        JWTVerifier verifier=JWT.require(alg).withClaim("username",username).build();
        try {
            verifier.verify(token);
            return true;
        }catch (JWTVerificationException e){
            e.printStackTrace();
            return false;
        }

    }
}
