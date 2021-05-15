package com.xbyrh.repo.model.bo;

import lombok.Data;

/**
 * create at 2021/5/14
 *
 * @author chenxinhui
 */
@Data
public class PermissionSaveBO {

    private String name;

    private String code;

    private Integer type;

    private String path;
}
