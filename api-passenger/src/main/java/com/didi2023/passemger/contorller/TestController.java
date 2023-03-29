package com.didi2023.passemger.contorller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/passenger")
public class TestController {


    @GetMapping("/test")
    public String test(){


        return "test apiPassenger controller success";
    }

}
