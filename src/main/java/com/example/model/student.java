package com.example.model;


import com.annotation.Invisible;
import com.constant.Constraint;

//测试切换数据源的测试实体类
//测试权限登录实体类，新增密码field -18/8/23
public class student {
    @Invisible(primaryKey = Constraint.PRIMARY_KEY)
    private Integer id;
    private String name;
    private Integer age;
    private String roleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
