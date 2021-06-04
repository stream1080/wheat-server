package com.example.wheat.service;

import com.example.wheat.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

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
    void register(User user);

    List<User> getUserList();

}
