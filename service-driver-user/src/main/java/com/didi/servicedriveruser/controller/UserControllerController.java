package com.didi.servicedriveruser.controller;

import com.didi.servicedriveruser.service.DriverUserService;
import com.didi2023.internalcommon.constant.DriverCarConstants;
import com.didi2023.internalcommon.dto.DriverUser;
import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.response.DriverUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserControllerController {

    @Autowired
    private DriverUserService driverUserService;


    @PostMapping("/user")
    public ResponseResult addUser(@RequestBody DriverUser driverUser) {

        log.info("addUser: {}", driverUser);

        return driverUserService.addUser(driverUser);
    }

    @PutMapping("/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser) {

        return driverUserService.updateUser(driverUser);
    }

    @GetMapping("/user")
    public ResponseResult<DriverUserResponse> getUser(@RequestBody DriverUser driverUser) {

        DriverUser driverUserDB = driverUserService.getDriverUser(driverUser).getData();

        DriverUserResponse driverUserResponse = new DriverUserResponse();
        Integer ifExist = DriverCarConstants.DRIVER_EXIST;
        if (driverUserDB == null) {
            ifExist = DriverCarConstants.DRIVER_NOT_EXIST;
        }
        driverUserResponse.setDriverPhone(driverUser.getDriverPhone());
        driverUserResponse.setIfExist(ifExist);
        return ResponseResult.success(driverUserResponse);

    }

}
