package com.xbyrh.media;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * create at 2021/4/21
 *
 * @author chenxinhui
 */
@SpringBootApplication
public class MediaServerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MediaServerApplication.class)
                .web(false)
                .run(args);
    }

}
