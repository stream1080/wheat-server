package com.example.wheat.service;

import com.example.wheat.WheatApplicationTests;
import com.example.wheat.vo.CategoryVo;
import com.example.wheat.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 分类模块单元测试类
 */
@Slf4j
class CategoryServiceTest extends WheatApplicationTests {

    @Autowired
    private CategoryService categoryService;

    @Test
    void selectAll() {
        ResponseVo<List<CategoryVo>> listResponseVo = categoryService.selectAll();
        assertEquals(0,listResponseVo.getStatus());
    }

    @Test
    void findSubCategoryId() {
        Set<Integer> set = new HashSet<>();
        categoryService.findSubCategoryId(2,set);
        log.info("Set={}",set);
    }
}