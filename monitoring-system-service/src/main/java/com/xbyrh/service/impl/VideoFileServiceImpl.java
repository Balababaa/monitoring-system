package com.xbyrh.service.impl;

import com.xbyrh.common.utils.DateUtil;
import com.xbyrh.repo.entity.VideoFile;
import com.xbyrh.repo.entity.VideoFileExample;
import com.xbyrh.repo.mapper.VideoFileMapper;
import com.xbyrh.repo.model.bo.VideoFileBO;
import com.xbyrh.repo.model.mapper.VideoFileConverter;
import com.xbyrh.service.IVideoFileService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<VideoFileBO> getVideoFileListByDeviceId(Long deviceId, String startTime, String endTime, Integer page,
                                                        Integer limit) {
        VideoFileExample example = new VideoFileExample();
        VideoFileExample.Criteria criteria = example.createCriteria();

        criteria.andDeviceIdEqualTo(deviceId);

        if (StringUtils.isNotBlank(startTime)) {
            criteria.andStartTimeGreaterThan(DateUtil.stringToDate(startTime));
        }

        if (StringUtils.isNotBlank(endTime)) {
            criteria.andEndTimeGreaterThan(DateUtil.stringToDate(endTime));
        }

        example.setLimit(limit);
        example.setOffset((page - 1) * limit);
        List<VideoFile> videoFileList = videoFileMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(videoFileList)) {
            return new ArrayList<>();
        }

        return videoFileConverter.toBOList(videoFileList);
    }

    @Override
    public Long countVideoFileByDeviceId(Long deviceId, String startTime, String endTime) {
        VideoFileExample example = new VideoFileExample();
        VideoFileExample.Criteria criteria = example.createCriteria();

        criteria.andDeviceIdEqualTo(deviceId);

        if (StringUtils.isNotBlank(startTime)) {
            criteria.andStartTimeGreaterThan(DateUtil.stringToDate(startTime));
        }

        if (StringUtils.isNotBlank(endTime)) {
            criteria.andEndTimeGreaterThan(DateUtil.stringToDate(endTime));
        }

        return (long) videoFileMapper.countByExample(example);
    }

    @Override
    public void addVideoFile(VideoFile videoFile) {
        videoFileMapper.insertSelective(videoFile);
    }
}
