package com.example.demo;

//import com.example.Dao.CacheConfiguration;
//import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.confClass"})
@ComponentScan(basePackages = {"com.example.Service"})
@ComponentScan(basePackages = {"com.example.Controller"})
@ComponentScan(basePackages={"com.example.model"})
@ComponentScan(basePackages = {"com.example.Aspect"})
@ComponentScan(basePackages={"com.shiro"})  //扫描shiro框架的包
@MapperScan("com.example.Dao")//与mybatis整合，自动扫描Mapper的包下所有mapper类
//多包扫描 @MapperScan("com.example.Dao"，"com.example.XXDao"，"org.xxx.xxDao")
@EnableCaching //开启缓存
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
