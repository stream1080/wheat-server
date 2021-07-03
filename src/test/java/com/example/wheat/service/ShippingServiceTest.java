package com.example.wheat.service;

import com.example.wheat.WheatApplicationTests;
import com.example.wheat.entity.Shipping;
import com.example.wheat.enums.ResponseEnum;
import com.example.wheat.form.CartAddForm;
import com.example.wheat.form.ShippingForm;
import com.example.wheat.vo.CartVo;
import com.example.wheat.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 收货地址模块单元测试类
 */
@Slf4j
@Transactional
@DisplayName("收货地址模块单元测试")
class ShippingServiceTest extends WheatApplicationTests {

    @Autowired
    private ShippingService shippingService;

    @Test
    @Transactional
    @DisplayName("测试添加收货地址")
    void add() {
        ShippingForm form = new ShippingForm();
        form.setReceiverName ("张三");
        form.setReceiverAddress ("上海徐汇");
        form.setReceiverCity("上海");
        form.setReceiverMobile( "18812345678");
        form.setReceiverPhone( "010123456");
        form.setReceiverProvince("上海");
        form.setReceiverDistrict("徐汇区") ;
        form.setReceiverZip( "000000") ;

        ResponseVo<Map<String, Integer>> responseVo = shippingService.add(1,form);
        log.info("result={}",responseVo);
        assertEquals(ResponseEnum.SUCCESS.getCode(),responseVo.getStatus());
    }

    @Test
    @Transactional
    @DisplayName("测试删除收货地址")
    void delete() {
        ResponseVo delete = shippingService.delete(5, 1);
        assertEquals(ResponseEnum.SUCCESS.getCode(),delete.getStatus());
    }

    @Test
    @Transactional
    @DisplayName("测试更新收货地址")
    void update() {
        ShippingForm form = new ShippingForm();
        form.setReceiverName ("张三");
        form.setReceiverAddress ("上海徐汇");
        form.setReceiverCity("上海");
        form.setReceiverMobile( "18812345678");
        form.setReceiverPhone( "010123456");
        form.setReceiverProvince("上海");
        form.setReceiverDistrict("徐汇区") ;
        form.setReceiverZip( "000000") ;
        ResponseVo update = shippingService.update(5, 1, form);
        assertEquals(ResponseEnum.SUCCESS.getCode(),update.getStatus());

    }

    @Test
    @DisplayName("测试查询收货地址")
    void list() {
        ResponseVo<PageInfo> list = shippingService.list(1, 1, 2);
        assertEquals(ResponseEnum.SUCCESS.getCode(),list.getStatus());
    }
}