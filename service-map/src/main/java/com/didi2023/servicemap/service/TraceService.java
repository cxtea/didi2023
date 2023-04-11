package com.didi2023.servicemap.service;

import com.didi2023.internalcommon.request.AmapDTO;
import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.response.TraceResponse;
import com.didi2023.servicemap.remote.TraceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TraceService {
    @Autowired
    private TraceClient traceClient;

    public ResponseResult<TraceResponse> add(AmapDTO amapDTO) {


        return traceClient.add(amapDTO);
    }
}
