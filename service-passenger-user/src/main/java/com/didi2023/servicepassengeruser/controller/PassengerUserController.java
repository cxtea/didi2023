package com.didi2023.servicepassengeruser.controller;

import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.request.VerificationCodeDTO;
import com.didi2023.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PassengerUserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO){

        String passengerPhone = verificationCodeDTO.getPassengerPhone();

        System.out.println(passengerPhone);

        return userService.loginOrRegister(passengerPhone);
    }
}
