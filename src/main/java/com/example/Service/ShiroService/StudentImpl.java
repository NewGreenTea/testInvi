package com.example.Service.ShiroService;

import com.confClass.DynamicDataSourceContextholder;
import com.confClass.databaseSource;
import com.example.Dao.StudentMapper;
import com.example.Service.ShiroService.StudentService;
import com.example.model.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StudentImpl implements StudentService {
    @Autowired
    private StudentMapper dm;

    //@Cacheable(value = "shiCache",key = "1000")
    public student getOne(student s){
        DynamicDataSourceContextholder.setCurrentDatasource(databaseSource.secondSource);
        return dm.getone(s);
    }

    //返回对象结果集
    //@CachePut(value = "shiCache",key="1000")
    public student getOther(student s){
        DynamicDataSourceContextholder.setCurrentDatasource(databaseSource.secondSource);
        return dm.getone(s);
    }

    //返回integer结果集
    //@CachePut(value = "shiCache",key="1000")
    public Integer updateStu(student s){
        DynamicDataSourceContextholder.setCurrentDatasource(databaseSource.secondSource);
        Integer i=dm.updateOne(s).size();
        return i;
    }

    //清除缓存
    //@CacheEvict(value = "shiCache",key="1000")
    public void removeC(){}

    //********shiro*************
    public String getPassword(String name){
        DynamicDataSourceContextholder.setCurrentDatasource(databaseSource.secondSource);
        return dm.Password(name);
    }
    public String getRoleName(String name){
        DynamicDataSourceContextholder.setCurrentDatasource(databaseSource.secondSource);
        return dm.RoleName(name);
    }
}
