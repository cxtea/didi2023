package com.didi.servicedriveruser.remote;

import com.didi2023.internalcommon.dto.AmapDTO;
import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.response.TerminalResponse;
import com.didi2023.internalcommon.response.TraceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-map")
public interface ServiceMapClient {


    @PutMapping("/terminal/add")
    ResponseResult<TerminalResponse> addTerminal(@RequestBody AmapDTO amap);

    @PutMapping("/trace/add")
    ResponseResult<TraceResponse> addTrace(@RequestBody AmapDTO amap);

    @PutMapping("/point/upload")
    ResponseResult uploadPoint(@RequestBody AmapDTO amap);
}
