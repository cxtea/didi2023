package com.didi.servicedriveruser.service;

import com.didi.servicedriveruser.mapper.DriverUserMapper;
import com.didi2023.internalcommon.constant.CommonStatusEnum;
import com.didi2023.internalcommon.constant.DriverCarConstants;
import com.didi2023.internalcommon.dto.DriverUser;
import com.didi2023.internalcommon.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
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

    public ResponseResult<DriverUser> getDriverUser(DriverUser driverUser) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("driver_phone", driverUser.getDriverPhone());
        map.put("state", DriverCarConstants.DRIVER_STATE_VALID);
        List<DriverUser> driverUsers = driverUserMapper.selectByMap(map);

        log.info("driverUsers: {}", driverUsers);
        if (driverUsers.isEmpty()) {
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXISTS.getCode(), CommonStatusEnum.DRIVER_NOT_EXISTS.getValue());
        }

        return ResponseResult.success(driverUsers.get(0));
    }
}
