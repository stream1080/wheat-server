package com.example.wheat.service;

import com.example.wheat.WheatApplicationTests;
import com.example.wheat.enums.ResponseEnum;
import com.example.wheat.vo.ProductDetailVo;
import com.example.wheat.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;


/**
 * 商品模块单元测试类
 */
@DisplayName("商品模块单元测试")
class ProductServiceTest extends WheatApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    @DisplayName("测试查询商品列表")
    void list() {
        ResponseVo<PageInfo> list = productService.list(0, 1, 2);
        assertEquals(ResponseEnum.SUCCESS.getCode(),list.getStatus());
    }

    @Test
    @DisplayName("测试查询商品详情")
    void detail() {
        ResponseVo<ProductDetailVo> detail = productService.detail(1);
        assertEquals(ResponseEnum.SUCCESS.getCode(),detail.getStatus());
    }
}