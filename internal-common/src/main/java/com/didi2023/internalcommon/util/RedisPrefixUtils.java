package com.didi2023.internalcommon.util;

public class RedisPrefixUtils {


    // 乘客验证码的前缀
    public static String verificationCodePrefix = "passenger-verfication-code-";

    public static String tokenPrefix = "token-";

    //生成 redis 验证码 key
    public static String generateKeyByPhone(String phone) {
        return verificationCodePrefix + phone;
    }


    // 生成redis token key
    public static String generateTokenKey(String phone, String identify,String tokenType) {

        return tokenPrefix + phone + "-" + identify + "-" + tokenType;

    }
}
