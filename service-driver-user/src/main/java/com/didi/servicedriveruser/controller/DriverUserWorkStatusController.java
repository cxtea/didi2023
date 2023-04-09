package com.didi.servicedriveruser.controller;


import com.didi2023.internalcommon.dto.DriverUserWorkStatus;
import com.didi.servicedriveruser.service.DriverUserWorkStatusService;
import com.didi2023.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 司机工作状态表 前端控制器
 * </p>
 *
 * @author shawn
 * @since 2023-04-09
 */
@RestController
public class DriverUserWorkStatusController {

    @Autowired
    private DriverUserWorkStatusService driverUserWorkStatusService;

    @PostMapping("/driver-user-work-status")
    public ResponseResult changeWorkStatus(@RequestBody DriverUserWorkStatus driverUserWorkStatus){
        Long driverId = driverUserWorkStatus.getDriverId();
        Integer workStatus = driverUserWorkStatus.getWorkStatus();

        return driverUserWorkStatusService.changeWorkStatus(driverId,workStatus);
    }

}
