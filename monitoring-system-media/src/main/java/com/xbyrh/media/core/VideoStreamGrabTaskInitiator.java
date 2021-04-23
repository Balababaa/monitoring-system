package com.xbyrh.media.core;

import com.xbyrh.media.context.VideoStreamGrabTaskContext;
import com.xbyrh.media.task.VideoStreamGrabTask;
import com.xbyrh.repo.entity.Device;
import com.xbyrh.service.IDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * create at 2021/4/22
 *
 * @author chenxinhui
 */
@Slf4j
@Component
public class VideoStreamGrabTaskInitiator implements InitializingBean {

    @Autowired
    private IDeviceService deviceService;

    private final HashMap<String, FFmpegFrameGrabber> grabberHashMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        List<Device> deviceList = deviceService.getAll();

        for (Device device : deviceList) {
            FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(device.getHttpFlvUrl());
            try {
                if (!grabberHashMap.containsKey(device.getHttpFlvUrl())) {
                    grabber.start();
                    grabberHashMap.put(device.getHttpFlvUrl(), grabber);
                }
            } catch (Exception e) {
                log.error("捕捉流失败，结束录制");
                e.printStackTrace();
                return;
            }
        }
    }


    @Scheduled(cron = "0 * * * * ?")
    public void record() {
        List<Device> deviceList = deviceService.getAll();
        log.info("开始录制, {}", deviceList);

        for (Device device : deviceList) {
            FFmpegFrameGrabber grabber;
            if (grabberHashMap.containsKey(device.getHttpFlvUrl())) {
                grabber = grabberHashMap.get(device.getHttpFlvUrl());
            } else {
                grabber = new FFmpegFrameGrabber(device.getHttpFlvUrl());
                try {
                    if (!grabberHashMap.containsKey(device.getHttpFlvUrl())) {
                        grabber.start();
                        grabberHashMap.put(device.getHttpFlvUrl(), grabber);
                    }
                } catch (Exception e) {
                    log.error("捕捉流失败，结束录制");
                    e.printStackTrace();
                    return;
                }
            }

            VideoStreamGrabTask videoStreamGrabTask = new VideoStreamGrabTask(device.getId(), device.getHttpFlvUrl(),
                                                                              grabber);
            VideoStreamGrabTaskContext.execute(videoStreamGrabTask);
        }

    }
}
