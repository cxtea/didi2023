package com.didi2023.servicemap.service;

import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.response.DirectionResponse;
import com.didi2023.servicemap.remote.MapDirectionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectionService {

    @Autowired
    private MapDirectionClient mapDirectionClient;

    public ResponseResult<DirectionResponse> driving(String depLongitude, String depLatitude, String desLongitude, String desLatitude) {

        String origin = depLongitude + "," + depLatitude;
        String destination = desLongitude + "," + desLatitude;



        DirectionResponse directionResponse = mapDirectionClient.direction(origin, destination);


        return ResponseResult.success(directionResponse);
    }
}
