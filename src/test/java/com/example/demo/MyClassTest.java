package com.example.demo;

import com.example.Controller.restfulCon;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//表示使用Spring Test组件进行单元测试
@RunWith(SpringJUnit4ClassRunner.class)
//老版的测试配置注解，已经转用新的注解SpringBootTest
//@SpringApplicationConfiguration(class=restfulCon.class)
@SpringBootTest(classes = restfulCon.class)
@WebAppConfiguration
//使用这个WebAppConfiguration会在跑单元测试的时候真实的启一个web服务
// 然后开始调用Controller的Rest API，待单元测试跑完之后再将web服务停掉
//@ContextConfiguration("classpath*:**web-config.xml")指定Bean的配置文件信息
public class MyClassTest {
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception{
        //通过参数指定一组控制器，这样就不需要从上下文获取
        mvc= MockMvcBuilders.standaloneSetup(new restfulCon()).build();
        //
        //mvc=MockMvcBuilders.webAppContextSetup();
    }

    @Test
    public void testController() throws Exception{
        RequestBuilder request=null;
        //MockMvcRequestBuilders.get("/user/")构造一个请求
        request=get("/User/");
        mvc.perform(request).andExpect(status().isOk());
        //看请求的状态响应码是否为200
    }
}
