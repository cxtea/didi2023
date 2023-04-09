package com.didi2023.internalcommon.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 司机工作状态表
 * </p>
 *
 * @author shawn
 * @since 2023-04-09
 */
@Data
public class DriverUserWorkStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 司机id
     */
    private Long driverId;

    /**
     * 工作状态
     */
    private Integer workStatus;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;


}
