package com.example.wheat.service;

import com.example.wheat.entity.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wheat.vo.OrderVo;
import com.example.wheat.vo.ResponseVo;
import com.github.pagehelper.PageInfo;

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

    /**
     * 查询订单列表
     * @param uid
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResponseVo<PageInfo> list(Integer uid, Integer pageNum, Integer pageSize);

    /**
     * 查询订单详情
     * @param uid
     * @param orderNo
     * @return
     */
    ResponseVo<OrderVo> detail(Integer uid, Long orderNo);

    /**
     * 取消订单
     * @param uid
     * @param orderNo
     * @return
     */
    ResponseVo<OrderVo> cance(Integer uid, Long orderNo);



}
