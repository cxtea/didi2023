package com.didi2023.servicemap.service;

import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.servicemap.remote.ServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceForMapService {

    @Autowired
    private ServiceClient serviceClient;


    public ResponseResult add(String name) {

        return serviceClient.addService(name);
    }
}
