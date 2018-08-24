package com.example.Controller.shiroController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guest")
public class GuestCont {
    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public String getMessage() {
        return "您是guest，无权获得该接口的信息！";
    }
}
