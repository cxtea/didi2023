package com.didi2023.internalcommon.constant;

import lombok.Getter;

public enum CommonStatusEnum {


    FAIL(0, "fail"),

    SUCCESS(1, "success"),

    TOKEN_ERROR(10000, "token invalid"),

    VERIFICATION_CODE_ERROR(11000, "验证码不正确"),

    USER_NOT_EXISTS(12000, "用户不存在"),

    DRIVER_NOT_EXISTS(13000, "司机不存在"),

    DRIVER_CAR_BIND_EXIST(15001, "司机车辆绑定关系已存在"),

    DRIVER_CAR_BIND_NOT_EXIST(15002, "司机车辆绑定关系不存在"),

    DRIVER_BIND_CAR(15003, "司机已绑定车辆"),

    CAR_BIND_DRIVER(15004, "车辆已绑定司机"),
    ;
    @Getter
    private int code;
    @Getter
    private String value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
