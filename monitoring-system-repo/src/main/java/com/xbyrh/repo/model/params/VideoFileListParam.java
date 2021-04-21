package com.xbyrh.repo.model.params;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * create at 2021/4/19
 *
 * @author chenxinhui
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VideoFileListParam extends PaginationParam{

    private Long deviceId;

    private String startTime;

    private String endTime;

}
