package com.xbyrh.service.impl;

import com.xbyrh.repo.entity.VideoFile;
import com.xbyrh.repo.mapper.VideoFileMapper;
import com.xbyrh.repo.model.bo.VideoFileBO;
import com.xbyrh.repo.model.mapper.VideoFileConverter;
import com.xbyrh.service.IVideoFileService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * create at 2021/4/19
 *
 * @author chenxinhui
 */
@Service
public class VideoFileServiceImpl implements IVideoFileService {

    @Autowired
    private VideoFileMapper videoFileMapper;

    @Autowired
    private VideoFileConverter videoFileConverter;

    @Override
    public List<VideoFileBO> getVideoFileListByDeviceId(Long deviceId, String startTime, String endTime, Long page,
                                                        Long limit) {
        List<VideoFile> videoFileList = videoFileMapper.getVideoFileListByDeviceId(deviceId, startTime,
                                                                                             endTime,
                                                                                             (page - 1) * limit, limit);

        if (CollectionUtils.isEmpty(videoFileList)) {
            return new ArrayList<>();
        }

        return videoFileConverter.toBOList(videoFileList);
    }

    @Override
    public Long countVideoFileByDeviceId(Long deviceId, String startTime, String endTime) {
        return videoFileMapper.countVideoFileByDeviceId(deviceId, startTime, endTime);
    }
}
