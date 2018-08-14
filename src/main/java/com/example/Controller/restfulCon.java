package com.example.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/User")
public class restfulCon {

    @RequestMapping(value = "/",method= RequestMethod.GET)
    public String getString(){
        return "requestmethod.get!";
    }

    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public String getId(@PathVariable int id){
        return "your id is:"+id;
    }
}
