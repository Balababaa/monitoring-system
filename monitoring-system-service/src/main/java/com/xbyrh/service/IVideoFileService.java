package com.xbyrh.service;

import com.xbyrh.repo.entity.VideoFile;
import com.xbyrh.repo.model.bo.VideoFileBO;

import java.util.Date;
import java.util.List;

/**
 * create at 2021/4/19
 *
 * @author chenxinhui
 */
public interface IVideoFileService {
    List<VideoFileBO> getVideoFileListByDeviceId(Long deviceId, String startTime, String endTime, Integer page, Integer limit);

    Long countVideoFileByDeviceId(Long deviceId, String startTime, String endTime);

    void addVideoFile(VideoFile videoFile);
}
