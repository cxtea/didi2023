package com.didi2023.passemger.service;

import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.dto.TokenResult;
import com.didi2023.internalcommon.util.JWTUtils;
import com.didi2023.passemger.remote.ServicePassengerUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    public ResponseResult getUser(String accessToken){

        TokenResult tokenResult = JWTUtils.checkToken(accessToken);

        String phone = tokenResult.getPhone();
        System.out.println(phone);
        return servicePassengerUserClient.getUser(phone);
    }
}
