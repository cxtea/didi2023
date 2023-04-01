package com.didi2023.internalcommon.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PassengerUser {

    private Long id;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;

    private String passengerName;
    private String passengerPhone;
    private byte passengerGender;
    private byte state;

    private String profilePhoto;
}
