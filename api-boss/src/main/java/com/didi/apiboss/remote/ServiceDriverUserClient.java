package com.didi.apiboss.remote;

import com.didi2023.internalcommon.dto.Car;
import com.didi2023.internalcommon.dto.DriverCarBindingRelationship;
import com.didi2023.internalcommon.dto.DriverUser;
import com.didi2023.internalcommon.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {

    @PostMapping("/user")
    ResponseResult addUser(@RequestBody DriverUser driverUser);

    @PutMapping("/user")
    ResponseResult updateUser(@RequestBody DriverUser driverUser);

    @PostMapping("/car")
    ResponseResult addCar(@RequestBody Car car);
    @PutMapping("/car")
    ResponseResult updateCar(@RequestBody Car car);

    @PostMapping("/driver-car-binding-relationship/bind")
    ResponseResult bind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);


    @PostMapping("/driver-car-binding-relationship/unbind")
    ResponseResult unbind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);



}
