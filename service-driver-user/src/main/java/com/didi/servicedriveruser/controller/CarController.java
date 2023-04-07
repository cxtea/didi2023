package com.didi.servicedriveruser.controller;


import com.didi.servicedriveruser.service.CarService;
import com.didi2023.internalcommon.dto.Car;
import com.didi2023.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shawn
 * @since 2023-04-08
 */
@RestController
public class CarController {

    @Autowired
    private CarService carService;
    @PostMapping("/car")
    public ResponseResult addCar(@RequestBody Car car){

        return carService.addCar(car);
    }
    @PutMapping("/car")
    public ResponseResult updateCar(@RequestBody Car car){

        return carService.updateCar(car);
    }

    @GetMapping("/car/{id}")
    public ResponseResult getCarById(@PathVariable Integer id){

        return carService.getCarById(id);
    }
}
