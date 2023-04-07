package com.didi.servicedriveruser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.didi2023.internalcommon.dto.DriverUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface DriverUserMapper extends BaseMapper<DriverUser> {


}
