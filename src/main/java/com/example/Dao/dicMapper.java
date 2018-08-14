package com.example.Dao;

import com.example.model.student;
import com.example.model.tabl_dic;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Mapper
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

    @Select("select * from student")
    List<student> allStudent();
}
