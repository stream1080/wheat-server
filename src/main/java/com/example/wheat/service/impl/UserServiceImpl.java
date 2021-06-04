package com.example.wheat.service.impl;

import com.example.wheat.entity.User;
import com.example.wheat.mapper.UserMapper;
import com.example.wheat.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stream
 * @since 2021-06-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 注册
     * @param user
     */
    @Override
    public void register(User user) {

        //用户名username不能重复
        int countByUsername = userMapper.countByUsername(user.getUsername());
        if(countByUsername > 0 ){
            throw new RuntimeException("该用户已注册");
        }

        //手机号码不能重复
        int countByPhone = userMapper.countByPhone(user.getPhone());
        if(countByPhone > 0 ){
            throw new RuntimeException("该用户已注册");
        }

        //邮箱不能重复
        int countByEmail = userMapper.countByEmail(user.getEmail());
        if(countByEmail > 0 ){
            throw new RuntimeException("该用户已注册");
        }

        //MD5加密密码Spring自带
        user.setPassword(DigestUtils.md5DigestAsHex(
                user.getPassword().getBytes(StandardCharsets.UTF_8)));

        //写入数据库
        int resqltCount = userMapper.insert(user);
        if (resqltCount == 0) {
            throw new RuntimeException("注册失败");
        }
    }


    public List<User> getUserList() {
        return userMapper.selectList(null);
    }
}
