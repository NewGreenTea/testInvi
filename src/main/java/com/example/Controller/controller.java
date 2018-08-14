package com.example.Controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

    //通过application.properties设置一些参数值
    @Value("${first.project.name}")
    private String name;

    @RequestMapping("/say")
    public String sayHello(){
        return "hello wrold!";
    }

    @RequestMapping("/property")
    public String getNameandFeel(){
        //dio d=new dio();
        return this.name;
    }
}
