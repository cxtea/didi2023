package com.didi.servicedriveruser.controller;

import com.didi.servicedriveruser.service.DriverUserService;
import com.didi2023.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private DriverUserService driverUserService;
    @GetMapping("/test")
    public ResponseResult test() {

        return driverUserService.getDriverUserById();

    }
}
