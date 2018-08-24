package com.example.Service;

import com.example.model.student;
import com.example.model.tabl_dic;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;


public interface tabl_dicService {
    //所有明细表
    public List<tabl_dic> getAll();

    //所有学生
    public List<student> getAllStu();

    //新用法所有明细表
    public List<tabl_dic> getAllTable(tabl_dic dic);

    //测试返回对象为null的情况
    public List<tabl_dic> getAlldic();

    //***********缓存相关**************
    //测试单参数String
    public List<tabl_dic> getCace(String dic);
    //测试单参数Vo
    public List<tabl_dic> getVoCace(tabl_dic dic);

    public List<tabl_dic> removeCace(tabl_dic dic);

    public List<tabl_dic> removeAll();

    public List<tabl_dic> getone(tabl_dic dic);
}
