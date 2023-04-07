package com.didi.apidriver.controller;

import com.didi.apidriver.service.DriverUserService;
import com.didi2023.internalcommon.dto.DriverUser;
import com.didi2023.internalcommon.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class DriverUserController {

    @Autowired
    private DriverUserService driverUserService;

    @PostMapping("/user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser) {

        return driverUserService.addDriverUser(driverUser);
    }

    @PutMapping("/user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser) {

        return driverUserService.updateDriverUser(driverUser);
    }
}
