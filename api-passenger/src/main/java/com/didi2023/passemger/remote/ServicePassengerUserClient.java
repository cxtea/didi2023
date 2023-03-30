package com.didi2023.passemger.remote;

import com.didi2023.internalcommon.constant.dto.ResponseResult;
import com.didi2023.internalcommon.constant.request.VerificationCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-passenger-user")
public interface ServicePassengerUserClient {


    @RequestMapping(method = RequestMethod.POST,value = "/user")
    ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO);
}
