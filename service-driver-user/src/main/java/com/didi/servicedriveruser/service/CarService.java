package com.didi.servicedriveruser.service;

import com.didi.servicedriveruser.mapper.CarMapper;
import com.didi2023.internalcommon.dto.Car;
import com.didi2023.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CarService {

    @Autowired
    private CarMapper carMapper;

    public ResponseResult addCar(Car car) {

        car.setGmtCreate(LocalDateTime.now());
        car.setGmtModified(LocalDateTime.now());
        int insert = carMapper.insert(car);

        return ResponseResult.success(insert);

    }

    public ResponseResult updateCar(Car car) {

        car.setGmtModified(LocalDateTime.now());
        int update = carMapper.updateById(car);

        return ResponseResult.success(update);
    }

    public ResponseResult getCarById(Integer id) {

        Car car = carMapper.selectById(id);

        return ResponseResult.success(car);
    }
}
