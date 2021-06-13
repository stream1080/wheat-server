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

    /**
     * 删除收货地址
     * @param uid
     * @param id
     * @return
     */
    @Delete("delete from shipping where id = #{id, jdbcType=INTEGER} and user_id = #{uid, jdbcType=INTEGER}")
    int deleteByIdAndByUid(@Param("uid") Integer uid,@Param("id") Integer id);

    /**
     * 通过user_id查询收货地址列表
     * @param uid
     * @return
     */
    @Select("SELECT * FROM shipping where user_id = #{uid,jdbcType=VARCHAR}")
    List<Shipping> selectByUid(Integer uid);

    /**
     * 通过user_id和shipping_id查询收货地址信息
     * @param uid
     * @param shippingId
     * @return
     */
    @Select("SELECT * FROM shipping where user_id = #{uid,jdbcType=VARCHAR} and id = #{shippingId,jdbcType=VARCHAR}")
    Shipping selectByUidAndShippingId(@Param("uid") Integer uid,@Param("shippingId") Integer shippingId);

    /**
     * 通过shippingId批量查询收货地址
     * @param idSet
     * @return
     */
    @Select("<script>" +
            "select * from shipping where id in("
            +"<foreach collection='idSet' separator=',' item='item'>"
            + "#{item} "
            + "</foreach> "
            +")</script>")
    List<Shipping> selectByIdSet(@Param("idSet") Set idSet);
}
