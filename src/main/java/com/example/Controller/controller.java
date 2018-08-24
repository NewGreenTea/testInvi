package com.example.Controller;


import com.example.Service.tabl_dicSerImpl;
import com.example.model.tabl_dic;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class controller {
    @Autowired
    private tabl_dicSerImpl td;

    private tabl_dic tt=new tabl_dic();

    //通过application.properties设置一些参数值
    @Value("${first.project.name}")
    private String name;

    @RequestMapping("/property")
    public String getNameandFeel(){
        //dio d=new dio();
        return this.name;
    }

    //-------------------------------------------
    //测试AOP编程的请求
    @RequestMapping(value="/say",method= RequestMethod.GET)
    public String sayHello(@RequestParam String name){
        return "hello wrold!"+name;
    }

    //测试缓存ehcache
    //测试新用法sql
    @RequestMapping(value="/sqltest",method=RequestMethod.GET)
    public List<tabl_dic> getAllTable(){
        tabl_dic t=new tabl_dic();
        t.setTable_name("tabl_map_no");
        List<tabl_dic> l=td.getAllTable(t);
        return l;
    }

    //测试返回对象为NULL情况
    @RequestMapping(value="/nulltest",method=RequestMethod.GET)
    public List<tabl_dic> getAlldic(){
        List<tabl_dic> l=td.getAlldic();
        return l;
    }

    //------------------------------------------
    //测试参数缓存
    @RequestMapping(value = "/getCache",method = RequestMethod.GET)
    public List<tabl_dic> getCache(@RequestParam("ta")String t){
        //参数VO参数
        //tabl_dic t=new tabl_dic();
        //t.setTable_name("tabl_pipe_plan");
        tt.setTable_name(t);
        List<tabl_dic> l=td.getVoCace(tt);
        //测试String单参数

        //List<tabl_dic> l=td.getCace(t);
        return l;
    }

    //测试移除缓存
    @RequestMapping(value = "/removeCache",method = RequestMethod.GET)
    public List<tabl_dic> removeCache(@RequestParam("ta")String tt){
        tabl_dic t=new tabl_dic();
        t.setTable_name(tt);
        List<tabl_dic> l=td.removeCace(t);
        //List<tabl_dic> l=td.removeAll();
        //String t="tabl_pipe_plan";
        //List<tabl_dic> l=td.removeCace(t);
        return l;
    }

    @RequestMapping(value = "/getone",method = RequestMethod.GET)
    public List<tabl_dic> oneCache(@RequestParam("ta")String t){
        //参数VO参数
        //tabl_dic t=new tabl_dic();
        //t.setTable_name("tabl_pipe_plan");
        tt.setTable_name(t);
        List<tabl_dic> l=td.getone(tt);
        //测试String单参数

        //List<tabl_dic> l=td.getCace(t);
        return l;
    }
}
