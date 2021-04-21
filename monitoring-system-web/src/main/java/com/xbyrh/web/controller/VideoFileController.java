package com.xbyrh.web.controller;

import com.xbyrh.common.annotations.NoAuth;
import com.xbyrh.repo.model.bo.VideoFileBO;
import com.xbyrh.repo.model.mapper.VideoFileConverter;
import com.xbyrh.repo.model.params.VideoFileListParam;
import com.xbyrh.repo.model.vo.VideoFileVO;
import com.xbyrh.service.IVideoFileService;
import com.xbyrh.web.model.support.PaginationResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * create at 2021/4/19
 *
 * @author chenxinhui
 */
@RestController
@RequestMapping("/api/video/file")
public class VideoFileController {

    @Autowired
    private IVideoFileService videoFileService;

    @Autowired
    private VideoFileConverter videoFileConverter;

    @GetMapping("list")
    public PaginationResponse<VideoFileVO> list(VideoFileListParam videoFileListParam) {
        List<VideoFileBO> videoFileBOList = videoFileService.getVideoFileListByDeviceId(
                videoFileListParam.getDeviceId(),
                StringUtils.isBlank(videoFileListParam.getStartTime()) ? null : videoFileListParam.getStartTime(),
                StringUtils.isBlank(videoFileListParam.getEndTime()) ? null : videoFileListParam.getEndTime(),
                videoFileListParam.getPage(), videoFileListParam.getLimit());

        Long total = videoFileService.countVideoFileByDeviceId(
                videoFileListParam.getDeviceId(),
                StringUtils.isBlank(videoFileListParam.getStartTime()) ? null : videoFileListParam.getStartTime(),
                StringUtils.isBlank(videoFileListParam.getEndTime()) ? null : videoFileListParam.getEndTime()
        );

        return PaginationResponse.ok(total, videoFileConverter.toVOList(videoFileBOList));
    }

    @NoAuth
    @GetMapping("/play/{filename:.+}")
    public ResponseEntity<Resource> play(@PathVariable("filename") String filename, HttpServletResponse response) {

        Resource resource = new FileSystemResource(new File(filename));

        String contentType = "video/mp4";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}

