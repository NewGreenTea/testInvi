package com.confClass;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@MapperScan("com.example.Dao")
@Configuration
public class dataSourceConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primarysoucre(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.second")
    public DataSource secondsource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSource dynamicdatasource(){
        DynamicDataSourceChange dynamicsource=new DynamicDataSourceChange();
        dynamicsource.setDefaultTargetDataSource(primarysoucre());

        //注意点，没有的话报错，targetSource没有，因为只设置了一个主数据源，其他数据源没有设置
        Map<Object,Object> datareslut=new HashMap<Object,Object>();
        datareslut.put(databaseSource.primarySource,primarysoucre());
        datareslut.put(databaseSource.secondSource,secondsource());
        dynamicsource.setTargetDataSources(datareslut);
        return dynamicsource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicdatasource());

        //此处设置为了解决找不到mapper文件的问题，预防万一Dao里的借口类没有
        /*sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(
                        "classpath*:com/example/Mapper/*.xml"));*/
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(dynamicdatasource());
    }
}
