package com.example.wheat.mapper;

import com.example.wheat.entity.OrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface OrderItemMapper extends BaseMapper<OrderItem> {

    /**
     * 通过orderNo批量查询订单列表
     * @param orderNoSet
     * @return
     */
    @Select("<script>" +
            "select * from order_info where order_no in("
            +"<foreach collection='orderNoSet' separator=',' item='item'>"
            + "#{item} "
            + "</foreach> "
            +")</script>")
    List<OrderItem> selectByOrderNoSet(@Param("orderNoSet") Set orderNoSet);

}
