package com.didi2023.passemger.service;

import cn.hutool.json.JSONObject;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.didi2023.internalcommon.constant.CommonStatusEnum;
import com.didi2023.internalcommon.constant.IdentifyConstant;
import com.didi2023.internalcommon.constant.dto.ResponseResult;
import com.didi2023.internalcommon.constant.request.VerificationCodeDTO;
import com.didi2023.internalcommon.constant.response.NumberCodeResponse;
import com.didi2023.internalcommon.constant.response.TokenResponse;
import com.didi2023.internalcommon.constant.util.JWTUtils;
import com.didi2023.passemger.remote.ServicePassengerUserClient;
import com.didi2023.passemger.remote.ServiceVerificationcodeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerificationCodeService {

    @Autowired
    private ServiceVerificationcodeClient serviceVerificationcodeClient;

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    // 乘客验证码的前缀
    private String verificationCodePrefix = "passenger-verfication-code-";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult generatorCode(String passengerPhone) {

        //调用验证码服务 生成验证码
        ResponseResult<NumberCodeResponse> numberCodeResponseResponse = serviceVerificationcodeClient.getNumberCode(6);
        int numberCode = numberCodeResponseResponse.getData().getNumberCode();

        System.out.println("验证码:"+numberCode);
        //调用redis 服务将验证码缓存到redis
        String key = generateKeyByPhone(passengerPhone);
        stringRedisTemplate.opsForValue().set(key, numberCode + "", 2, TimeUnit.MINUTES);

        //通过短信服务商,将对应的验证码发送到手机上 ,阿里短信服务 TODO

        return ResponseResult.success();
    }

    /**
     * 校验验证码
     * @param passengerPhone    手机号
     * @param verificationCode  验证码
     * @return
     */
    public ResponseResult checkCode(String passengerPhone,String verificationCode){

        //生成key
        String key = generateKeyByPhone(passengerPhone);
        //根据key获取验证码
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        System.out.println("获取到redis中的验证码:"+ codeRedis);
        // 校验验证码
        if (StringUtils.isBlank(codeRedis)){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if (!verificationCode.trim().equals(codeRedis.trim())){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        //判断原来是否有用户,并进行对应处理
        VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO();
        verificationCodeDTO.setPassengerPhone(passengerPhone);
        servicePassengerUserClient.loginOrRegister(verificationCodeDTO);

        String token = JWTUtils.generateToken(passengerPhone, IdentifyConstant.PASSENGER_IDENTITY);
        //颁发令牌
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);
        return ResponseResult.success(tokenResponse);
    }

    private String generateKeyByPhone(String phone){
        return verificationCodePrefix + phone;
    }
}
