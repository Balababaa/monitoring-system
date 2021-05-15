package com.xbyrh.repo.model.bo;

import com.xbyrh.repo.entity.Permission;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * create at 2021/5/14
 *
 * @author chenxinhui
 */
@Data
public class RoleBO {

    private Long id;

    private String name;

    private String code;

    private Long modifier;

    private Long creator;

    private Date createTime;

    private Date updateTime;

    private List<Permission> permissionList = new ArrayList<>();

}
