package com.example.Controller;

import com.example.Service.ShiroService.StudentService;
import com.example.model.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping(value = "/User")
public class restfulCon {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getString() {
        return "requestmethod.get!";
    }

    //使用@PathVariable和@PathParam注解比较好
    @RequestMapping(value = "/stuId/{id}", method = RequestMethod.GET)
    public String getId(@PathVariable int id) {
        return "your id is:" + id;
    }

    @GetMapping("/stuN/{name}")
    @ResponseBody
    public student getOne(@PathVariable(value = "name") String name) {
        student temp = new student();
        temp.setName(name);
        return studentService.getOne(temp);
    }

    @RequestMapping("/logindemo")
    public ModelAndView newView(){
        ModelAndView m=new ModelAndView();
        m.setViewName("noPermission");
        return m;
    }

    @RequestMapping("/demo")
    public String newDemo(){
        return "login";
    }
}
