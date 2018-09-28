package com.mybatis.demo.web;

import com.mybatis.demo.entity.User;
import com.mybatis.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/getUsers")
    public List<User> getUsers(){
        List<User> users= userMapper.selectAll();
        return users;
    }

    @RequestMapping("/add")
    public void save(User user){
        userMapper.insert(user);
    }

    @RequestMapping("/getUser")
    public User getUser(Long id){
        User user= userMapper.selectByPrimaryKey(id);
        return user;
    }
}
