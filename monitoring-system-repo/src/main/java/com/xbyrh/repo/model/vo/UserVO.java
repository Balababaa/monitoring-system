package com.xbyrh.repo.model.vo;

import lombok.Data;

/**
 * created on 2021/4/14.
 *
 * @author chenxinhui
 */

@Data
public class UserVO {

    private Long uid;

    private String username;

    private String nickname;

    private String password;

    private String email;

    private String avatar;

}
