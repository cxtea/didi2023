package com.didi.apiboss.controller;

import com.didi.apiboss.service.DriverUserService;
import com.didi2023.internalcommon.dto.DriverUser;
import com.didi2023.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverUserController {

    @Autowired
    private DriverUserService driverUserService;

    @PostMapping("/driver-user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser) {

        return driverUserService.addDriverUser(driverUser);
    }

    @PutMapping("/driver-user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser) {

        return driverUserService.updateDriverUser(driverUser);
    }
}
