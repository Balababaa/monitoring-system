package com.xbyrh.web.model.dto;

import lombok.Data;

/**
 * Created on 2021/4/12.
 *
 * @author chenxinhui
 */

@Data
public class OpenIdDTO {

    private String accessToken;

    private Long expiresIn;

    private String refreshToken;

    private String openid;

    private String scope;

    private String unionid;

}
