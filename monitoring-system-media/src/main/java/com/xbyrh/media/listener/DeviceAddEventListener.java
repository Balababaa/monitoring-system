package com.xbyrh.media.listener;

import com.xbyrh.common.event.DeviceAddEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * create at 2021/4/21
 *
 * @author chenxinhui
 */
@Component
public class DeviceAddEventListener implements ApplicationListener<DeviceAddEvent> {

    /**
     * 启动视频录制
     * @param deviceAddEvent deviceAddEvent
     */
    @Override
    public void onApplicationEvent(DeviceAddEvent deviceAddEvent) {

    }

}
