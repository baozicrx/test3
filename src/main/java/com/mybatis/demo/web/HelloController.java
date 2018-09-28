package com.mybatis.demo.web;

import com.mybatis.demo.entity.User;
import com.mybatis.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/list")
    public String page(Model model, @RequestParam(value = "currPage",required = false,defaultValue = "1") Integer currPage){
        Map<String,Object> map=new HashMap<>();
        Integer pageSize=2;
        map.put("currIndex",(currPage-1)*pageSize);
        map.put("pageSize",pageSize);
        List<User> pages=userMapper.selectPage(map);
        model.addAttribute("pages",pages);
        model.addAttribute("currPage",currPage);
        return "list";
    }
}
