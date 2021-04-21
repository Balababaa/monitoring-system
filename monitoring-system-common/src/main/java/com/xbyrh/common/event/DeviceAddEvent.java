package com.xbyrh.common.event;

import org.springframework.context.ApplicationEvent;

/**
 * create at 2021/4/21
 *
 * @author chenxinhui
 */
public class DeviceAddEvent extends ApplicationEvent {
    public DeviceAddEvent(Object source) {
        super(source);
    }
}
