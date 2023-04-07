package com.didi.apiboss.controller;

import com.didi.apiboss.service.CarService;
import com.didi.apiboss.service.DriverCarBindingRelationshipService;
import com.didi.apiboss.service.DriverUserService;
import com.didi2023.internalcommon.dto.Car;
import com.didi2023.internalcommon.dto.DriverCarBindingRelationship;
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

    @Autowired
    private CarService carService;

    @Autowired
    private DriverCarBindingRelationshipService driverCarBindingRelationshipService;
    @PostMapping("/driver-user")
    public ResponseResult addDriverUser(@RequestBody DriverUser driverUser) {

        return driverUserService.addDriverUser(driverUser);
    }

    @PutMapping("/driver-user")
    public ResponseResult updateDriverUser(@RequestBody DriverUser driverUser) {

        return driverUserService.updateDriverUser(driverUser);
    }

    @PostMapping("/car")
    public ResponseResult addCar(@RequestBody Car car){

        return carService.addCar(car);
    }
    @PutMapping("/car")
    public ResponseResult updateCar(@RequestBody Car car){

        return carService.updateCar(car);
    }

    @PostMapping("/driver-car-binding-relationship/bind")
    public ResponseResult bind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){

        return driverCarBindingRelationshipService.bind(driverCarBindingRelationship);
    }

    @PostMapping("/driver-car-binding-relationship/unbind")
    public ResponseResult unbind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){


        return driverCarBindingRelationshipService.unbind(driverCarBindingRelationship);
    }
}
