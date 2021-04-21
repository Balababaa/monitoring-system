package com.xbyrh.common.event;

import org.springframework.context.ApplicationEvent;

/**
 * create at 2021/4/21
 *
 * @author chenxinhui
 */
public class DeviceDeleteEvent extends ApplicationEvent {
    public DeviceDeleteEvent(Object source) {
        super(source);
    }
}
