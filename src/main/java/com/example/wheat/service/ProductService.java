package com.example.wheat.service;

import com.example.wheat.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.wheat.vo.ProductVo;
import com.example.wheat.vo.ResponseVo;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stream
 * @since 2021-06-04
 */
public interface ProductService extends IService<Product> {

    ResponseVo<List<ProductVo>> list(Integer categoryId,Integer pageNum,Integer pageSize);
}
