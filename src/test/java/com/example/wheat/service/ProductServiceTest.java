package com.example.wheat.service;

import com.example.wheat.WheatApplicationTests;
import com.example.wheat.enums.ResponseEnum;
import com.example.wheat.vo.ProductDetailVo;
import com.example.wheat.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;


/**
 * 商品模块单元测试类
 */
class ProductServiceTest extends WheatApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    void list() {
        ResponseVo<PageInfo> list = productService.list(0, 1, 2);
        assertEquals(ResponseEnum.SUCCESS.getCode(),list.getStatus());
    }

    @Test
    void detail() {
        ResponseVo<ProductDetailVo> detail = productService.detail(1);
        assertEquals(ResponseEnum.SUCCESS.getCode(),detail.getStatus());
    }
}