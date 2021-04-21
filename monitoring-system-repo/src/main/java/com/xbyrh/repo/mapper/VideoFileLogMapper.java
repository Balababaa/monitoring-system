package com.xbyrh.repo.mapper;

import com.xbyrh.repo.entity.VideoFileLog;
import com.xbyrh.repo.entity.VideoFileLogExample;
import java.util.List;

public interface VideoFileLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VideoFileLog record);

    int insertSelective(VideoFileLog record);

    List<VideoFileLog> selectByExample(VideoFileLogExample example);

    VideoFileLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VideoFileLog record);

    int updateByPrimaryKey(VideoFileLog record);
}