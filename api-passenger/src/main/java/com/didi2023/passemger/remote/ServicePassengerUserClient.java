package com.didi2023.passemger.remote;

import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.request.VerificationCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("service-passenger-user")
public interface ServicePassengerUserClient {


    @RequestMapping(method = RequestMethod.POST,value = "/user")
    ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO);

    @GetMapping("/user/{phone}")
    ResponseResult getUser(@PathVariable("phone") String passengerPhone);
}
