package com.xbyrh.repo.model.vo;

import lombok.Data;

/**
 * create at 2021/5/13
 *
 * @author chenxinhui
 */
@Data
public class UserListVO {

    private Long uid;

    private String username;

    private String nickname;

    private String email;

    private Integer isDelete;

    private String roleList;

    private String createTime;

    private String updateTime;

}
