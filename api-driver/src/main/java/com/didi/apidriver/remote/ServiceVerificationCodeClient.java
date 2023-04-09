package com.didi.apidriver.remote;

import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-verificationcode")
public interface ServiceVerificationCodeClient {

    @GetMapping("/numberCode/{size}")
    ResponseResult<NumberCodeResponse> numberCode(@PathVariable("size") int size) ;

}
