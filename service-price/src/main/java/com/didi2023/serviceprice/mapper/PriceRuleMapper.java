package com.didi2023.serviceprice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.didi2023.internalcommon.dto.PriceRule;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
@MapperScan("com.didi2023.serviceprice.mapper")
public interface PriceRuleMapper extends BaseMapper<PriceRule> {

}
