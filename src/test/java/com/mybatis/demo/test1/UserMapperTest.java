package com.mybatis.demo.test1;

import com.mybatis.demo.entity.User;
import com.mybatis.demo.entity.Users;
import com.mybatis.demo.mapper.UserMapper;
import com.mybatis.demo.mapper.UsersMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void testQuery() throws Exception{
        List<User> lists=userMapper.selectAll();
        System.out.println("分页前的数据"+lists.toString());
        Integer currPage=2;
        Integer pageSize=1;
        Integer firstIndex=(currPage - 1) * pageSize;
        Integer lastIndex=currPage*pageSize;
        List<User>pageList=lists.subList(firstIndex,lastIndex);
        System.out.println("分页后的数据"+pageList.toString());
    }

    @Test
    public void testPage() throws Exception{
        Integer currPage=3;
        Integer pageSize=2;
        Map<String,Object> map=new HashMap();
        map.put("currIndex",(currPage-1)*pageSize);
        map.put("pageSize",pageSize);
        List<Users> lists=usersMapper.selectPage(map);
        System.out.println(lists.toString());
    }

    @Test
    public void testInsert() throws Exception{
        User user=new User("bz","123123",20,"bbzz");
        userMapper.insert(user);
        //Assert.assertEquals(11,userMapper.selectAll().size());
    }
}
