package com.bee.springboot.mapper;

import java.util.List;

import com.bee.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

	public List<User> findUserInfo();
	public int addUserInfo(User user);
	public int delUserInfo(int id);
	public User findOneUserInfo(int id);

	int dynaDeleteList(List<Integer> ids);
}
