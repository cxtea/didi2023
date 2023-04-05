package com.didi2023.internalcommon.dto;

import lombok.Data;

@Data
public class ForecastPriceDTO {

    /**
     * 出发地纬度
     */
    private String depLatitude;
    /**
     * 出发地经度
     */
    private String depLongitude;
    /**
     * 目的地纬度
     */
    private String destLatitude;
    /**
     * 目的地经度
     */
    private String destLongitude;


}
