package com.didi2023.passemger.service;

import cn.hutool.json.JSONObject;
import com.didi2023.internalcommon.constant.dto.ResponseResult;
import com.didi2023.internalcommon.constant.response.NumberCodeResponse;
import com.didi2023.internalcommon.constant.response.TokenResponse;
import com.didi2023.passemger.remote.ServiceVerificationcodeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerificationCodeService {

    @Autowired
    private ServiceVerificationcodeClient serviceVerificationcodeClient;

    // 乘客验证码的前缀
    private String verificationCodePrefix = "passenger-verfication-code-";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult generatorCode(String passengerPhone) {

        //调用验证码服务 生成验证码
        ResponseResult<NumberCodeResponse> numberCodeResponseResponse = serviceVerificationcodeClient.getNumberCode(6);
        int numberCode = numberCodeResponseResponse.getData().getNumberCode();

        //调用redis 服务将验证码缓存到redis
        String key = verificationCodePrefix + passengerPhone;
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

        // 根据手机号 去redis 读取验证码 TODO

        // 校验验证码 TODO

        //判断原来是否有用户,并进行对应处理 TODO

        //颁发令牌 TODO
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("token value");
        return ResponseResult.success(tokenResponse);
    }
}
