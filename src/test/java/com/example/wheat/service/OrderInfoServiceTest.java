package com.example.wheat.service;

import com.example.wheat.WheatApplicationTests;
import com.example.wheat.enums.ResponseEnum;
import com.example.wheat.vo.OrderVo;
import com.example.wheat.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 订单模块单元测试类
 */
@Transactional
@DisplayName("订单模块单元测试")
class OrderInfoServiceTest extends WheatApplicationTests {


    @Autowired
    private OrderInfoService orderInfoService;

    @Test
    @DisplayName("测试创建订单")
    void create() {

        final ResponseVo<OrderVo> orderVoResponseVo = orderInfoService.create(5, 1);
        assertEquals(ResponseEnum.CART_SELECTED_IS_EMPTY.getCode(),orderVoResponseVo.getStatus());
    }

    @Test
    @DisplayName("测试查询订单")
    void list() {
        final ResponseVo<PageInfo> list = orderInfoService.list(5, 1, 4);
        assertEquals(ResponseEnum.SUCCESS.getCode(),list.getStatus());
    }

    @Test
    @DisplayName("测试查询订单详情")
    void detail() {
        Long orderNo = 1623315563399L;
        final ResponseVo<OrderVo> detail = orderInfoService.detail(5, orderNo);
        assertEquals(ResponseEnum.SUCCESS.getCode(),detail.getStatus());
    }

    @Test
    @DisplayName("测试取消订单")
    void cancel() {
        final ResponseVo<OrderVo> cancel = orderInfoService.cancel(5, 1623315563399L);
        assertEquals(ResponseEnum.SUCCESS.getCode(),cancel.getStatus());
    }
}