package com.didi2023.internalcommon.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class DriverUser {
    private Long id;

    private String address;

    private String driverName;

    private String driverPhone;

    private Integer driverGender;

    private LocalDate driverBirthday;

    private String driverNation;

    private String driverConcatAddress;

    private String licenseId;

    //  初次领取驾驶证时间
    private LocalDate getDriverLicenseDate;
    // 驾驶证有效期始
    private LocalDate driverLicenseOn;
    // 驾驶证有效期止
    private LocalDate driverLicenseOff;

    private Integer taxiDriver;
    // 网络预约出租车驾驶员发证机构
    private String networkCarIssueOrganization;
    // 网络预约出租车驾驶员编号
    private String certificateNo;
    // 资格证发证日期
    private LocalDate networkCarIssueDate;
    // 初次领取资格证日期
    private LocalDate getNetworkCarProofDate;
    // 资格证有效期始
    private LocalDate networkCarProofOn;
    // 资格证有效期止
    private LocalDate networkCarProofOff;
    // 报备日期
    private LocalDate registerDate;
    // 服务类型
    private Integer commercialType;
    // 驾驶员合同签署公司
    private String contractCompany;
    // 合同有效期起
    private LocalDate contractOn;
    // 合同有效期止
    private LocalDate contractOff;
    // 司机状态
    private Integer state;
    // 创建时间
    private LocalDateTime gmtCreate;
    // 修改时间
    private LocalDateTime gmtModified;


}
