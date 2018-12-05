package com.cwh.springbootMybatis.service.impl;

import java.util.List;
//import java.util.logging.Logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cwh.springbootMybatis.entity.User;
import com.cwh.springbootMybatis.mapper.UserMapper;
import com.cwh.springbootMybatis.service.UserService;

/**
 * 1.内部测试了缓存功能
 */
@Service
public class UserServiceImpl implements UserService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserMapper userMapper;
    @Cacheable(value = "user1",key="methodName")//3
    public List<User> getUserInfo(){
        logger.info("开始查询数据");
        List<User> result = userMapper.findUserInfo();
        System.out.println("为id、key为:"+result.toString()+"数据做了缓存");
        return result;
    }

    //@Transactional开启事务
    @Transactional
    @CachePut(value = "user")
	public void insert(User user) {
		int count = userMapper.addUserInfo(user);
        System.out.println("为id、key为:"+user.getId()+"数据做了缓存");
	/*	int i=1/0;
		userMapper.addUserInfo(user);*/
		
	}

    /**
     * 请求地址：　　http://localhost:8080/user/findOneUserInfo?id=1
     * 配置缓存：
     * 1.application导入配置文件
     * 2.加入要配置的cache
     * 3.注释加Cacheable,  如果不传参数，key可为methodName；传参就为参数的变量id；当然也可以不传
     * @param id
     * @return
     */
    @Override
    @Cacheable(value = "user1",key="#id")
    public User selectUserById(int id) {
       User user =  userMapper.findOneUserInfo(id);
        System.out.println("为id、key为:"+user.getId()+"数据做了缓存");
        return user;
    }
}
