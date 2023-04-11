package com.didi2023.servicemap.service;

import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.response.TerminalResponse;
import com.didi2023.servicemap.remote.TerminalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TerminalService {

    @Autowired
    private TerminalClient terminalClient;

    public ResponseResult<TerminalResponse> add(String name) {

        return terminalClient.add(name);
    }
}
