package com.clientAda4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动程序
 *
 * @author ruoyi
 */
@SpringBootApplication
public class RuoYiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("服务启动成功");
    }

}
