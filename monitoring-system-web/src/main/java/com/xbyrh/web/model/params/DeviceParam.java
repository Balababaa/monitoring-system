package com.xbyrh.web.model.params;

import lombok.Data;

/**
 * create at 2021/4/16
 *
 * @author chenxinhui
 */

@Data
public class DeviceParam {

    private String deviceName;

    private Integer deviceType;

    private String httpFlvUrl;

}
