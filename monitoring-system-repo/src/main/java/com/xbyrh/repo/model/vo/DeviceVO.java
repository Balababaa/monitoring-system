package com.xbyrh.repo.model.vo;

import lombok.Data;

/**
 * create at 2021/4/17
 *
 * @author chenxinhui
 */
@Data
public class DeviceVO {

    private Long id;

    private String deviceName;

    private String deviceType;

    private String httpFlvUrl;

    private Integer uid;

    private Boolean isDelete;

    private Integer creator;

    private String createTime;

    private Integer modifier;

    private String updateTime;

}
