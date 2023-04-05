package com.didi2023.internalcommon.dto;

import lombok.Data;

@Data
public class PriceRule {

    //城市代码
    private String cityCode;
    //车辆类型
    private String vehicleType;
    //起步价
    private Double startFare;
    //起步里程
    private Integer startMile;
    //每公里加价
    private Double unitPricePerMile;
    //每分钟加价
    private Double unitPricePerMinute;

}
