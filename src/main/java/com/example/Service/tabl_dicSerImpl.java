package com.example.Service;

import com.confClass.DynamicDataSourceContextholder;
import com.confClass.databaseSource;
import com.example.Dao.dicMapper;
import com.example.model.student;
import com.example.model.tabl_dic;
import org.springframework.beans.factory.annotation.Autowired;
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
}
