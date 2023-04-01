package com.didi2023.internalcommon.constant;

import lombok.Getter;

public enum CommonStatusEnum {

    VERIFICATION_CODE_ERROR(1099,"验证码不正确"),
    FAIL(0, "fail"),
    SUCCESS(1, "success"),
    TOKEN_ERROR(2,"token invalid"),
    USER_NOT_EXISTS(3,"user not exists")
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
