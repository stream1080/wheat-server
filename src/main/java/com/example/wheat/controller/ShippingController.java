package com.example.wheat.controller;


import com.example.wheat.conts.WheatConst;
import com.example.wheat.entity.User;
import com.example.wheat.form.ShippingForm;
import com.example.wheat.service.ShippingService;
import com.example.wheat.vo.ResponseVo;
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
@RequestMapping("/shipping")
@Api(tags = "收货地址模块API")
@CrossOrigin
public class ShippingController {

    @Autowired
    private ShippingService shippingService;

    @GetMapping("/list")
    @ApiOperation(value = "分页查询收货地址列表")
    public ResponseVo list (@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                            HttpSession session) {
        User user = (User) session.getAttribute(WheatConst.CURRENT_USER);
        return shippingService.list(user.getId(), pageNum, pageSize);
    }

    @PostMapping ("/add")
    @ApiOperation(value = "添加收货地址")
    public ResponseVo add(@Valid @RequestBody ShippingForm form,
                           HttpSession session) {
        User user = (User) session.getAttribute(WheatConst.CURRENT_USER) ;
        return shippingService.add(user.getId(),form);
    }

    @DeleteMapping("/del")
    @ApiOperation(value = "删除收货地址")
    public ResponseVo delete(@PathVariable Integer shippingId,
                             HttpSession session) {
        User user = (User) session.getAttribute(WheatConst.CURRENT_USER);
        return shippingService.delete(user.getId(),shippingId);
    }


    @PutMapping("/update")
    @ApiOperation(value = "更新收货地址")
    public ResponseVo update (@PathVariable Integer shippingId,
                              @Valid @RequestBody ShippingForm form,
                              HttpSession session) {
        User user = (User) session.getAttribute (WheatConst.CURRENT_USER);
        return shippingService.update(user.getId(),shippingId,form);
    }

}

