package com.didi2023.internalcommon.constant.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.didi2023.internalcommon.constant.dto.TokenResult;

import java.util.Calendar;
import java.util.HashMap;

public class JWTUtils {

    private static final String SIGN = "1234%^&";

    private static final String JWT_KEY_PHONE = "phone";
    private static final String JWT_KEY_IDENTIFY = "identify";

    // 生成token
    public static String generateToken(String phone, String identify) {
        HashMap<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE, phone);
        map.put(JWT_KEY_IDENTIFY, identify);


        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DATE,1);

        String token = JWT.create()
                .withClaim(JWT_KEY_PHONE, phone)
                .withClaim(JWT_KEY_IDENTIFY, identify)
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(SIGN));


        return token;
    }

    // 解析token
    public static TokenResult parseToken(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);

        String phone = verify.getClaim(JWT_KEY_PHONE).toString();
        String identify = verify.getClaim(JWT_KEY_IDENTIFY).toString();

        TokenResult tokenResult = new TokenResult();
        tokenResult.setIdentify(identify);
        tokenResult.setPhone(phone);

        return tokenResult;

    }

    public static void main(String[] args) {
        String token = generateToken("18623654177", "1");

        System.out.println(token);
    }

}

