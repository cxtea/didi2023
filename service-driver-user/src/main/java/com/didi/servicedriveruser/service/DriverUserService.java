package com.didi.servicedriveruser.service;

import com.didi.servicedriveruser.mapper.DriverUserMapper;
import com.didi2023.internalcommon.dto.DriverUser;
import com.didi2023.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class DriverUserService {


    @Autowired
    private DriverUserMapper driverUserMapper;

    public ResponseResult getDriverUserById() {

        DriverUser driverUser = driverUserMapper.selectById(1);

        return ResponseResult.success(driverUser);
    }

    public ResponseResult addUser(DriverUser driverUser) {

        driverUser.setGmtCreate(LocalDateTime.now());
        driverUser.setGmtModified(LocalDateTime.now());
        int insert = driverUserMapper.insert(driverUser);

        return ResponseResult.success(insert);
    }

    public ResponseResult updateUser(DriverUser driverUser) {

            driverUser.setGmtModified(LocalDateTime.now());
            int i = driverUserMapper.updateById(driverUser);

            return ResponseResult.success(i);
    }
}
