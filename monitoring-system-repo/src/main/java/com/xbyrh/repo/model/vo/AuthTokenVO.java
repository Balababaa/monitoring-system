package com.xbyrh.repo.model.vo;

import com.xbyrh.repo.entity.User;
import lombok.Data;

/**
 * create at 2021/4/16
 *
 * @author chenxinhui
 */
@Data
public class AuthTokenVO {

    private String accessToken;

    private Integer expiredIn;

    private String refreshToken;

    private User user;

}
