package com.didi2023.internalcommon.request;

import com.didi2023.internalcommon.dto.Point;
import lombok.Data;

@Data
public class AmapDTO {

    private Long carId;

    private String name;

    private String sid;

    private String tid;

    private String trid;

    private String desc;

    private Point[] points ;
}
