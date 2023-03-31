package com.didi2023.passemger.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.didi2023.internalcommon.constant.TokenConstants;
import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.dto.TokenResult;
import com.didi2023.internalcommon.util.JWTUtils;
import com.didi2023.internalcommon.util.RedisPrefixUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class JWTInterceptor implements HandlerInterceptor {


    @Autowired
    private StringRedisTemplate stringRedisTemplate ;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean result = true;
        String resultString = "";

        String token = request.getHeader("Authorization");

        TokenResult tokenResult = JWTUtils.checkToken(token);


         if(tokenResult != null){
             String accessTokenKey = RedisPrefixUtils.generateTokenKey(tokenResult.getPhone(),tokenResult.getIdentify(), TokenConstants.ACCESS_TOKEN_TYPE);

             String redisAccessToken = stringRedisTemplate.opsForValue().get(accessTokenKey);
             if(StringUtils.isBlank(redisAccessToken) || !token.trim().equals(redisAccessToken)){
                 resultString  = "token invalid";
                 result = false;
             }
         }


        if (!result) {
            PrintWriter writer = response.getWriter();
            writer.print(JSONObject.fromObject(ResponseResult.fail(resultString)));
        }

        return result;
    }


}
