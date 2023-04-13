package com.didi.apidriver.remote;

import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.request.AmapDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface ServiceMapClient {

    @PostMapping("/point/upload")
    ResponseResult uploadPoint(@RequestBody AmapDTO amapDTO);
}
