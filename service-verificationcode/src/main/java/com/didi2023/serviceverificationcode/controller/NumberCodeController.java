package com.didi2023.serviceverificationcode.controller;

import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.response.NumberCodeResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberCodeController {


    @RequestMapping("/numberCode/{size}")
    public ResponseResult<NumberCodeResponse> numberCode(@PathVariable("size") int size) {

        // 生成验证码

        double mathRandom = (Math.random() * 9 + 1) * Math.pow(10, size - 1);
        int code = (int) mathRandom;
        NumberCodeResponse response = new NumberCodeResponse();
        response.setNumberCode(code);

        return ResponseResult.success(response);
    }


}
