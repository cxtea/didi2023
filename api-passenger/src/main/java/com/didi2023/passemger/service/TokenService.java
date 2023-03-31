package com.didi2023.passemger.service;

import com.didi2023.internalcommon.constant.CommonStatusEnum;
import com.didi2023.internalcommon.constant.TokenConstants;
import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.dto.TokenResult;
import com.didi2023.internalcommon.response.TokenResponse;
import com.didi2023.internalcommon.util.JWTUtils;
import com.didi2023.internalcommon.util.RedisPrefixUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    public ResponseResult refreshToken(String refreshTokenSrc){

        //解析 refreshToken
        TokenResult tokenResult = JWTUtils.checkToken(refreshTokenSrc);
        //读取redis 中的 refreshToken
        String refreshTokenKey = RedisPrefixUtils.generateTokenKey(tokenResult.getPhone(),tokenResult.getIdentify(), TokenConstants.REFRESH_TOKEN_TYPE);
        String accessTokenKey = RedisPrefixUtils.generateTokenKey(tokenResult.getPhone(),tokenResult.getIdentify(), TokenConstants.ACCESS_TOKEN_TYPE);
        String redisRefreshToken = stringRedisTemplate.opsForValue().get(refreshTokenKey);
        //验证 token
        if(StringUtils.isBlank(redisRefreshToken) || !refreshTokenSrc.trim().equals(redisRefreshToken)){
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        // 生成新的token
        String accessToken = JWTUtils.generateToken(tokenResult.getPhone(),tokenResult.getIdentify(),TokenConstants.ACCESS_TOKEN_TYPE);
        String refreshToken = JWTUtils.generateToken(tokenResult.getPhone(),tokenResult.getIdentify(),TokenConstants.REFRESH_TOKEN_TYPE);

        stringRedisTemplate.opsForValue().set(accessTokenKey,accessToken,30, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey,refreshToken,31,TimeUnit.DAYS);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setRefreshToken(refreshToken);
        tokenResponse.setAccessToken(accessToken);

        return ResponseResult.success(tokenResponse);
    }
}
