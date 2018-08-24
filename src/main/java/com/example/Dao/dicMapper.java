package com.example.Dao;

import com.example.Mapper.SimpleSelectLangPriver;
import com.example.model.student;
import com.example.model.tabl_dic;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

//@Mapper
//@Cacheable(cacheNames = "table")
public interface dicMapper {
    @Select("select * from tabl_dic")
    List<tabl_dic> getAll();

    @Select("select * from tabl_dic where table_name= #{name}")
    tabl_dic findByName(@Param("name") String name);

    @Insert("insert into tabl_dic(table_name,table_chn,father_tabl,table_type)"
   +" values (#{name},#{chn},#{father},#{type})")
    int insert(@Param("name") String name,@Param("chn") String chn,
               @Param("father") String father,@Param("type") String type);
    //除参数形式外，还可以是对象或是实体对象
    //int insert (tabl_dic dicEntity)；

    @Update("update tabl_dic set table_name=#{table_name},table_chn=#{table_chn},"
            +"table_type=#{table_type},father_tabl=#{father_tabl} where table_name=#{table_name}")
    void updata(tabl_dic dd);
    //更新一个表全部字段，这个太死板了。需要一个动态更新某字段

    @Delete("delete from tabl_dic where table_name=#{name}")
    void delete(String name);


    //返回一个自定义的newclass(vo类，视图类)，字段不是完整的实体映射类
    @Results({
        @Result(property = "newclass.name",column = "table_name"),
        @Result(property = "newclass.column",column="father_tabl")
        //column对应的是select的字段
        //property对应的是实体表中字段
    })
    @Select("select table_name,father_tabl from tabl_dic")
    List<tabl_dic> allTabl_dic();

    @Select("select * from studentCache")
    List<student> allStudent();


    //使用新的langDriver用法 -18/8/15
    @Select("select * from tabl_dic (#{table_type})")
    @Lang(SimpleSelectLangPriver.class)
    public List<tabl_dic> getAllDIC(tabl_dic dic);
    //在使用对象的时候，查询select里的参数字段，没有特别大的意义，反正会对对象的所有属性地段一个一个匹配

    //测试返回对象为null的情况
    @Select("select * from tabl_dic")
    public List<tabl_dic> getAlldic();


    //*************缓存相关********************
    //参数缓存
    //@Cacheable(value = "singleCache",key = "#p0")     //单参数形式OK
    @Select("select * from tabl_dic where table_name= #{dic}")
    public List<tabl_dic> getCace(String dic);

    //key的使用方法参数时我们可以直接使用“#参数名”或者“#p参数index”,自定义时使用springEL表达式

    //@Cacheable(value = "singleCache")   //不建议在dao这写缓存
    // 单参数VO类形式不行  “#dic ，#p0”都不行,"'dic'"可以
    //如果参数是类的话不通过，会报属性错误-18-8-22
    @Select("select * from tabl_dic (#{dic})")
    @Lang(SimpleSelectLangPriver.class)
    public List<tabl_dic> getVoCace(tabl_dic dic);

    //清除缓存

    @Select("select * from tabl_dic where table_name= #{table_name}")
    public List<tabl_dic> removeC(tabl_dic dic);
    @Select("select * from tabl_dic where table_name= #{table_name}")
    public List<tabl_dic> removeAll(tabl_dic dic);
}
