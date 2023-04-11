package com.didi2023.servicemap.service;

import com.didi2023.internalcommon.dto.AmapDTO;
import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.servicemap.remote.PointClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointService {

    @Autowired
    private PointClient pointClient;

    public ResponseResult upload(AmapDTO amap) {

        return pointClient.upload(amap);

    }
}
