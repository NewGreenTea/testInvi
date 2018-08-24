package com.shiro.configuration;

import com.shiro.Realm.MyRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;

//Shiro的配置类，以前是xml文件，现在都是注解和java类取替代
//18/8/23

//并没有缓存管理器  -18/8/24
@Configuration
public class ShiroConfiguration {

    /**
     * 自定义身份认证 realm;
     * 必须写这个类，并加上 @Bean 注解，目的是注入 shiroRealm，
     * 否则会影响 shiroRealm 类中其他类的依赖注入
     */
    @Bean
    public MyRealm shiroRealm(){
        MyRealm mReal=new MyRealm();
        return mReal;
    }

    /**
     * 注入 securityManager
     * 安全管理器
     */
    @Bean
    public SecurityManager securitymanager(){
        DefaultWebSecurityManager sm=new DefaultWebSecurityManager();
        sm.setRealm(shiroRealm());  //参数是上一个方法shiroRealm
        return sm;
    }


    /**
     * shiro的web过滤器
     * @param sm
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager sm){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        //必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(sm);
        //setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
        shiroFilterFactoryBean.setLoginUrl("/login.html");

        //登录成功跳转的页面（显示全部功能，但是在功能模块的uri会拦截...要不每个角色的功能界面不同）
        //建议是  动态加载功能模块
        shiroFilterFactoryBean.setSuccessUrl("/manger.html");
        // 设置无权限时跳转的 url;
        shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");

        // 设置拦截器
        Map<String,String> filterChainDefinitionMap =new LinkedHashMap<>();
        //游客，开发权限
        filterChainDefinitionMap.put("/guest/**", "anon");
        //用户，需要角色权限 “user”
        filterChainDefinitionMap.put("/user","roles[teacher]");
        //管理员，需要角色权限 “admin”
        filterChainDefinitionMap.put("/admin/**","roles[admin]");
        //开放登陆接口
        filterChainDefinitionMap.put("/login", "anon");

        filterChainDefinitionMap.put("/User/logindemo", "anon");

        filterChainDefinitionMap.put("/User/login", "anon");
        //其余接口一律拦截
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    /**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
