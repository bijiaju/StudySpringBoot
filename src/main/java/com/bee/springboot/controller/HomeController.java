package com.bee.springboot.controller;

import com.bee.springboot.util.response.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author bee
 * created at 2018/11/16
 */
@RequestMapping(value = "/home")
@RestController
@Api(tags = "登录相关")
public class HomeController {

    static Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @PostMapping("/login")
    @ApiOperation("登录")
    public CommonResponse login(HttpServletRequest request, @RequestParam String telephone, @RequestParam String password, @RequestParam String verifyCode) {

        return null;
    }

}
