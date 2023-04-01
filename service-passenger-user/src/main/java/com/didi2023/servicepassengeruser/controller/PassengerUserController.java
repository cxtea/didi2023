package com.didi2023.servicepassengeruser.controller;

import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.request.VerificationCodeDTO;
import com.didi2023.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PassengerUserController {

    @Autowired
    private UserService userService;


    @PostMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO){

        String passengerPhone = verificationCodeDTO.getPassengerPhone();

        System.out.println(passengerPhone);

        return userService.loginOrRegister(passengerPhone);
    }

    @GetMapping("/user/{phone}")
    public ResponseResult getUser(@PathVariable("phone") String passengerPhone){

        return userService.getUserByPhone(passengerPhone);
    }
}
