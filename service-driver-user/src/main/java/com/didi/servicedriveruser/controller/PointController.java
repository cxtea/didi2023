package com.didi.servicedriveruser.controller;

import com.didi.servicedriveruser.service.PointService;
import com.didi2023.internalcommon.dto.AmapDTO;
import com.didi2023.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/point")
public class PointController {

    @Autowired
    private PointService pointService;
    @PostMapping("/upload")
    public ResponseResult upload(@RequestBody AmapDTO amapDTO) {

        return pointService.uploadPoint(amapDTO);
    }

}
