package com.didi.apiboss.service;

import com.didi.apiboss.remote.ServiceDriverUserClient;
import com.didi2023.internalcommon.dto.DriverUser;
import com.didi2023.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverUserService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult addDriverUser(DriverUser driverUser) {

        ResponseResult responseResult = serviceDriverUserClient.addUser(driverUser);

        Object data = responseResult.getData();
        return ResponseResult.success(data);
    }

    public ResponseResult updateDriverUser(DriverUser driverUser) {

            ResponseResult responseResult = serviceDriverUserClient.updateUser(driverUser);

            Object data = responseResult.getData();
            return ResponseResult.success(data);
    }
}
