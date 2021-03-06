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

    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String page(Model model, @RequestParam(value = "currPage",required = false,defaultValue = "1") Integer currPage){
        Map<String,Object> map=new HashMap<>();
        Integer pageSize=2;
        map.put("currIndex",(currPage-1)*pageSize);
        map.put("pageSize",pageSize);
        Integer lastPage=(int)Math.ceil(userMapper.selectAll().size()/2.0);
        List<User> pages=userMapper.selectPage(map);
        model.addAttribute("pages",pages);
        model.addAttribute("currPage",currPage);
        model.addAttribute("lastPage",lastPage);
        return "list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "userAdd";
    }

    @RequestMapping("/add")
    public String add(User user){
        userMapper.insert(user);
        return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model,Long id){
        User user=userMapper.selectByPrimaryKey(id);
        model.addAttribute("user",user);
        return "userEdit";
    }

    @RequestMapping("/edit")
    public String edit(User user){
        userMapper.updateByPrimaryKey(user);
        return "redirect:/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id){
        userMapper.deleteByPrimaryKey(id);
        return "redirect:/list";
    }

}
