package com.lmxdawn.him.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 登录验证的注解
 */
//此注解只能修饰方法
@Target(ElementType.METHOD)
//当前注解如何去保持
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckLoginAnnotation {
    String value() default "";
}
