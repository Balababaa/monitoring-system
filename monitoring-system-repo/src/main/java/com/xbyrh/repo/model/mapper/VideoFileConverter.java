package com.xbyrh.repo.model.mapper;

import com.xbyrh.repo.entity.VideoFile;
import com.xbyrh.repo.model.bo.VideoFileBO;
import com.xbyrh.repo.model.vo.VideoFileVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * create at 2021/4/19
 *
 * @author chenxinhui
 */
@Mapper(componentModel = "spring")
public interface VideoFileConverter {

    VideoFileBO toBO(VideoFile videoFile);

    List<VideoFileBO> toBOList(List<VideoFile> videoFileList);

    @Mapping(source = "startTime", target = "startTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "endTime", target = "endTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    VideoFileVO toVO(VideoFileBO videoFileBO);

    List<VideoFileVO> toVOList(List<VideoFileBO> videoFileBOList);

}
