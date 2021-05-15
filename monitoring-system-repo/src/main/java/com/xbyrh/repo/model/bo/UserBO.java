package com.xbyrh.repo.model.bo;

import com.xbyrh.repo.entity.Role;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * create at 2021/5/14
 *
 * @author chenxinhui
 */
@Data
public class UserBO {

    private Long id;

    private Long uid;

    private String username;

    private String nickname;

    private String password;

    private String email;

    private String avatar;

    private Integer isDelete;

    private Date createTime;

    private Date updateTime;

    private List<Role> roleList;

}
