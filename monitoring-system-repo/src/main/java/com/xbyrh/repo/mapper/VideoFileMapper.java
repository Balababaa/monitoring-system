package com.xbyrh.repo.mapper;

import com.xbyrh.repo.entity.VideoFile;
import com.xbyrh.repo.entity.VideoFileExample;
import com.xbyrh.repo.model.bo.VideoFileBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VideoFile record);

    int insertSelective(VideoFile record);

    List<VideoFile> selectByExample(VideoFileExample example);

    VideoFile selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VideoFile record);

    int updateByPrimaryKey(VideoFile record);

    List<VideoFile> getVideoFileListByDeviceId(@Param("deviceId") Long deviceId, @Param("startTime") String startTime,
                                               @Param("endTime") String endTime, @Param("offset") Long offset,
                                               @Param("limit") Long limit);

    Long countVideoFileByDeviceId(@Param("deviceId") Long deviceId, @Param("startTime") String startTime,
                                  @Param("endTime") String endTime);
}