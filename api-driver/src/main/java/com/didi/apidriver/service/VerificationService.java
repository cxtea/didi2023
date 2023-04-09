package com.didi.apidriver.service;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.didi.apidriver.remote.ServiceDriverUserClient;
import com.didi.apidriver.remote.ServiceVerificationCodeClient;
import com.didi2023.internalcommon.constant.CommonStatusEnum;
import com.didi2023.internalcommon.constant.DriverCarConstants;
import com.didi2023.internalcommon.constant.IdentifyConstant;
import com.didi2023.internalcommon.constant.TokenConstants;
import com.didi2023.internalcommon.dto.DriverUser;
import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.response.DriverUserResponse;
import com.didi2023.internalcommon.response.TokenResponse;
import com.didi2023.internalcommon.util.JWTUtils;
import com.didi2023.internalcommon.util.RedisPrefixUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class VerificationService {


    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;
    @Autowired
    private ServiceVerificationCodeClient verificationCodeClient;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public ResponseResult checkAndSendVerificationCode(String driverPhone) {


        DriverUser driverUser = new DriverUser();
        driverUser.setDriverPhone(driverPhone);
        //根据手机号查询司机用户是否存在
        DriverUserResponse res = serviceDriverUserClient.getUser(driverUser).getData();

        if (res.getIfExist() == DriverCarConstants.DRIVER_EXIST) {
            //如果存在，生成验证码，发送验证码
            int numberCode = verificationCodeClient.numberCode(6).getData().getNumberCode();
            log.info("numberCode: {}", numberCode);
            //TODO: 发送验证码

            // 保存验证码到redis
            String key = RedisPrefixUtils.generateKeyByPhone(driverPhone, IdentifyConstant.DRIVER_IDENTITY);
            redisTemplate.opsForValue().set(key, String.valueOf(numberCode), 2, TimeUnit.MINUTES);

        } else {
            //如果不存在,注册用户，生成验证码，发送验证码

            System.out.println("not exist");
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXISTS.getCode(), CommonStatusEnum.DRIVER_NOT_EXISTS.getValue());
        }

        return ResponseResult.success("verification code send success");
    }

    public ResponseResult checkVerificationCode(String driverPhone, String verificationCode) {
        //生成key
        String key = RedisPrefixUtils.generateKeyByPhone(driverPhone, IdentifyConstant.DRIVER_IDENTITY);
        //根据key获取验证码
        String codeRedis = redisTemplate.opsForValue().get(key);
        System.out.println("获取到redis中的验证码:" + codeRedis);
        // 校验验证码
        if (StringUtils.isBlank(codeRedis)) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if (!verificationCode.trim().equals(codeRedis.trim())) {
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }

        //颁发令牌
        String accessToken = JWTUtils.generateToken(driverPhone, IdentifyConstant.DRIVER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        String refreshToken = JWTUtils.generateToken(driverPhone, IdentifyConstant.DRIVER_IDENTITY, TokenConstants.REFRESH_TOKEN_TYPE);


        //将token 存到redis中
        String accessTokenKey = RedisPrefixUtils.generateTokenKey(driverPhone, IdentifyConstant.DRIVER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        redisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);

        String refreshTokenKey = RedisPrefixUtils.generateTokenKey(driverPhone, IdentifyConstant.DRIVER_IDENTITY, TokenConstants.REFRESH_TOKEN_TYPE);
        redisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 31, TimeUnit.DAYS);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);

        return ResponseResult.success(tokenResponse);

    }
}
