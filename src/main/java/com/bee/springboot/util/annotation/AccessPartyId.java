package com.zx.sms.sale.resolver.annotation;

import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

import java.lang.annotation.*;

/**
 * 注入接入方编号
 *
 * @author weichunhe
 * created at 2018/11/28
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@ApiIgnore
@Documented
public @interface AccessPartyId{

}
