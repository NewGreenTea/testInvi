package com.example.demo;

import com.example.Dao.dicMapper;
import com.example.model.tabl_dic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
//@Transactional
public class mybatisTest {
    @Autowired
    //@Resource
    private dicMapper dic;

    @Test
    //@Rollback
    @Transactional
    //SPRING的事务管理注解,保证数据操作的完整性
    public void testMybatis()throws Exception{
        //dic.insert("dd1","dd2","dd3","dd4");
        System.out.println("insert ok?");
        tabl_dic f=dic.findByName("dd1");//多次执行会报找到两条记录的错误
        System.out.println(f.getTable_type());

        tabl_dic dd=new tabl_dic();
        dd.setFather_tabl(f.getFather_tabl());
        dd.setTable_chn(f.getTable_chn());
        dd.setTable_name(f.getTable_name());
        dd.setTable_type("ff1");

        dic.updata(dd);
        f=dic.findByName("dd1");
        System.out.println(f.getTable_type());
    }

    /*@Test
    //@Rollback
    @Transactional
    //SPRING的事务管理注解,保证数据操作的完整性，
    // 例如在插入数据时，中间发生错误，导致发生错误之前的执行好的操作都会被还原
    //以下成功插入的dd2，dd3，dd4都没有实际插入数据库中
    // @Transactional一般用在service层
    public void testMybatis()throws Exception {
        dic.insert("dd2", "dd2", "dd3", "dd4");
        dic.insert("dd3", "dd2", "dd3", "dd4");
        dic.insert("dd4", "dd2", "dd3", "dd4");
        Exception e = new Exception("sss");
        throw e;
    }*/
}
