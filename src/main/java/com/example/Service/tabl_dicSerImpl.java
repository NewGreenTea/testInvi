package com.example.Service;

import com.confClass.DynamicDataSourceContextholder;
import com.confClass.databaseSource;
import com.example.Dao.dicMapper;
import com.example.model.student;
import com.example.model.tabl_dic;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class tabl_dicSerImpl implements tabl_dicService {
    @Autowired
    private dicMapper dm;

    @Override
    @Transactional
    public List<tabl_dic> getAll() {
        DynamicDataSourceContextholder.setCurrentDatasource(databaseSource.primarySource);
        return dm.getAll();
    }

    @Override
    public List<student> getAllStu(){
        DynamicDataSourceContextholder.setCurrentDatasource(databaseSource.secondSource);
        return dm.allStudent();
    }

    //新用法所有明细表
    public List<tabl_dic> getAllTable(tabl_dic dic){
        DynamicDataSourceContextholder.setCurrentDatasource(databaseSource.primarySource);
        return dm.getAllDIC(dic);
    }

    //测试返回对象为null的情况
    @Cacheable(value="shiCache")
    public List<tabl_dic> getAlldic(){
        DynamicDataSourceContextholder.setCurrentDatasource(databaseSource.primarySource);
        return dm.getAlldic();
    }

    //***********缓存相关**************
    //-18-8-22缓存最好放在serviceImpl实现方法类中
    //如果没有参数的方法下没有指定缓存的key的话，在删除的时候，给key="0"也没用，默认方式不知道，不是0
    //@Cacheable(value = "singleCache",key = "#t_name")    //如果是参数，则取参数
    public List<tabl_dic> getCace(String t_name){
        DynamicDataSourceContextholder.setCurrentDatasource(databaseSource.primarySource);
        return dm.getCace(t_name);
    }
    @Cacheable(value = "shiCache",key = "1000")
    public List<tabl_dic> getone(tabl_dic dic){
        DynamicDataSourceContextholder.setCurrentDatasource(databaseSource.primarySource);
        return dm.getVoCace(dic);
    }
//    @Cacheable(value = "singleCache",key="#p0.table_name")   //缓存的key取类的属性,取唯一值最佳,含有方法
    @CachePut(value="shiCache",key = "1000")
    public List<tabl_dic> getVoCace(tabl_dic dic){
        DynamicDataSourceContextholder.setCurrentDatasource(databaseSource.primarySource);
        return dm.getVoCace(dic);
    }

    @Override
    @CacheEvict(value="shiCache" ,key="'10'")
    public List<tabl_dic> removeCace(tabl_dic dic) {
        DynamicDataSourceContextholder.setCurrentDatasource(databaseSource.primarySource);
        return dm.removeC(dic);
    }

    //@CacheEvict(value = "singleCache",key="#uu",beforeInvocation = true)
    public List<tabl_dic> removeCace(String uu){
        tabl_dic temp=new tabl_dic();
        temp.setTable_name(uu);
        DynamicDataSourceContextholder.setCurrentDatasource(databaseSource.primarySource);
        return dm.removeC(temp);
    }
    @CacheEvict(cacheNames = "shiCache",beforeInvocation = true)
    // 全清除属性 ：allEntries = true
    public List<tabl_dic> removeAll(){
        DynamicDataSourceContextholder.setCurrentDatasource(databaseSource.primarySource);
        tabl_dic temp=new tabl_dic();
        return dm.removeC(temp);
    }
}
