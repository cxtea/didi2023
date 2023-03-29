package com.didi2023.passemger.service;

import cn.hutool.json.JSONObject;
import com.didi2023.internalcommon.constant.dto.ResponseResult;
import com.didi2023.internalcommon.constant.response.NumberCodeResponse;
import com.didi2023.passemger.remote.ServiceVerificationcodeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeService {

    @Autowired
    private ServiceVerificationcodeClient serviceVerificationcodeClient;

    public String generatorCode(String passengerPhone) {

        //调用验证码服务 生成验证码 TODO
        System.out.println("调用验证码服务 生成验证码");
        ResponseResult<NumberCodeResponse> numberCodeResponseResponse = serviceVerificationcodeClient.getNumberCode(6);
        int numberCode = numberCodeResponseResponse.getData().getNumberCode();

        System.out.println("remote number code :" + numberCode);
        //调用redis 服务将验证码缓存到redis TODO
        System.out.println("调用redis 服务将验证码缓存到");
        JSONObject result = new JSONObject();
        result.set("code", "1");
        result.set("message", "success");

        return result.toString();
    }

}
