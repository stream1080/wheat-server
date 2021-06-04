package com.example.wheat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.wheat.mapper")
@SpringBootApplication
public class WheatApplication {

    public static void main(String[] args) {
        SpringApplication.run(WheatApplication.class, args);
    }

}
