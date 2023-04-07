package com.didi.apiboss.service;

import com.didi.apiboss.remote.ServiceDriverUserClient;
import com.didi2023.internalcommon.dto.Car;
import com.didi2023.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;
    public ResponseResult addCar(Car car) {
        return serviceDriverUserClient.addCar(car);
    }

    public ResponseResult updateCar(Car car) {
        return serviceDriverUserClient.updateCar(car);
    }
}
