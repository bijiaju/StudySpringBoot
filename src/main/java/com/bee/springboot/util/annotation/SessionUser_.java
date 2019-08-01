package com.bee.springboot.util.annotation;

import java.lang.annotation.*;

/**
 * 注入接入方编号
 * @author weichunhe
 * created at 2018/11/28
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SessionUser_ {

}
