package com.example.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


//demo测试实体类
@Component
public class dio {

    @Value("${first.project.name}")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeel() {
        return feel;
    }

    public void setFeel(String feel) {
        this.feel = feel;
    }

    @Value("${first.project.feel}")
    private String feel;

    public String toString(){
        return this.getName()+"-"+this.getFeel();
    }
}
