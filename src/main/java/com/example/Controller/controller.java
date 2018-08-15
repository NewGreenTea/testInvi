package com.example.Controller;


import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

    //通过application.properties设置一些参数值
    @Value("${first.project.name}")
    private String name;

    @RequestMapping("/property")
    public String getNameandFeel(){
        //dio d=new dio();
        return this.name;
    }

    //-------------------------------------------
    //测试AOP编程的请求
    @RequestMapping(value="/say",method= RequestMethod.GET)
    public String sayHello(@RequestParam String name){
        return "hello wrold!"+name;
    }


}
