package com.didi2023.internalcommon.dto;

import lombok.Data;

@Data
public class Point {

    // 经纬度坐标 "116.397428,39.90923"  格式为：X,Y  小数点后最多6位
    private String location;
    // 此次定位的时间点 1544176895000
    private long locatetime;
    // 速度 非必填 单位：km/h  小数点后最多3位
    private double speed;
    // 方向 取值范围[0~360]，0代表正北方，采取顺时针方向取值 小数点后最多4位
    private double direction;
    //单位：米  小数点后最多3位
    private double height;
    //定位精度  仅允许输入数字 小数点后最多3位
    private double accuracy;
}
