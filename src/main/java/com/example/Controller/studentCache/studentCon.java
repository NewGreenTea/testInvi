package com.example.Controller.studentCache;

import com.example.Service.ShiroService.StudentService;
import com.example.model.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class studentCon {
    @Autowired
    private StudentService ss;

    @RequestMapping(value = "/getStu",method= RequestMethod.GET)
    public student getOne(@RequestParam("id")Integer id){
        student temp=new student();
        temp.setId(id);
        return ss.getOne(temp);
    }

    @RequestMapping(value = "/getOStu",method= RequestMethod.GET)
    public student getOther(@RequestParam("id")Integer id){
        student temp=new student();
        temp.setId(id);
        temp.setAge(99);
        return ss.getOther(temp);
    }

    @RequestMapping(value = "/getUp",method= RequestMethod.GET)
    public Integer getUpdate(@RequestParam("id")Integer id){
        student temp=new student();
        temp.setId(id);
        temp.setAge(99);
        return ss.updateStu(temp);
    }

    @RequestMapping(value = "/remove",method= RequestMethod.GET)
    public String removeCache(){
        ss.removeC();
        return "remove Cache!";
    }
}
