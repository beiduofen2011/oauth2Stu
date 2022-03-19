package com.fen.dou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@MapperScan("com.fen.dou.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(DemoApplication.class, args);
    }
}
