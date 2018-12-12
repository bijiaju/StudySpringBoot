package com.bee.springboot.service;

import java.util.List;

import com.bee.springboot.entity.User;

public interface UserService {
    public List<User> getUserInfo();
    
    public void insert(User user);

    public User selectUserById(int id);
}
