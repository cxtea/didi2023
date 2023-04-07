package com.didi.apidriver.service;

import com.didi.apidriver.remote.ServiceDriverUserClient;
import com.didi2023.internalcommon.dto.DriverUser;
import com.didi2023.internalcommon.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DriverUserService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;
    public ResponseResult addDriverUser(DriverUser driverUser) {
        ResponseResult responseResult = serviceDriverUserClient.addUser(driverUser);
        return ResponseResult.success(responseResult.getData());
    }

    public ResponseResult updateDriverUser(DriverUser driverUser) {

        return serviceDriverUserClient.updateUser(driverUser);
    }
}
