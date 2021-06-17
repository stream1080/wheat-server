package com.example.wheat.controller;


import com.example.wheat.conts.WheatConst;
import com.example.wheat.entity.User;
import com.example.wheat.form.CartAddForm;
import com.example.wheat.form.CartUptadtForm;
import com.example.wheat.mapper.UserMapper;
import com.example.wheat.service.CartService;
import com.example.wheat.vo.CartVo;
import com.example.wheat.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author stream
 * @since 2021-06-04
 */
@RestController
@Api(tags = "购物车模块API")
@CrossOrigin
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/carts")
    @ApiOperation(value = "购物车商品列表")
    public ResponseVo<CartVo> list(HttpSession session){
        User user = (User) session.getAttribute(WheatConst.CURRENT_USER);
        return cartService.list(user.getId());
    }

    @PostMapping("/carts")
    @ApiOperation(value = "添加购物车商品")
    public ResponseVo<CartVo> add(@Valid @RequestBody CartAddForm cartAddForm, Integer userId,
                                  HttpSession session){
        User user = (User) session.getAttribute(WheatConst.CURRENT_USER);
        log.info("cartAddForm = {}"+cartAddForm);
        return cartService.add(user.getId(),cartAddForm);
    }

    @PutMapping("/carts/{productId}")
    @ApiOperation(value = "更新购物车商品")
    public ResponseVo<CartVo> update(@PathVariable Integer productId,
                                     @Valid @RequestBody CartUptadtForm cartUptadtForm,
                                  HttpSession session){
        User user = (User) session.getAttribute(WheatConst.CURRENT_USER);
        return cartService.update(user.getId(),productId,cartUptadtForm);
    }

    @DeleteMapping("/carts/{productId}")
    @ApiOperation(value = "删除购物车商品")
    public ResponseVo<CartVo> delete(@PathVariable Integer productId,
                                     HttpSession session){
        User user = (User) session.getAttribute(WheatConst.CURRENT_USER);
        return cartService.delete(user.getId(),productId);
    }

    @PutMapping("/carts/selectAll")
    @ApiOperation(value = "全选购物车商品")
    public ResponseVo<CartVo> selectAll(HttpSession session){
        User user = (User) session.getAttribute(WheatConst.CURRENT_USER);
        return cartService.selectAll(user.getId());
    }

    @PutMapping("/carts/unSelectAll")
    @ApiOperation(value = "全不选购物车商品")
    public ResponseVo<CartVo> unSelectAll(HttpSession session){
        User user = (User) session.getAttribute(WheatConst.CURRENT_USER);
        return cartService.unSelectAll(user.getId());
    }

    @GetMapping("/carts/products/sum")
    @ApiOperation(value = "购物车商品总数")
    public ResponseVo<Integer> sum(HttpSession session){
        log.info("购物车 sessionId = {}" + session.getId());
        User user = (User) session.getAttribute(WheatConst.CURRENT_USER);
        return cartService.sum(user.getId());
    }
}

