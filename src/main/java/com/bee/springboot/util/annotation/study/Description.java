package com.bee.springboot.util.annotation;


import java.lang.annotation.*;

/**
 * 声明注解
 */
//以下都是源注解
@Target({ElementType.TYPE,ElementType.METHOD})//在类和方法层面能用
@Retention(RetentionPolicy.RUNTIME)//运行时注解
@Inherited   //只会继承父类，不会实现父类
@Documented
public @interface Description {
    String desc();

    String author();

    int age() default 18;
}
