package com.example.Dao;

import com.example.Mapper.SimpleSelectLangPriver;
import com.example.Mapper.SimpleUpdateLangDriver;
import com.example.model.student;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentMapper {

    @Select("select * from studentCache (#{stu})")
    @Lang(SimpleSelectLangPriver.class)
    public student getone(student stu);

    @Select("update studentCache (#{stu}) where id=#{id}")
    @Lang(SimpleUpdateLangDriver.class)
    public List<student> updateOne(student stu);

    //*************shiro权限**************
    @Select("select password from student where name=#{name}")
    public String Password(String name);

    @Select("select roleName from student where name=#{name}")
    public String RoleName(String name);
}
