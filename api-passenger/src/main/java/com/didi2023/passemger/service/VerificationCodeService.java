package com.didi2023.passemger.service;

import cn.hutool.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeService {

    public String generatorCode(String passengerPhone){

        //调用验证码服务 生成验证码 TODO
        System.out.println("调用验证码服务 生成验证码");

        //调用redis 服务将验证码缓存到redis TODO
        System.out.println("调用redis 服务将验证码缓存到");
        JSONObject result = new JSONObject();
        result.set("code","1");
        result.set("message","success");

        return result.toString();
    }

}
