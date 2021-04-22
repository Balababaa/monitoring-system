package com.xbyrh.common.event;

import org.springframework.context.ApplicationEvent;

/**
 * create at 2021/4/21
 *
 * @author chenxinhui
 */
public class DeviceAddEvent extends ApplicationEvent {

    private Long deviceId;

    private String httpFlvUrl;

    public DeviceAddEvent(Long deviceId, String httpFlvUrl) {
        super(deviceId);
        this.deviceId = deviceId;
        this.httpFlvUrl = httpFlvUrl;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public String getHttpFlvUrl() {
        return httpFlvUrl;
    }

}
