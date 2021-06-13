package com.example.wheat.controller;


import com.example.wheat.conts.WheatConst;
import com.example.wheat.entity.User;
import com.example.wheat.form.OrderCreateForm;
import com.example.wheat.mapper.UserMapper;
import com.example.wheat.service.OrderInfoService;
import com.example.wheat.vo.OrderVo;
import com.example.wheat.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "订单模块API")
@CrossOrigin
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/orders")
    @ApiOperation(value = "创建订单")
    public ResponseVo<OrderVo> create(@Valid @RequestBody OrderCreateForm form,
                                      HttpSession session) {
        User user = (User) session.getAttribute(WheatConst.CURRENT_USER);
        return orderInfoService.create(user.getId(),form.getShippingId());
    }

    @GetMapping("/orders")
    @ApiOperation(value = "查询订单列表")
    public ResponseVo<PageInfo> list(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize ,
                                      HttpSession session) {
        User user = (User) session.getAttribute(WheatConst.CURRENT_USER);
        return orderInfoService.list(user.getId(),pageNum,pageSize);
    }

    @GetMapping("/orders/{orderNo}")
    @ApiOperation(value = "查询订单详情")
    public ResponseVo<OrderVo> detail(@PathVariable Long orderNo,
                                      HttpSession session) {
        User user = (User) session.getAttribute(WheatConst.CURRENT_USER);
        user = userMapper.selectById(5);
        return orderInfoService.detail(user.getId(),orderNo);
    }

    @PutMapping ( "/orders/{orderNo}")
    @ApiOperation(value = "取消订单")
    public ResponseVo<OrderVo> cancel(@PathVariable Long orderNo,
                                      HttpSession session) {
        User user = (User) session.getAttribute(WheatConst.CURRENT_USER);
        return orderInfoService.cancel(user.getId(),orderNo);
    }

}

