package com.didi2023.servicepassengeruser.service;

import com.didi2023.internalcommon.constant.dto.ResponseResult;
import com.didi2023.internalcommon.constant.response.TokenResponse;
import com.didi2023.servicepassengeruser.dto.PassengerUser;
import com.didi2023.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private PassengerUserMapper passengerUserMapper;
    public ResponseResult loginOrReg(String passengerPhone){
        System.out.println("user service 被调用 ,手机号:"+ passengerPhone);
        //根据手机号查询用户信息
        Map<String,Object> map = new HashMap<>();
        map.put("passenger_phone",passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);

        System.out.println(passengerUsers.size() == 0 ? "无记录":passengerUsers.get(0).getPassengerPhone());
        //登录或者注册用户

        //判断用户是否存在  存在登录 获取token

        //不存在 注册并获取token
        TokenResponse tokenResponse = new TokenResponse();

        return ResponseResult.success(tokenResponse);
    }
}
