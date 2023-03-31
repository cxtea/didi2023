package com.didi2023.servicepassengeruser.service;

import com.didi2023.internalcommon.dto.ResponseResult;
import com.didi2023.internalcommon.response.TokenResponse;
import com.didi2023.servicepassengeruser.dto.PassengerUser;
import com.didi2023.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private PassengerUserMapper passengerUserMapper;
    public ResponseResult loginOrRegister(String passengerPhone){
        System.out.println("user service 被调用 ,手机号:"+ passengerPhone);
        //根据手机号查询用户信息
        Map<String,Object> map = new HashMap<>();
        map.put("passenger_phone",passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);

        System.out.println(passengerUsers.size() == 0 ? "无记录":passengerUsers.get(0).getPassengerPhone());
        //登录或者注册用户
        if(passengerUsers.size() == 0){
            PassengerUser passengerUser = new PassengerUser();
            passengerUser.setPassengerName("张三");
            passengerUser.setPassengerGender((byte)0);
            passengerUser.setPassengerPhone(passengerPhone);
            passengerUser.setState((byte)0);

            LocalDateTime now = LocalDateTime.now();
            passengerUser.setGmtCreate(now);
            passengerUser.setGmtModified(now);

            passengerUserMapper.insert(passengerUser);
        }
        //判断用户是否存在  存在登录 获取token

        //不存在 注册并获取token
        TokenResponse tokenResponse = new TokenResponse();

        return ResponseResult.success(tokenResponse);
    }
}
