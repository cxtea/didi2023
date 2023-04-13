package com.didi2023.servicemap.controller;

import com.didi2023.internalcommon.request.AmapDTO;
import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.response.TraceResponse;
import com.didi2023.servicemap.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/point")
public class PointController {
    @Autowired
    private PointService pointService;

    @PutMapping("/upload")
    public ResponseResult pointUpdate(@RequestBody AmapDTO amap){

        return pointService.upload(amap);
    }
}
