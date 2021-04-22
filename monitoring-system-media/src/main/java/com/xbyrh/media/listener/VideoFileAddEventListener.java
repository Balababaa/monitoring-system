package com.xbyrh.media.listener;

import com.xbyrh.common.event.VideoFileAddEvent;
import com.xbyrh.repo.entity.VideoFile;
import com.xbyrh.service.IVideoFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * create at 2021/4/22
 *
 * @author chenxinhui
 */
@Component
public class VideoFileAddEventListener implements ApplicationListener<VideoFileAddEvent> {

    @Autowired
    private IVideoFileService videoFileService;

    @Override
    public void onApplicationEvent(VideoFileAddEvent videoFileAddEvent) {

        VideoFile videoFile = new VideoFile();
        videoFile.setDeviceId(videoFileAddEvent.getDeviceId());
        videoFile.setFileName(videoFileAddEvent.getFileName());
        videoFile.setFileUrl("");

        File file = new File(videoFile.getFileName());
        videoFile.setFileSize((int) file.length());
        videoFile.setStartTime(videoFileAddEvent.getStartTime());
        videoFile.setEndTime(videoFileAddEvent.getEndTime());

        videoFileService.addVideoFile(videoFile);
    }
}
