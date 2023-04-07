package com.didi.servicedriveruser.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.didi.servicedriveruser.mapper.DriverCarBindingRelationshipMapper;
import com.didi2023.internalcommon.constant.CommonStatusEnum;
import com.didi2023.internalcommon.constant.DriverCarConstants;
import com.didi2023.internalcommon.dto.DriverCarBindingRelationship;
import com.didi2023.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DriverCarBindingRelationshipService {

    @Autowired
    private DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;

    public ResponseResult bind(DriverCarBindingRelationship driverCarBindingRelationship) {

        //判断是否已经绑定
        QueryWrapper<DriverCarBindingRelationship> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id", driverCarBindingRelationship.getDriverId());
        queryWrapper.eq("car_id", driverCarBindingRelationship.getCarId());
        queryWrapper.eq("bind_state", DriverCarConstants.BINDING);
        Integer count = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if (count > 0) {
            return ResponseResult.fail(CommonStatusEnum.DRIVER_CAR_BIND_EXIST.getCode(), CommonStatusEnum.DRIVER_CAR_BIND_EXIST.getValue());
        }

        //判断司机是否已经绑定车辆
        queryWrapper.clear();
        queryWrapper.eq("driver_id", driverCarBindingRelationship.getDriverId());
        queryWrapper.eq("bind_state", DriverCarConstants.BINDING);
        Integer count1 = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if (count1 > 0) {
            return ResponseResult.fail(CommonStatusEnum.DRIVER_BIND_CAR.getCode(), CommonStatusEnum.DRIVER_BIND_CAR.getValue());
        }

        //判断车辆是否已经绑定司机
        queryWrapper.clear();
        queryWrapper.eq("car_id", driverCarBindingRelationship.getCarId());
        queryWrapper.eq("bind_state", DriverCarConstants.BINDING);
        Integer count2 = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if (count2 > 0) {
            return ResponseResult.fail(CommonStatusEnum.CAR_BIND_DRIVER.getCode(), CommonStatusEnum.CAR_BIND_DRIVER.getValue());
        }


        driverCarBindingRelationship.setBindState(DriverCarConstants.BINDING);
        driverCarBindingRelationship.setBindingTime(LocalDateTime.now());
        int insert = driverCarBindingRelationshipMapper.insert(driverCarBindingRelationship);

        return ResponseResult.success(insert);
    }

    public ResponseResult unbind(DriverCarBindingRelationship driverCarBindingRelationship) {

        //判断是否已经绑定
        QueryWrapper<DriverCarBindingRelationship> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id", driverCarBindingRelationship.getDriverId());
        queryWrapper.eq("car_id", driverCarBindingRelationship.getCarId());
        queryWrapper.eq("bind_state", DriverCarConstants.BINDING);
        List<DriverCarBindingRelationship> driverCarBindingRelationships = driverCarBindingRelationshipMapper.selectList(queryWrapper);
        if (driverCarBindingRelationships.isEmpty()) {
            return ResponseResult.fail(CommonStatusEnum.DRIVER_CAR_BIND_NOT_EXIST.getCode(), CommonStatusEnum.DRIVER_CAR_BIND_NOT_EXIST.getValue());
        }

        //
        DriverCarBindingRelationship relationship = driverCarBindingRelationships.get(0);
        relationship.setBindState(DriverCarConstants.UNBINDING);
        relationship.setUnBindingTime(LocalDateTime.now());

        int update = driverCarBindingRelationshipMapper.updateById(relationship);

        return ResponseResult.success(update);

    }
}
