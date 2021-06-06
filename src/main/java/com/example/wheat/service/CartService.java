package com.example.wheat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wheat.entity.OrderInfo;
import com.example.wheat.form.CartAddForm;
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

    ResponseVo<CartVo> add(Integer uid ,CartAddForm cartAddForm);

    ResponseVo<CartVo> list(Integer uid);

}
