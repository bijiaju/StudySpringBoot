package com.bee.springboot.util.annotation;

import springfox.documentation.annotations.ApiIgnore;

import java.lang.annotation.*;

/**
 * 注入接入方编号
 *
 * @author bee
 * created at 2018/11/28
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@ApiIgnore
@Documented
public @interface AccessPartyId{

}
