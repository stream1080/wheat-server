package com.example.wheat.controller;


import com.example.wheat.service.CategoryService;
import com.example.wheat.vo.CategoryVo;
import com.example.wheat.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResponseVo<List<CategoryVo>> selectAll(){
        return categoryService.selectAll();
    }


}

