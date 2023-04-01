package com.didi2023.passemger.contorller;

import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.passemger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseResult getUser(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        return userService.getUser(token);
    }
}
