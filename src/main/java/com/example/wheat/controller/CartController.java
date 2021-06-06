package com.example.wheat.controller;


import com.example.wheat.conts.WheatConst;
import com.example.wheat.entity.User;
import com.example.wheat.form.CartAddForm;
import com.example.wheat.form.CartUptadtForm;
import com.example.wheat.service.CartService;
import com.example.wheat.vo.CartVo;
import com.example.wheat.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
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
@RequestMapping("/carts")
@Api(tags = "购物车模块API")
public class CartController {

    @Autowired
    private CartService cartService;


    @GetMapping("/list")
    @ApiOperation(value = "购物车商品列表")
    public ResponseVo<CartVo> list(HttpSession session){
        User user = (User) session.getAttribute(WheatConst.CURRENT_USER);
        return cartService.list(user.getId());
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加购物车商品")
    public ResponseVo<CartVo> add(@Valid @RequestBody CartAddForm cartAddForm,
                                  HttpSession session){
        User user = (User) session.getAttribute(WheatConst.CURRENT_USER);
        return cartService.add(user.getId(),cartAddForm);
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新购物车商品")
    public ResponseVo<CartVo> update(@PathVariable Integer productId,
                                     @Valid @RequestBody CartUptadtForm cartUptadtForm,
                                  HttpSession session){
        User user = (User) session.getAttribute(WheatConst.CURRENT_USER);
        return cartService.update(user.getId(),productId,cartUptadtForm);
    }

    @DeleteMapping("/del")
    @ApiOperation(value = "删除购物车商品")
    public ResponseVo<CartVo> delete(@PathVariable Integer productId,
                                     HttpSession session){
        User user = (User) session.getAttribute(WheatConst.CURRENT_USER);
        return cartService.delete(user.getId(),productId);
    }

    @PutMapping("/all")
    @ApiOperation(value = "全选购物车商品")
    public ResponseVo<CartVo> selectAll(HttpSession session){
        User user = (User) session.getAttribute(WheatConst.CURRENT_USER);
        return cartService.selectAll(user.getId());
    }

    @PutMapping("/unall")
    @ApiOperation(value = "全不选购物车商品")
    public ResponseVo<CartVo> unSelectAll(HttpSession session){
        User user = (User) session.getAttribute(WheatConst.CURRENT_USER);
        return cartService.unSelectAll(user.getId());
    }

    @GetMapping("/sum")
    @ApiOperation(value = "购物车商品总数")
    public ResponseVo<Integer> sum(HttpSession session){
        User user = (User) session.getAttribute(WheatConst.CURRENT_USER);
        return cartService.sum(user.getId());
    }

}

