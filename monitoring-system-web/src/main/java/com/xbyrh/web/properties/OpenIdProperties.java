package com.xbyrh.web.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created on 2021/4/12.
 *
 * @author chenxinhui
 */

@Data
@Component
@ConfigurationProperties(prefix = "open-id")
public class OpenIdProperties {

    private String url;

    private String appId;

    private String secret;

}
