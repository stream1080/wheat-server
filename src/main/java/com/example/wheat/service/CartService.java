package com.example.wheat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wheat.entity.OrderInfo;
import com.example.wheat.form.CartAddForm;
import com.example.wheat.form.CartUptadtForm;
import com.example.wheat.vo.CartVo;
import com.example.wheat.vo.ResponseVo;

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

}
