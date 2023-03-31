package com.didi2023.passemger.contorller;

import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.response.TokenResponse;
import com.didi2023.passemger.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {


    @Autowired
    private TokenService tokenService;
    @PostMapping("/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse){

        String refreshToken = tokenResponse.getRefreshToken();

        return tokenService.refreshToken(refreshToken);

    }
}
