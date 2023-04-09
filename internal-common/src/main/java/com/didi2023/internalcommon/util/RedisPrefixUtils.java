package com.didi2023.internalcommon.util;

public class RedisPrefixUtils {


    // 乘客验证码的前缀
    public static String verificationCodePrefix = "verification-code-";


    public static String tokenTypePassenger = "passenger";

    public static String tokenTypeDriver = "driver";
    public static String tokenPrefix = "token-";

    //生成 redis 验证码 key

    /**
     * 生成redis key
     *
     * @param phone
     * @param identify IdentifyConstant
     * @return
     */
    public static String generateKeyByPhone(String phone, String identify) {

        return verificationCodePrefix + identify + "-" + phone;
    }


    // 生成redis token key
    public static String generateTokenKey(String phone, String identify, String tokenType) {

        return tokenPrefix + phone + "-" + identify + "-" + tokenType;

    }
}
