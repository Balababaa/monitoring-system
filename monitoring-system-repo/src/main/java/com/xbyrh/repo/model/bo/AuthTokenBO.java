package com.xbyrh.repo.model.bo;

import lombok.Data;

/**
 * create at 2021/4/16
 *
 * @author chenxinhui
 */
@Data
public class AuthTokenBO {

    private String accessToken;

    private Integer expiredIn;

    private String refreshToken;

}
