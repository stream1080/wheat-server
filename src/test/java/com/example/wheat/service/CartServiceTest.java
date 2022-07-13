package com.example.wheat.service;

import com.example.wheat.WheatApplicationTests;
import com.example.wheat.entity.Cart;
import com.example.wheat.form.CartAddForm;
import com.example.wheat.form.CartUptadtForm;
import com.example.wheat.vo.CartVo;
import com.example.wheat.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 购物车单元测试类
 */
@Slf4j
@Transactional
@DisplayName("购物车模块单元测试")
class CartServiceTest extends WheatApplicationTests {

    @Autowired
    private CartService cartService;

    @DisplayName("测试添加购物车")
    @Test
    @Transactional
    void add() {
        CartAddForm cartAddForm = new CartAddForm();
        cartAddForm.setProductId(1);
        cartAddForm.setSelected(true);
        ResponseVo<CartVo> add = cartService.add(1, cartAddForm);
        assertEquals(0,add.getStatus());
        log.info("cart={}",add);
    }

    @Test
    @DisplayName("测试删除购物车商品")
    @Transactional
    void delete() {
        ResponseVo<CartVo> delete = cartService.delete(5, 1);
        assertEquals(15,delete.getStatus());
    }

    @Test
    @Transactional
    @DisplayName("测试更新购物车")
    void update() {
        CartUptadtForm cartUptadtForm = new CartUptadtForm();
        cartUptadtForm.setQuantity(1);
        cartUptadtForm.setSelected(true);
        ResponseVo<CartVo> update = cartService.update(5, 1, cartUptadtForm);
        assertEquals(15,update.getStatus());
    }

    @Test
    @DisplayName("测试获取购物车列表")
    void list() {
        ResponseVo<CartVo> list = cartService.list(5);
        assertEquals(0,list.getStatus());
    }

    @Test
    @DisplayName("测试全选购物车商品")
    void selectAll() {
        ResponseVo<CartVo> responseVo = cartService.selectAll(5);
        assertEquals(0,responseVo.getStatus());
    }

    @Test
    @DisplayName("测试全不选购物车商品")
    void unSelectAll() {
        ResponseVo<CartVo> responseVo = cartService.unSelectAll(5);
        assertEquals(0,responseVo.getStatus());
    }

    @Test
    @DisplayName("测试获取购物车商品数量")
    void sum() {
        ResponseVo<Integer> responseVo = cartService.sum(5);
        assertEquals(0,responseVo.getStatus());
    }

    @Test
    @DisplayName("测试获取购物车商品")
    void listForCart() {
        List<Cart> carts = cartService.listForCart(5);
        assertEquals(0,carts.size());
    }
}