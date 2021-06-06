package com.example.wheat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wheat.entity.OrderInfo;
import com.example.wheat.form.CartAddForm;
import com.example.wheat.form.CartUptadtForm;
import com.example.wheat.vo.CartVo;
import com.example.wheat.vo.ResponseVo;
import io.swagger.models.auth.In;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stream
 * @since 2021-06-04
 */
public interface CartService {

    /**
     * 添加购物车
     * @param uid
     * @param cartAddForm
     * @return
     */
    ResponseVo<CartVo> add(Integer uid ,CartAddForm cartAddForm);

    /**
     * 查询购物车
     * @param uid
     * @return
     */
    ResponseVo<CartVo> list(Integer uid);

    /**
     * 更新购物车
     * @param uid
     * @param productId
     * @param cartUptadtForm
     * @return
     */
    ResponseVo<CartVo> update(Integer uid, Integer productId, CartUptadtForm cartUptadtForm);

    /**
     * 删除购物车商品
     * @param uid
     * @param productId
     * @return
     */
    ResponseVo<CartVo> delete(Integer uid, Integer productId);

    /**
     * 全选
     * @param uid
     * @return
     */
    ResponseVo<CartVo> selectAll(Integer uid);

    /**
     * 全不选
     * @param uid
     * @return
     */
    ResponseVo<CartVo> unSelectAll(Integer uid);

    /**
     * 商品总和
     * @param uid
     * @return
     */
    ResponseVo<Integer> sum(Integer uid);

}
