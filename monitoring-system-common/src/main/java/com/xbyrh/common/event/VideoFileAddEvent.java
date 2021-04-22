package com.xbyrh.common.event;

import org.springframework.context.ApplicationEvent;

import java.util.Date;

/**
 * create at 2021/4/22
 *
 * @author chenxinhui
 */
public class VideoFileAddEvent extends ApplicationEvent {

    private Long deviceId;

    private String fileName;

    private Date startTime;

    private Date endTime;

    public VideoFileAddEvent(Long deviceId, String fileName, Date startTime, Date endTime) {
        super(deviceId);
        this.deviceId = deviceId;
        this.fileName = fileName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public String getFileName() {
        return fileName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }
}
