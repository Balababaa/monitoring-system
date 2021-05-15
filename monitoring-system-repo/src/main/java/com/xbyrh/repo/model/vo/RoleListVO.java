package com.xbyrh.repo.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * create at 2021/5/14
 *
 * @author chenxinhui
 */
@Data
public class RoleListVO {

    private Long id;

    private String name;

    private String code;

    private Long creator;

    private String createTime;

    private Long modifier;

    private String updateTime;

    private String permissionList;

}
