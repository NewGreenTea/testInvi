package com.example.Service.ShiroService;

import com.example.model.student;

public interface StudentService {

    public student getOne(student s);

    public student getOther(student s);

    public Integer updateStu(student s);

    public void removeC();

    //*****************shiro权限测试*****************
    public String getPassword(String name);

    public String getRoleName(String name);
}
