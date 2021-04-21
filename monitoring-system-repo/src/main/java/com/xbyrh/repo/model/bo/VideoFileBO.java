package com.xbyrh.repo.model.bo;

import lombok.Data;

import java.util.Date;

/**
 * create at 2021/4/19
 *
 * @author chenxinhui
 */
@Data
public class VideoFileBO {

    private Long id;

    private String fileName;

    private Integer fileSize;

    private Date startTime;

    private Date endTime;

}
