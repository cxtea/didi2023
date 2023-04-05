package com.didi2023.internalcommon.response;

import lombok.Data;

@Data
public class DirectionResponse {

    // 距离 单位米
    private Integer distance;
    // 时间 单位分钟
    private Integer duration;
    // 路线
    private String polyline;
}
