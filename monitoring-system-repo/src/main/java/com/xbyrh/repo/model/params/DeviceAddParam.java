package com.xbyrh.repo.model.params;

import lombok.Data;

/**
 * create at 2021/4/16
 *
 * @author chenxinhui
 */

@Data
public class DeviceAddParam {

    private Long id;

    private String deviceName;

    private Integer deviceType;

    private String httpFlvUrl;

    private Long uid;

    private Integer isDelete;
}
