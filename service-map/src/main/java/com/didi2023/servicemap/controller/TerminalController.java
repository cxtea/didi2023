package com.didi2023.servicemap.controller;

import com.didi2023.internalcommon.request.AmapDTO;
import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.response.TerminalResponse;
import com.didi2023.servicemap.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/terminal")
public class TerminalController {

    @Autowired
    private TerminalService terminalService;

    @PutMapping("/add")
    public ResponseResult<TerminalResponse> add(@RequestBody AmapDTO amap){

        String name = amap.getName();

        return terminalService.add(name);
    }
}
