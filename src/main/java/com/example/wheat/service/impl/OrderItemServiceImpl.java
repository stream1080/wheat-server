package com.example.wheat.service.impl;

import com.example.wheat.entity.OrderItem;
import com.example.wheat.mapper.OrderItemMapper;
import com.example.wheat.service.OrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stream
 * @since 2021-06-04
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}
