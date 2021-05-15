package com.xbyrh.repo.model.vo;

import lombok.Data;

/**
 * create at 2021/5/14
 *
 * @author chenxinhui
 */
@Data
public class PermissionListVO {

    private String name;

    private String code;

    private String type;

    private String path;

    private Long creator;

    private String createTime;

    private Long modifier;

    private String updateTime;

}
