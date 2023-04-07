package com.didi2023.servicemap.controller;

import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.servicemap.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistrictController {

    @Autowired
    private DistrictService districtService;
    @GetMapping("/initDistrict")
    public ResponseResult initDistrict(String keywords,String subdistrict){

        return districtService.initDistrict(keywords,subdistrict);
    }
}
