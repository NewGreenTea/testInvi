package com.annotation;


import com.constant.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author hayate
 * @Date 2018年8月17日 10:23
 *
 * 自定义的注解, 用于排除多余的变量（自定义注解，过滤多余字段）
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Invisible {
    /**
     * 约束
     *
     * @return
     */
    public int primaryKey() default Constraint.NO;
}
