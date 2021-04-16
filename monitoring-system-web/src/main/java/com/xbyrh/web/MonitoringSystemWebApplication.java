package com.xbyrh.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author XinHui Chen
 * @crea 2021/3/19 20:16
 */

@SpringBootApplication(scanBasePackages = "com.xbyrh")
@MapperScan("com.xbyrh.repo.mapper")
public class MonitoringSystemWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonitoringSystemWebApplication.class, args);
    }
}
