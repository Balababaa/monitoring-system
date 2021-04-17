package com.xbyrh.service.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * create at 2021/4/16
 *
 * @author chenxinhui
 */
@Data
@Component
@ConfigurationProperties(prefix = "access-token")
public class AccessTokenProperties {

    private Integer expireTime;

}
