package com.zx.sms.sale.resolver.annotation;

import springfox.documentation.annotations.ApiIgnore;

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
