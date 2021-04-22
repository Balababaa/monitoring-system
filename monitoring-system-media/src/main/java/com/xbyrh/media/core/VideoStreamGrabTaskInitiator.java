package com.xbyrh.media.core;

import com.xbyrh.common.event.DeviceAddEvent;
import com.xbyrh.repo.entity.Device;
import com.xbyrh.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * create at 2021/4/22
 *
 * @author chenxinhui
 */
@Component
public class VideoStreamGrabTaskInitiator implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private IDeviceService deviceService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();

        List<Device> deviceList = deviceService.getAll();

        for (Device device : deviceList) {
            applicationContext.publishEvent(new DeviceAddEvent(device.getId(),device.getHttpFlvUrl()));
        }
    }
}
