package com.example.Service;

import com.example.model.student;
import com.example.model.tabl_dic;

import java.util.List;

public interface tabl_dicService {
    //所有明细表
    public List<tabl_dic> getAll();

    //所有学生
    public List<student> getAllStu();
}
