package com.didi.apidriver.controller;

import com.didi.apidriver.service.PointService;
import com.didi2023.internalcommon.dto.Point;
import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.request.AmapDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/point")
public class PointController {

    @Autowired
    private PointService pointService;
    @PostMapping("/upload")
    public ResponseResult upload(@RequestBody AmapDTO amapDTO) {
        Long carId = amapDTO.getCarId();
        Point[] points = amapDTO.getPoints();

        return pointService.uploadPoint(carId, points);
    }
}
