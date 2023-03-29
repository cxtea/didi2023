package com.didi2023.servicepassengeruser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.didi2023.servicepassengeruser.dto.PassengerUser;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
@MapperScan("com.didi2023.servicepassengeruser.mapper")
public interface PassengerUserMapper extends BaseMapper<PassengerUser> {

}
