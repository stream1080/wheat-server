package com.example.wheat.mapper;

import com.example.wheat.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.wheat.entity.User;
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
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 通过categoryId批量查询商品信息
     * @param categoryIdSet
     * @return
     */
    @Select("<script>" +
            "select * from product where status = 1 and category_id in("
            +"<foreach collection='categoryIdSet' separator=',' item='item'>"
            + "#{item} "
            + "</foreach> "
            +")</script>")
    List<Product> selectByCategoryIdSet(@Param("categoryIdSet") Set<Integer> categoryIdSet);

    /**
     * 通过productId批量查询商品信息
     * @param productIdSet
     * @return
     */
    @Select("<script>" +
            "select * from product where status = 1 and id in("
            +"<foreach collection='productIdSet' separator=',' item='item'>"
            + "#{item} "
            + "</foreach> "
            +")</script>")
    List<Product> selectByProductIdSet(@Param("productIdSet") Set<Integer> productIdSet);
}
