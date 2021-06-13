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


    /**
     * 查询邮箱是否存在
     * @param email
     * @return
     */
    @Select("SELECT count(1) FROM user where username = #{email,jdbcType=VARCHAR}")
    int countByEmail(String email);

    /**
     * 查询电话号码是否存在
     * @param phone
     * @return
     */
    @Select("SELECT count(1) FROM user where username = #{phone,jdbcType=VARCHAR}")
    int countByPhone(String phone);

    /**
     * 查询username是否存在
     * @param username
     * @return
     */
    @Select("SELECT count(1) FROM user where username = #{username,jdbcType=VARCHAR}")
    int countByUsername(String username);

    /**
     * 通过username查询用户信息
     * @param username
     * @return
     */
    @Select("SELECT * FROM user where username = #{username,jdbcType=VARCHAR}")
    User seletByUsername(String username);

}
