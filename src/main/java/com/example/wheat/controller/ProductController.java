package com.example.wheat.controller;


import com.example.wheat.service.CategoryService;
import com.example.wheat.service.ProductService;
import com.example.wheat.vo.CategoryVo;
import com.example.wheat.vo.ProductDetailVo;
import com.example.wheat.vo.ResponseVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author stream
 * @since 2021-06-04
 */
@RestController
@RequestMapping("/product")
@ApiModel(value="商品模块API", description="")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public ResponseVo<PageInfo> list(@RequestParam(required = false) Integer categoryId,
                                     @RequestParam(required = false,defaultValue = "1") Integer pageNum,
                                     @RequestParam(required = false,defaultValue = "10") Integer pageSize){

        return productService.list(categoryId,pageNum,pageSize);
    }

    @GetMapping("/getproductbyid")
    public ResponseVo<ProductDetailVo> detail(Integer productId){
        return productService.detail(productId);
    }
}

