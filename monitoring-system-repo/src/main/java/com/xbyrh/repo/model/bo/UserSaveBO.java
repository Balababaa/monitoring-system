package com.xbyrh.repo.model.bo;

import lombok.Data;

/**
 * create at 2021/5/14
 *
 * @author chenxinhui
 */
@Data
public class UserSaveBO {

    private Long uid;

    private String username;

    private String nickname;

    private String email;

}
