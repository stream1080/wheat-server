package com.example.wheat.service.impl;

import com.example.wheat.entity.User;
import com.example.wheat.enums.ResponseEnum;
import com.example.wheat.enums.RoleEnum;
import com.example.wheat.mapper.UserMapper;
import com.example.wheat.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wheat.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.example.wheat.enums.ResponseEnum.EMAIL_EXIST;
import static com.example.wheat.enums.ResponseEnum.USWENAME_EXIST;

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
    public ResponseVo register(User user) {
//        error();
        //用户名username不能重复
        int countByUsername = userMapper.countByUsername(user.getUsername());
        if(countByUsername > 0 ){
//            throw new RuntimeException("该用户已注册");
            return ResponseVo.error(USWENAME_EXIST);
        }

        //手机号码不能重复
        int countByPhone = userMapper.countByPhone(user.getPhone());
        if(countByPhone > 0 ){
//            throw new RuntimeException("该用户已注册");
            return ResponseVo.error(USWENAME_EXIST);
        }

        //邮箱不能重复
        int countByEmail = userMapper.countByEmail(user.getEmail());
        if(countByEmail > 0 ){
//            throw new RuntimeException("该用户已注册");
            return ResponseVo.error(EMAIL_EXIST);
        }

        //普通用户注册
        user.setRole(RoleEnum.CUSTOMER.getCode());

        //MD5加密密码Spring自带
        user.setPassword(DigestUtils.md5DigestAsHex(
                user.getPassword().getBytes(StandardCharsets.UTF_8)));

        //写入数据库
        int resqltCount = userMapper.insert(user);
        if (resqltCount == 0) {
//            throw new RuntimeException("注册失败");
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        return ResponseVo.success();
    }

    private void error(){
        throw new RuntimeException("意外错误");
    }


    public List<User> getUserList() {
        return userMapper.selectList(null);
    }
}
