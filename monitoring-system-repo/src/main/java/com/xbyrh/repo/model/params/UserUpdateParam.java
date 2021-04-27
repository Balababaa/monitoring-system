package com.xbyrh.repo.model.params;

import lombok.Data;

/**
 * create at 2021/4/15
 *
 * @author chenxinhui
 */

@Data
public class UserUpdateParam {

    private String nickname;

    private String avatar;

    private String password;

}
