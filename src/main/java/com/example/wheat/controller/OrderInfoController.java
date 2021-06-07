package com.example.wheat.controller;


import com.example.wheat.conts.WheatConst;
import com.example.wheat.entity.User;
import com.example.wheat.form.OrderCreateForm;
import com.example.wheat.service.OrderInfoService;
import com.example.wheat.vo.OrderVo;
import com.example.wheat.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
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
@RequestMapping("/order")
@Api(tags = "订单模块API")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @PostMapping("/add")
    public ResponseVo<OrderVo> create(@Valid @RequestBody OrderCreateForm form,
                                      HttpSession session) {
        User user = (User) session.getAttribute(WheatConst.CURRENT_USER);
        return orderInfoService.create(user.getId(),form.getShippingId());
    }

    @GetMapping("/list")
    public ResponseVo<PageInfo> list(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize ,
                                      HttpSession session) {
        User user = (User) session.getAttribute(WheatConst.CURRENT_USER);
        return orderInfoService.list(user.getId(),pageNum,pageSize);
    }

    @GetMapping("/info")
    public ResponseVo<OrderVo> detail(@PathVariable Long orderNo,
                                      HttpSession session) {
        User user = (User) session.getAttribute(WheatConst.CURRENT_USER);
        return orderInfoService.detail(user.getId(),orderNo);
    }

    @PutMapping ( "/cel")
    public ResponseVo<OrderVo> cancel(@PathVariable Long orderNo,
                                      HttpSession session) {
        User user = (User) session.getAttribute(WheatConst.CURRENT_USER);
        return orderInfoService.cancel(user.getId(),orderNo);
    }



}

