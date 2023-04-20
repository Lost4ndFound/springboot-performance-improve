package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: lsw
 * @date: 2023/4/19 13:21
 */
@SpringBootApplication
// 使用 @ComponentScan() 定位扫包比 @SpringBootApplication 扫包更快
@ComponentScan("com.study")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
