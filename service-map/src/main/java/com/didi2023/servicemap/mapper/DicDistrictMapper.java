package com.didi2023.servicemap.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.didi2023.internalcommon.dto.DicDistrict;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

@Mapper

@Repository
public interface DicDistrictMapper extends BaseMapper<DicDistrict> {

}
