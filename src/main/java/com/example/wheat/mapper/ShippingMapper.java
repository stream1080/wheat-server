package com.example.wheat.mapper;

import com.example.wheat.entity.OrderItem;
import com.example.wheat.entity.Shipping;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
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
public interface ShippingMapper extends BaseMapper<Shipping> {

    @Delete("delete from shipping where id = #{id, jdbcType=INTEGER} and user_id = #{uid, jdbcType=INTEGER}")
    int deleteByIdAndByUid(@Param("uid") Integer uid,@Param("id") Integer id);

    @Select("SELECT * FROM shipping where user_id = #{uid,jdbcType=VARCHAR}")
    List<Shipping> selectByUid(Integer uid);

    @Select("SELECT * FROM shipping where user_id = #{uid,jdbcType=VARCHAR} and id = #{shippingId,jdbcType=VARCHAR}")
    Shipping selectByUidAndShippingId(@Param("uid") Integer uid,@Param("shippingId") Integer shippingId);

    @Select("<script>" +
            "select * from shipping where id in("
            +"<foreach collection='idSet' separator=',' item='item'>"
            + "#{item} "
            + "</foreach> "
            +")</script>")
    List<Shipping> selectByIdSet(@Param("idSet") Set idSet);
}
