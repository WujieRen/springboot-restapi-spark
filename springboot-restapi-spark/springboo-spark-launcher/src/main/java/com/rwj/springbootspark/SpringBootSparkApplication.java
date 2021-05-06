package com.rwj.springbootspark;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author rwj
 * @create_time 2021/4/24
 * @description
 */
@MapperScan(basePackages = {"com.rwj.springbootspark.dao"})
@ComponentScan(basePackages={"com.rwj.springbootspark.*"})
@SpringBootApplication
public class SpringBootSparkApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootSparkApplication.class, args);
    }
}
