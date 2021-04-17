package com.xbyrh.repo.model.bo;

import lombok.Data;

import java.util.Date;

@Data
public class DeviceBO {

    private Long id;

    private String deviceName;

    private Integer deviceType;

    private String httpFlvUrl;

}