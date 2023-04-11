package com.didi.servicedriveruser.service;

import com.didi.servicedriveruser.mapper.CarMapper;
import com.didi.servicedriveruser.remote.ServiceMapClient;
import com.didi2023.internalcommon.dto.AmapDTO;
import com.didi2023.internalcommon.dto.Car;
import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.response.TerminalResponse;
import com.didi2023.internalcommon.response.TraceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CarService {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private ServiceMapClient serviceMapClient;

    public ResponseResult addCar(Car car) {

        car.setGmtCreate(LocalDateTime.now());
        car.setGmtModified(LocalDateTime.now());

        //获得车辆的 tid
        AmapDTO amapDTO = new AmapDTO();
        amapDTO.setName(car.getVehicleNo());
        ResponseResult<TerminalResponse> responseResult = serviceMapClient.addTerminal(amapDTO);
        String tid = responseResult.getData().getTid();
        car.setTid(tid);

        //获得车辆的轨迹 trid
        amapDTO.setTid(tid);
        ResponseResult<TraceResponse> traceResponseResponseResult = serviceMapClient.addTrace(amapDTO);
        String trid = traceResponseResponseResult.getData().getTrid();
        car.setTrid(trid);

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
