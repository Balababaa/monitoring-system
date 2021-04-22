package com.xbyrh.media.listener;

import com.xbyrh.common.event.DeviceAddEvent;
import com.xbyrh.media.context.VideoStreamGrabTaskContext;
import com.xbyrh.media.task.VideoStreamGrabTask;
import com.xbyrh.repo.entity.Device;
import com.xbyrh.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
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
     *
     * @param deviceAddEvent deviceAddEvent
     */
    @Override
    public void onApplicationEvent(DeviceAddEvent deviceAddEvent) {
        Long id = deviceAddEvent.getDeviceId();
        String httpFlvUrl = deviceAddEvent.getHttpFlvUrl();

        VideoStreamGrabTask videoStreamGrabTask = new VideoStreamGrabTask(id, httpFlvUrl);

        VideoStreamGrabTaskContext.add(id, videoStreamGrabTask);
    }

}
