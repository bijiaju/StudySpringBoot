package com.cwh.springbootMybatis.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cwh.springbootMybatis.entity.Person;
import com.cwh.springbootMybatis.util.CommonUtil;
import com.cwh.springbootMybatis.util.PageUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cwh.springbootMybatis.entity.User;
import com.cwh.springbootMybatis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
  
@RestController  //注解相当于@ResponseBody ＋ @Controller合在一起的作用
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	/**
	 * 这里有错误，需要接着修改
	 * @return
	 */
	@RequestMapping("/getUserInfo")
    public PageInfo getUserInfo() {
		PageUtil.startPage(0);
		List<User> users = userService.getUserInfo();
        System.out.println(users.toString());
        return new PageInfo((Page)users);
    }


	/**
	 * findAll
	 * Hibernate
	 * @return
	 */
	/*@RequestMapping("/getUserInfo")
	public PageInfo getUserInfo() {
		PageUtil.startPage(0);
		List<User> users = userService.getUserInfo();
		System.out.println(users.toString());
		return new PageInfo((Page)users);
	}*/

	/**
	 * 测试请求
	 * @return
	 */
	@RequestMapping("/testResult")
	public Map<String,Object> testResult() {
		logger.info("Start...");

		Map<String,Object> retMap = new HashMap<>();
		List<User> users = userService.getUserInfo();
		retMap.put("beans",users);
		return CommonUtil.setReturnMap("0","请求成功",retMap);
	}

	@RequestMapping("/addUserInfo")
    public String addUserInfo() {
		User user = new User();
		user.setId(3L);
		user.setName("cwh");
		userService.insert(user);
        return "success:"+user.toString();
    }

	@RequestMapping("/findOneUserInfo")
	public String addUserInfo(@RequestParam(value = "id", required = false) Integer id) {

		User user = userService.selectUserById(id);
		return "success:"+user.toString();
	}
	
}  
