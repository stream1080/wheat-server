package com.example.wheat.service.impl;

import com.example.wheat.entity.Product;
import com.example.wheat.mapper.ProductMapper;
import com.example.wheat.service.CategoryService;
import com.example.wheat.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.wheat.vo.ProductVo;
import com.example.wheat.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stream
 * @since 2021-06-04
 */
@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ResponseVo<List<ProductVo>> list(Integer categoryId, Integer pageNum, Integer pageSize) {
        Set<Integer> categoryIdSet = new HashSet<>();
        categoryService.findSubCategoryId(categoryId,categoryIdSet);
        categoryIdSet.add(categoryId);

        List<Product> products = productMapper.selectByCategoryIdSet(categoryIdSet);
        log.info("products={}",products);
        return null;
    }
}
