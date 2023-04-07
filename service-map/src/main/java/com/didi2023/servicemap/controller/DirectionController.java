package com.didi2023.servicemap.controller;

import com.didi2023.internalcommon.dto.DicDistrict;
import com.didi2023.internalcommon.dto.ForecastPriceDTO;
import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.response.DirectionResponse;
import com.didi2023.servicemap.mapper.DicDistrictMapper;
import com.didi2023.servicemap.service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/direction")
public class DirectionController {

    @Autowired
    private DicDistrictMapper dicDistrictMapper;
    @Autowired
    private DirectionService directionService;
    @GetMapping("/driving")
    public ResponseResult<DirectionResponse> driving(@RequestBody ForecastPriceDTO forecastPriceDTO){

        String depLongitude = forecastPriceDTO.getDepLongitude();
        String depLatitude = forecastPriceDTO.getDepLatitude();
        String desLongitude = forecastPriceDTO.getDestLongitude();
        String desLatitude = forecastPriceDTO.getDestLatitude();

        return directionService.driving(depLongitude,depLatitude,desLongitude,desLatitude);

    }
    @RequestMapping("/test")
    public ResponseResult test(){

        HashMap<String, Object> map = new HashMap<>();
        map.put("address_code","10010");

        List<DicDistrict> dicDistricts = dicDistrictMapper.selectByMap(map);
        System.out.println(dicDistricts);

        return ResponseResult.success(dicDistricts);


    }
}
