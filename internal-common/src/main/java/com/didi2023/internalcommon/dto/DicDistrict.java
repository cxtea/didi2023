package com.didi2023.internalcommon.dto;

import lombok.Data;

@Data
public class DicDistrict {

//
//    address_code        char(6)   not null
//    primary key,
//    address_name        char(128) null,
//    parent_address_code char(6)   null,
//    level               tinyint   null

    private String addressCode;
    private String addressName;
    private String parentAddressCode;
    private Integer level;

}
