package com.didi.servicedriveruser.service;

import com.didi.servicedriveruser.remote.ServiceMapClient;
import com.didi2023.internalcommon.dto.AmapDTO;
import com.didi2023.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointService {

    @Autowired
    private ServiceMapClient serviceMapClient;

    public ResponseResult uploadPoint(AmapDTO amapDTO){

        return serviceMapClient.uploadPoint(amapDTO);
    }

}
