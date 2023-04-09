package com.didi.servicedriveruser.service;

import com.didi2023.internalcommon.dto.DriverUserWorkStatus;
import com.didi.servicedriveruser.mapper.DriverUserWorkStatusMapper;
import com.didi2023.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * <p>
 * 司机工作状态表 服务实现类
 * </p>
 *
 * @author shawn
 * @since 2023-04-09
 */
@Service
public class DriverUserWorkStatusService {

    @Autowired
    private DriverUserWorkStatusMapper driverUserWorkStatusMapper;
    public ResponseResult changeWorkStatus(Long driverId ,Integer workStatus){

        HashMap<String, Object> map = new HashMap<>();
        map.put("driver_id",driverId);
        DriverUserWorkStatus driverUserWorkStatus = driverUserWorkStatusMapper.selectById(driverId);

        driverUserWorkStatus.setWorkStatus(workStatus);
        LocalDateTime now = LocalDateTime.now();
        driverUserWorkStatus.setGmtModified(now);

        int update = driverUserWorkStatusMapper.updateById(driverUserWorkStatus);

        return ResponseResult.success(update);

    }
}
