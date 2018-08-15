package com.example.demo;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Component;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.confClass"})
@ComponentScan(basePackages = {"com.example.Service"})
@ComponentScan(basePackages = {"com.example.Controller"})
@ComponentScan(basePackages={"com.example.model"})
@ComponentScan(basePackages = {"com.example.Aspect"})
//@ComponentScan(basePackages={"com.example.Dao"})
@MapperScan("com.example.Dao")//与mybatis整合，自动扫描Mapper的包下所有mapper类
//多包扫描 @MapperScan("com.example.Dao"，"com.example.XXDao"，"org.xxx.xxDao")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
