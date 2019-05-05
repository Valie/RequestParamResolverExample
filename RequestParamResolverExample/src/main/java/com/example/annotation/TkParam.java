package com.example.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 自定义参数注解
 * @Author WeiLi
 **/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface TkParam {

    /**
     * 参数别名
     */
    @AliasFor("name")
    String value() default "";
    /**
     * 参数别名
     */
    @AliasFor("value")
    String name() default "";
    /**
     * 是否必填
     */
    boolean required() default true;
}
