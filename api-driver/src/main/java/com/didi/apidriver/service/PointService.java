package com.didi.apidriver.service;

import com.didi.apidriver.remote.ServiceDriverUserClient;
import com.didi.apidriver.remote.ServiceMapClient;
import com.didi2023.internalcommon.dto.Car;
import com.didi2023.internalcommon.dto.Point;
import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.request.AmapDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Autowired
    private ServiceMapClient serviceMapClient;

    public ResponseResult uploadPoint(Long id, Point[] points) {
        // 通过carid 获取 tid & trid
        ResponseResult<Car> carResult= serviceDriverUserClient.getCarById(id);
        Car car = carResult.getData();
        String tid = car.getTid();
        String trid = car.getTrid();

        AmapDTO amapDTO = new AmapDTO();
        amapDTO.setTid(tid);
        amapDTO.setTrid(trid);
        amapDTO.setPoints(points);
        ResponseResult responseResult = serviceMapClient.uploadPoint(amapDTO);

        return responseResult;
    }

}
