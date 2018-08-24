package com.example.Aspect;


import com.example.Controller.controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@Aspect//使用@Aspect注解会把一个普通的java类编程切面类
@Component
public class WebLogAspect {
    private Logger logger= LogManager.getLogger(controller.class);
    //Logger logger2=Logger.

    @Pointcut("execution(public * com.example.Controller..*.*(..))")
    public void weblog(){}

    @Before("weblog()")
    public void doBefore(JoinPoint j){
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();

        //Log
        logger.info("URL："+request.getRequestURL().toString());
        logger.info("HTTP_METHOD："+request.getMethod());
        logger.info("IP："+request.getRemoteAddr());
        logger.info("CLASS_METHOD："+j.getSignature().getDeclaringTypeName()+"-"+j.getSignature().getName());
        logger.info("ARGS："+ Arrays.toString(j.getArgs()));
    }

    @AfterReturning(returning ="smgui",pointcut = "weblog()")
    public void doAfterReturning(Object smgui){
        logger.info("Reponse:"+smgui);
    }
}
