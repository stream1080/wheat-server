package com.example.wheat;

import com.example.wheat.entity.User;
import com.example.wheat.mapper.UserMapper;
import com.example.wheat.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WheatApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        User user = new User();
        user.setUsername("jack");
        user.setPassword("jack");
        user.setEmail("jack@qq.com");
        user.setRole(1);
        userService.register(user);

//        List<User> users = userMapper.selectList(null);
//        users.forEach(System.out::println);
    }

}
