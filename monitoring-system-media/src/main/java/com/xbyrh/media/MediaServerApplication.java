package com.xbyrh.media;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * create at 2021/4/21
 *
 * @author chenxinhui
 */
@SpringBootApplication(scanBasePackages = "com.xbyrh")
@MapperScan("com.xbyrh.repo.mapper")
public class MediaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediaServerApplication.class, args);
    }

}
