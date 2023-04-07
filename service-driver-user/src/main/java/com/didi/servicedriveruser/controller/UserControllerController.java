package com.didi.servicedriveruser.controller;

import com.didi.servicedriveruser.service.DriverUserService;
import com.didi2023.internalcommon.dto.DriverUser;
import com.didi2023.internalcommon.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserControllerController {

    @Autowired
    private DriverUserService driverUserService;


    @PostMapping("/user")
    public ResponseResult addUser(@RequestBody DriverUser driverUser){

        log.info("addUser: {}", driverUser);

        return driverUserService.addUser(driverUser);
    }

    @PutMapping("/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser){

            return driverUserService.updateUser(driverUser);
    }

}
