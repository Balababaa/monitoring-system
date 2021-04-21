package com.xbyrh.repo.mapper;

import com.xbyrh.repo.entity.VideoFile;
import com.xbyrh.repo.entity.VideoFileExample;
import java.util.List;

public interface VideoFileMapper {
    int countByExample(VideoFileExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VideoFile record);

    int insertSelective(VideoFile record);

    List<VideoFile> selectByExample(VideoFileExample example);

    VideoFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VideoFile record);

    int updateByPrimaryKey(VideoFile record);
}