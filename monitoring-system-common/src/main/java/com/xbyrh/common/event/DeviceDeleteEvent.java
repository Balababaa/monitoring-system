package com.xbyrh.common.event;

import org.springframework.context.ApplicationEvent;

/**
 * create at 2021/4/21
 *
 * @author chenxinhui
 */
public class DeviceDeleteEvent extends ApplicationEvent {

    private Long deviceId;

    public DeviceDeleteEvent(Long deviceId) {
        super(deviceId);
        this.deviceId = deviceId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

}
