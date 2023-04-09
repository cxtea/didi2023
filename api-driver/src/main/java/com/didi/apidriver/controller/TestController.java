package com.didi.apidriver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/auth")
    public String auth(){
        return "auth";
    }

    @GetMapping("/noauth")
    public String noauth(){
        return "noauth";
    }
}
