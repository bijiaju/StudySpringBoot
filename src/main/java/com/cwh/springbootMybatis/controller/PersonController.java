package com.cwh.springbootMybatis.controller;

import com.cwh.springbootMybatis.dao.PersonRepository;
import com.cwh.springbootMybatis.entity.Person;
import com.cwh.springbootMybatis.entity.User;
import com.cwh.springbootMybatis.mapper.PersonMapper;
import com.cwh.springbootMybatis.service.UserService;
import com.cwh.springbootMybatis.util.CommonUtil;
import com.cwh.springbootMybatis.util.PageUtil;
import com.cwh.springbootMybatis.util.response.CommonResponse;
import com.cwh.springbootMybatis.util.response.ResponseStatus;
import com.cwh.springbootMybatis.util.response.ResponseStatusEnum;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController  //注解相当于@ResponseBody ＋ @Controller合在一起的作用
@RequestMapping(value = "/person")
@Api(tags = "账户信息相关")
public class PersonController {
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PersonMapper personMapper;

	@RequestMapping(path = "/addPerson")
	public CommonResponse addPerson(Person person) {
		//ResponseStatus status =
		try{
			person.setAge(37);//
			personRepository.save(person);
		}catch (Exception e){
			return CommonResponse.failResponse(ResponseStatus.fail("fail"));
		}

		return CommonResponse.successResponse(null);

		/*for(int i=0;i<10;i++){
			person.setName("bee"+i);
			person.setAge(15);
			personRepository.save(person);
		}*/

	}

	@DeleteMapping(path = "/deletePerson")
	public void deletePerson(Long id) {
		//personRepository.delete(id);
	}

	/**
	 * 这里有错误，需要接着修改
	 * @return
	 */
	@RequestMapping("/findAll")
	@ApiOperation("获取有所的用户")
	public CommonResponse getAllPerson() {

		PageUtil.startPage(0);
		List<Person> p =  personMapper.queryAll();
		System.out.println(p+"---------------------------");
		//System.out.println(p+"---------------------------");
		return CommonResponse.successPage(p);
	}
	
}  
