package com.didi.apidriver.remote;

import com.didi2023.internalcommon.dto.DriverUser;
import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.response.DriverUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {

    @PostMapping("/user")
    ResponseResult addUser(@RequestBody DriverUser driverUser);

    @PutMapping("/user")
    ResponseResult updateUser(@RequestBody DriverUser driverUser);

    @GetMapping ("/user")
    ResponseResult<DriverUserResponse> getUser(@RequestBody DriverUser driverUser);
}
