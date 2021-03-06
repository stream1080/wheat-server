package com.example.wheat.mapper;

import com.example.wheat.entity.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.wheat.entity.OrderItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author stream
 * @since 2021-06-04
 */
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    /**
     * 通过user_id查询订单
     * @param uid
     * @return
     */
    @Select("SELECT * FROM order_info where user_id = #{uid,jdbcType=VARCHAR}")
    List<OrderInfo> selectByUid(Integer uid);

    /**
     * 通过order_no查询订单
     * @param orderNo
     * @return
     */
    @Select("SELECT * FROM order_info where order_no = #{orderNo,jdbcType=VARCHAR}")
    OrderInfo selectByOrderNo(Long orderNo);

}
