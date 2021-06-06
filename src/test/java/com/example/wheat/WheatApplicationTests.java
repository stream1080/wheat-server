package com.example.wheat;

import com.baomidou.mybatisplus.extension.api.Assert;
import com.example.wheat.entity.Cart;
import com.example.wheat.entity.User;
import com.example.wheat.enums.ResponseEnum;
import com.example.wheat.form.CartAddForm;
import com.example.wheat.mapper.UserMapper;
import com.example.wheat.service.CartService;
import com.example.wheat.service.CategoryService;
import com.example.wheat.service.ProductService;
import com.example.wheat.service.UserService;
import com.example.wheat.vo.ProductVo;
import com.example.wheat.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
@Slf4j
class WheatApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CartService cartService;

//    @Test
//    @Transactional
//    void contextLoads() {
//        User user = new User();
//        user.setUsername("jack");
//        user.setPassword("jack");
//        user.setEmail("jack@qq.com");
//        user.setRole(1);
//        userService.register(user);
//
////        List<User> users = userMapper.selectList(null);
////        users.forEach(System.out::println);
//    }

    @Test
    @Transactional
    void findSubCategoryId() {
        Set<Integer> set = new HashSet<>();
        categoryService.findSubCategoryId(2,set);
        log.info("Set={}",set);

    }


    @Test
    @Transactional
    void productTest() {
        ResponseVo<PageInfo> responseVo = productService.list(3, 1, 2);

//        List<User> users = userMapper.selectList(null);
//        responseVo.forEach(System.out::println);
    }

    @Test
    @Transactional
    void detail() {
        ResponseVo<PageInfo> responseVo = productService.list(3, 1, 2);
//        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(),responseVo.getStatus());
        log.info("detail={}",responseVo.getStatus());
    }


    @Test
    void addredis() {
        CartAddForm cartAddForm = new CartAddForm();
        cartAddForm.setProductId(3);
        cartAddForm.setSelected(true);
        cartService.add(1,cartAddForm);
    }

}
