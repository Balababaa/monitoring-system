package com.xbyrh.mapper;

import com.xbyrh.entity.VideoLog;
import com.xbyrh.entity.VideoLogExample;
import java.util.List;

public interface VideoLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VideoLog record);

    int insertSelective(VideoLog record);

    List<VideoLog> selectByExample(VideoLogExample example);

    VideoLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VideoLog record);

    int updateByPrimaryKey(VideoLog record);
}