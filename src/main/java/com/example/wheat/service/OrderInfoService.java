package com.example.wheat.service;

import com.example.wheat.entity.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wheat.vo.ResponseVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stream
 * @since 2021-06-04
 */
public interface OrderInfoService extends IService<OrderInfo> {

    /**
     * 创建订单
     * @param uid
     * @param shippingId
     * @return
     */
    ResponseVo<OrderInfo> create(Integer uid,Integer shippingId);

}
