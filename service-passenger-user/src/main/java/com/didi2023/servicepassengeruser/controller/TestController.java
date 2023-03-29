package com.didi2023.servicepassengeruser.controller;

import com.didi2023.internalcommon.constant.dto.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test")
    public ResponseResult testResponseResult(){

        return ResponseResult.success();
    }
}
