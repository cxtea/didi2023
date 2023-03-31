package com.didi2023.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.didi2023.internalcommon.dto.TokenResult;

import java.util.HashMap;

public class JWTUtils {

    //加密salt
    private static final String SIGN = "1234%^&";

    private static final String JWT_KEY_PHONE = "phone";
    private static final String JWT_KEY_IDENTIFY = "identify";

    private static final String JWT_TOKEN_TYPE = "tokenType";

    // 生成token
    public static String generateToken(String phone, String identify, String tokenType) {

        HashMap<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE, phone);
        map.put(JWT_KEY_IDENTIFY, identify);
        //双 token
        map.put(JWT_TOKEN_TYPE, tokenType);

        //token 添加过期时间
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DATE,1);

        String token = JWT.create()
                .withClaim(JWT_KEY_PHONE, phone)
                .withClaim(JWT_KEY_IDENTIFY, identify)
                .withClaim(JWT_TOKEN_TYPE, tokenType)
                //过期时间
//                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(SIGN));


        return token;
    }

    // 解析token
    public static TokenResult parseToken(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);

        String phone = verify.getClaim(JWT_KEY_PHONE).asString();
        String identify = verify.getClaim(JWT_KEY_IDENTIFY).asString();

        TokenResult tokenResult = new TokenResult();
        tokenResult.setIdentify(identify);
        tokenResult.setPhone(phone);

        return tokenResult;

    }

    public static TokenResult checkToken(String token) {

        TokenResult tokenresult = null;
        try {
            tokenresult = JWTUtils.parseToken(token);
        } catch (Exception e) {

        }
        return tokenresult;
    }

    public static void main(String[] args) {
//        String token = generateToken("18623654177", "1");

//        System.out.println(token);
    }

}

