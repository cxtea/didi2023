package com.didi2023.passemger.contorller;

import com.didi2023.internalcommon.constant.dto.ResponseResult;
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

    /**
     * 需要认证的接口
     * @return
     */
    @RequestMapping("/authTest")
    public ResponseResult authTest(){

        return ResponseResult.success("authTest success");
    }
    /**
     * 不需要认证的接口
     * @return
     */
    @RequestMapping("/noauthTest")
    public ResponseResult noauthTest(){

        return ResponseResult.success("noauthTest success");
    }

}
