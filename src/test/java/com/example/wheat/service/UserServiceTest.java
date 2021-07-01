package com.example.wheat.service;

import com.example.wheat.WheatApplicationTests;
import com.example.wheat.entity.User;
import com.example.wheat.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 用户模块单元测试
 */
@Slf4j
class UserServiceTest extends WheatApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    @Transactional
    void register() {
        User user = new User();
        user.setUsername("Tom");
        user.setPassword("Tom");
        user.setEmail("Tom@qq.com");
        user.setRole(1);
        ResponseVo<User> resUser = userService.register(user);
        log.info("resUser={}",resUser);
    }

    @Test
    void login() {
        ResponseVo<User> user = userService.login("jack","jack");
        log.info("User={}",user);
    }
}