package com.example.wheat.service;

import com.example.wheat.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wheat.vo.ResponseVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stream
 * @since 2021-06-04
 */
public interface UserService extends IService<User> {

    /**
     * 注册
     * @param user
     */
    ResponseVo<User> register(User user);


    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    ResponseVo<User> login(String username, String password);

    List<User> getUserList();

}
