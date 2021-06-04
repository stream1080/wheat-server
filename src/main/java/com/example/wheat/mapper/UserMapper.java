package com.example.wheat.mapper;

import com.example.wheat.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stream
 * @since 2021-06-04
 */
public interface UserMapper extends BaseMapper<User> {


    @Select("SELECT count(1) FROM user where username = #{email,jdbcType=VARCHAR}")
    int countByEmail(String email);

    @Select("SELECT count(1) FROM user where username = #{phone,jdbcType=VARCHAR}")
    int countByPhone(String phone);

    @Select("SELECT count(1) FROM user where username = #{username,jdbcType=VARCHAR}")
    int countByUsername(String username);

}
