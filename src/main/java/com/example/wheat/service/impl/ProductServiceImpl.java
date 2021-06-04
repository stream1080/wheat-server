package com.example.wheat.service.impl;

import com.example.wheat.entity.Product;
import com.example.wheat.mapper.ProductMapper;
import com.example.wheat.service.ProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
