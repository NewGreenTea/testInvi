package com.example.demo;

import com.example.Service.tabl_dicService;
import com.example.model.student;
import com.example.model.tabl_dic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class dynamicsourceTest {
    @Autowired
    private tabl_dicService ser;

    @Test
    public void test(){
        tabl_dic d1=new tabl_dic();
        student d2=new student();
        d1=ser.getAll().get(20);
        d2=ser.getAllStu().get(1);

        System.out.println(d1.getTable_name()+"----"+d2.getAge());

    }
}
