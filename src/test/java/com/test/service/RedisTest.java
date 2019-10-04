
package com.test.service;

import com.bee.springboot.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)//设置启动类
public class RedisTest {


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testInsert() {
       redisTemplate.opsForValue().set("mykey","myValue");//存储字符串
    }

    @Test
    public void testQuery() {

    }

}

