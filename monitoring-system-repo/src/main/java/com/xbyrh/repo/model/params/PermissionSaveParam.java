package com.xbyrh.repo.model.params;

import lombok.Data;

/**
 * create at 2021/5/14
 *
 * @author chenxinhui
 */
@Data
public class PermissionSaveParam {

    private String name;

    private String code;

    private Integer type;

    private String path;
}
