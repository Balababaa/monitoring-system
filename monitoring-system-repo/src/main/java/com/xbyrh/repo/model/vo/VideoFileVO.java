package com.xbyrh.repo.model.vo;

import com.xbyrh.repo.model.params.PaginationParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * create at 2021/4/19
 *
 * @author chenxinhui
 */
@Data
public class VideoFileVO   {

    private Long id;

    private String fileName;

    private Integer fileSize;

    private String startTime;

    private String endTime;

}
