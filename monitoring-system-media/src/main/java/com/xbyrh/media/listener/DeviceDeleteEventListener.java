package com.xbyrh.media.listener;

import com.xbyrh.common.event.DeviceDeleteEvent;
import com.xbyrh.media.context.VideoStreamGrabTaskContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * create at 2021/4/21
 *
 * @author chenxinhui
 */
@Component
public class DeviceDeleteEventListener implements ApplicationListener<DeviceDeleteEvent> {

    /**
     * 停止视频录制
     * @param deviceDeleteEvent deviceDeleteEvent
     */
    @Override
    public void onApplicationEvent(DeviceDeleteEvent deviceDeleteEvent) {
        Long deviceId = deviceDeleteEvent.getDeviceId();

        VideoStreamGrabTaskContext.stop(deviceId);
    }
}
