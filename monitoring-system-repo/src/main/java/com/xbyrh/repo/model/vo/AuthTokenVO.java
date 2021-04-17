package com.xbyrh.repo.model.vo;

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

}
